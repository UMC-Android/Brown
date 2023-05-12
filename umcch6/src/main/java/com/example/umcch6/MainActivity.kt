package com.example.umcch6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById(R.id.bottom_navi)
        changeFragment(Home())

        bottomNavigation.run {

            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.Home -> {
                        changeFragment(Home())
                    }
                    R.id.Plant -> {
                        changeFragment(Plant())
                    }
                    R.id.Camera -> {
                        changeFragment(Camera())
                    }

                }
                true
            }
        }


    }

    fun changeFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}