package com.e.soulwinner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

import androidx.constraintlayout.widget.ConstraintLayout
import com.e.soulwinner.R


class MainActivity : AppCompatActivity() {
      lateinit var constraintLayout:ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        constraintLayout = findViewById(R.id.constraint_layout)

        Handler().postDelayed({ gotoSliderActivity()},2000)
    }

    fun gotoSliderActivity(){
        val intent =Intent(this, SliderActivity::class.java)
        startActivity(intent)
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_left
        )
        finish()
    }


}
