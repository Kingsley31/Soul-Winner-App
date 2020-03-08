package com.e.soulwinner.dialogs


import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.e.soulwinner.R
import kotlinx.android.synthetic.main.progress_dialog.view.*

class ProgressDialog(private val context: Context,private val message:String){
    var dialog: Dialog?=null


    fun show(){
        dialog= Dialog(context,R.style.full_screen_dialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view: View =LayoutInflater.from(context).inflate(R.layout.progress_dialog,null)
        val progressTxt:TextView=view.findViewById(R.id.progress_txt)
        val imageView:ImageView=view.findViewById<ImageView>(R.id.imageView3)
        imageView.apply {
            setBackgroundResource(R.drawable.loading_animation)
        }
        val animationDrawable:AnimationDrawable=imageView.background as AnimationDrawable
        progressTxt.setText(message)
        dialog?.setContentView(view)
        dialog?.show()
        animationDrawable.start()
    }

    fun hide(){
        dialog?.hide()
        dialog=null
    }
}