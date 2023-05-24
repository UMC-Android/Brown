package com.example.umcch4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umcch4.databinding.ActivityCheckAcitivityBinding

class CheckAcitivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCheckAcitivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCheckAcitivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val ms = intent.getStringExtra("message")
        viewBinding.textView.text = ms
    }
}