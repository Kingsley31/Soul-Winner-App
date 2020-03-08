package com.e.soulwinner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.e.soulwinner.R
import com.e.soulwinner.databinding.ActivityLoginBinding
import com.e.soulwinner.dialogs.CustomAlertDialog
import com.e.soulwinner.dialogs.ProgressDialog
import com.e.soulwinner.viewmodels.LoginActivityViewModel
import kotlinx.android.synthetic.main.login_form.view.*

class LoginActivity : AppCompatActivity() {
    lateinit  var loginBinding:ActivityLoginBinding
    lateinit var progressDialog:ProgressDialog
    lateinit var customAlertDialog: CustomAlertDialog
    lateinit var loginActivityViewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivityViewModel= ViewModelProviders.of(this)[LoginActivityViewModel::class.java]

        loginActivityViewModel.getLoading().observe(this,isLoadingObserver)
        loginActivityViewModel.getLoggedIn().observe(this,isLoggedInObserver)
        loginActivityViewModel.getErrorMessage().observe(this,errorMessageObserver)

        progressDialog= ProgressDialog(this,"Authenticating user...")
        loginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginBinding.root.findViewById<Button>(R.id.login_btn).setOnClickListener {
            val phone=loginBinding.root.phone_ed_txt.text.toString()
            val code=loginBinding.root.code_ed_txt.text.toString()
            if(validateLoginForm(phone,code)){
                loginActivityViewModel.loginUser(phone,code)
            }
        }
    }

    val errorMessageObserver = Observer<String> { errorMessage ->
        if(!errorMessage.isEmpty()){
            Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()
        }
    }

    val isLoggedInObserver= Observer<Boolean> { isLoggedIn:Boolean->
        gotoDashboardIfLoggedIn(isLoggedIn)
    }

    val isLoadingObserver= Observer <Boolean>{ isLoading:Boolean->
        showOrHideProgressDialog(isLoading)
    }

    private fun gotoDashboardIfLoggedIn(isLoggedIn:Boolean){
        if(isLoggedIn==true){
            loginBinding.root.phone_ed_txt.setText("")
            loginBinding.root.code_ed_txt.setText("")
            val intent=Intent(this,DashboardActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left)
        }
    }

    private fun validateLoginForm(phone:String,code:String):Boolean{
        var errorMessage=""
        if(phone.isEmpty()) {
            errorMessage="Phone number is required"
            showCustomAlertDialog(errorMessage )
            return false
        }

        //|| phone.length>11
        if(phone.length<11){
            errorMessage="Invalid Phone number, must be 11 characters"
            showCustomAlertDialog(errorMessage )
            return false
        }

        if(code.isEmpty()){
            errorMessage="Your code is required"
            showCustomAlertDialog(errorMessage )
            return false
        }

        return true
    }

    private fun showOrHideProgressDialog(isLoading:Boolean){
        if(isLoading==true){
            progressDialog.show()
        }else if(isLoading==false){
            if(progressDialog!=null) progressDialog.hide()
        }
    }


    private fun showCustomAlertDialog(body:String){
        customAlertDialog= CustomAlertDialog(this,"Validation Info",body)
        customAlertDialog.show()
    }
}
