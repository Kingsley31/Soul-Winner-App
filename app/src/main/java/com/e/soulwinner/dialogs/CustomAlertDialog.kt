package com.e.soulwinner.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.e.soulwinner.R

class CustomAlertDialog(private val context: Context,
                        private val title:String,
                        private val body:String){

    var dialog: Dialog?=null

    fun show(){
        dialog= Dialog(context, R.style.full_screen_dialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view: View = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog,null)
        val title_txt: TextView =view.findViewById(R.id.title)
        title_txt.text=title
        val body_txt: TextView =view.findViewById(R.id.body)
        body_txt.text=body
        val ok_btn:Button=view.findViewById(R.id.ok_btn)
        ok_btn.setOnClickListener({
            dialog?.dismiss()
            dialog=null
        })
        dialog?.setCancelable(false)
        dialog?.setContentView(view)
        dialog?.show()
    }
}