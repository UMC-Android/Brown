package com.example.umcch6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.umcch6.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Home : Fragment() {
    private val viewBinding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = ViewPager2Adapter(requireActivity())
        viewBinding.pager.adapter = adapter

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(viewBinding.tabLayout, viewBinding.pager) { tab, position ->
            // 탭 이름 설정
            tab.text = "Tab ${position + 1}"
        }.attach()

        return viewBinding.root
    }
}
