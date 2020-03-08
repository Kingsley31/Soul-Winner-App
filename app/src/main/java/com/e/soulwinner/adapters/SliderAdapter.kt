package com.e.soulwinner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.e.soulwinner.R
import com.e.soulwinner.databinding.ViewPagerItemBinding
import com.e.soulwinner.models.Slide
import kotlinx.android.synthetic.main.activity_slider.view.*

class SliderAdapter(private val context:Context,private val slides:List<Slide>):PagerAdapter(){


    override fun isViewFromObject(view: View, obj: Any): Boolean {
       return view == obj as ConstraintLayout
    }

    override fun getCount(): Int {
       return  slides.size
    }


    override fun instantiateItem(container:ViewGroup,position:Int):Any{
        val viewPagerItemBinding:ViewPagerItemBinding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.view_pager_item,container,false)
        viewPagerItemBinding.slide=slides[position]
        container.addView(viewPagerItemBinding.root)
        return viewPagerItemBinding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

}