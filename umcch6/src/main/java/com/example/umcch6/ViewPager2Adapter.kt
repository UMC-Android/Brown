package com.example.umcch6

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2Adapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> vf1()
            1 -> vf2()
            else -> vf3()
        }
    }
}