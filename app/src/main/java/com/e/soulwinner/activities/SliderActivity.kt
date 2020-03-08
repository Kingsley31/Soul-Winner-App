package com.e.soulwinner.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.e.soulwinner.R
import com.e.soulwinner.adapters.SliderAdapter
import com.e.soulwinner.models.Slide
import com.e.soulwinner.viewmodels.SliderViewModel

class SliderActivity : AppCompatActivity() {
    lateinit var sliderViewModel:SliderViewModel
    var viewPager:ViewPager?=null
    var loginButton:Button?=null
    var leftButton:ImageView?=null
    var rightButton:ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)

        viewPager=findViewById(R.id.viewPager)
        loginButton=findViewById(R.id.singInBtn)
        leftButton=findViewById(R.id.leftButton)
        rightButton=findViewById(R.id.rightButton)

        sliderViewModel=ViewModelProviders.of(this)[SliderViewModel::class.java]
        setUpViewPager(sliderViewModel.getSlides())
        viewPager!!.currentItem=sliderViewModel.getCurrentSlidePosition()

        leftButton!!.setOnClickListener({gotoPreviousSlide()})
        rightButton!!.setOnClickListener({gotoNextSlide()})
        loginButton?.setOnClickListener({gotoLoginActivity()})

    }

    private fun gotoPreviousSlide(){
        if (sliderViewModel.getCurrentSlidePosition() > 0) {
            sliderViewModel.setCurrentSlidePosition(sliderViewModel.getCurrentSlidePosition()-1)
            viewPager!!.currentItem = sliderViewModel.getCurrentSlidePosition()
        }
    }

    private fun gotoNextSlide(){
        if (sliderViewModel.getCurrentSlidePosition() < sliderViewModel.getSlides().size - 1) {
            sliderViewModel.setCurrentSlidePosition(sliderViewModel.getCurrentSlidePosition()+1)
            viewPager!!.currentItem = sliderViewModel.getCurrentSlidePosition()
        }
    }

    private fun gotoLoginActivity(){
        val intent: Intent =Intent(this,LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left)
    }


    private fun setUpViewPager(slides:List<Slide>){
        val sliderAdapter=SliderAdapter(this,slides)
        viewPager?.adapter=sliderAdapter
    }
}
