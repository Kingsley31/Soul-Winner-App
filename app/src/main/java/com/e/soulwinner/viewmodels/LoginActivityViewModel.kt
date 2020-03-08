package com.e.soulwinner.viewmodels


import com.e.soulwinner.models.User
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.e.soulwinner.api.SojApi
import com.e.soulwinner.data.UserDao
import com.e.soulwinner.data.UserPreferences
import com.e.soulwinner.models.SojApiResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivityViewModel(application: Application): AndroidViewModel(application) {
    private var isloading:MutableLiveData<Boolean> = MutableLiveData(false)
    private var isLoggedIn:MutableLiveData<Boolean> =MutableLiveData(false)
    private var errorMessage:MutableLiveData<String> =MutableLiveData("")
    private lateinit var userDao: UserDao
    private lateinit var userPreferences:UserPreferences
    init {

        userDao= com.e.soulwinner.data.SojDatabase.getDatabase(application).userDao()
        userPreferences= UserPreferences.getInstance(application)
    }


    fun getLoading():LiveData<Boolean>{
        return isloading
    }

    fun getLoggedIn():LiveData<Boolean>{
        return isLoggedIn
    }

    fun getErrorMessage():LiveData<String>{
        return errorMessage
    }

    fun loginUser(phone:String,code:String){
        isloading.postValue(true)
        val loginCall: Call<SojApiResponse> =SojApi.create().loginUser(phone,code)
        loginCall.enqueue(object: Callback<SojApiResponse> {
            override fun onFailure(call: Call<SojApiResponse>, t: Throwable) {
                Log.d("Retrofit",t.message)
                isloading.postValue(false)
                errorMessage.postValue("Error in network connection")
            }

            override fun onResponse( call: Call<SojApiResponse>, response: Response<SojApiResponse>) {
                isloading.postValue(false)
               if(response.isSuccessful){
                   val sojApiResponse=response.body()
                   if(sojApiResponse?.status==200){
                       val gson:Gson= Gson()
                       val userString=gson.toJson(sojApiResponse.data)
                       val user = Gson().fromJson(userString, User::class.java)
                       Log.d("Retrofit",user.address)
                       viewModelScope.launch { userDao.insert(user) }
                       userPreferences.setUserIsLoggedIn(true)
                       userPreferences.setLoggedInUserId(user.id)
                       isLoggedIn.postValue(true)

                   }else{
                       errorMessage.postValue("Invalid login credential")
                   }
               }else{
                   errorMessage.postValue("Something went wrong")
               }
            }

        })
    }
}