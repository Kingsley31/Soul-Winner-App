package com.e.soulwinner.viewmodels

import androidx.lifecycle.ViewModel
import com.e.soulwinner.R
import com.e.soulwinner.models.Slide

class SliderViewModel :ViewModel(){
    private val slides:List<Slide> = listOf<Slide>(Slide("Create a quick account or membership to get started or sign in with already registered account.",R.drawable.person),
        Slide("Register a non SOJ member to your account and stay connected to follow-up.",R.drawable.icon_with_multi_color),
        Slide("Keep track of com.e.soulwinner.models.Soul until converted as a full member of streams of Joy to get a soul count.",R.drawable.hand_phone)
    )

    private var currentSlidePosition:Int=0


    fun getSlides():List<Slide>{
        return  slides
    }

    fun getCurrentSlidePosition():Int{
        return currentSlidePosition;
    }

    fun setCurrentSlidePosition(position:Int){
        currentSlidePosition=position
    }

}