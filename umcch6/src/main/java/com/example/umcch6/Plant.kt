package com.example.umcch6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.umcch6.databinding.FragmentPlantBinding
import com.example.umcch6.databinding.ViewPagerReBinding
import me.relex.circleindicator.CircleIndicator3

class Plant : Fragment() {
    private val viewBinding:FragmentPlantBinding by lazy{
        FragmentPlantBinding.inflate(layoutInflater)
    }
    private fun getDrawable():ArrayList<Int>{
        return arrayListOf<Int>(
            R.drawable.bunch,
            R.drawable.flower,
            R.drawable.vase)
    }
    lateinit var viewPagerImage: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPagerImage = viewBinding.vp2
        viewPagerImage.adapter = ViewPagerReAdapter(getDrawable())
        viewPagerImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator:CircleIndicator3 = viewBinding.indicator
        indicator.setViewPager(viewPagerImage)
        return viewBinding.root
    }
}