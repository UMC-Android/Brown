package com.example.umc5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.SimpleAdapter.ViewBinder
import com.example.umc5.databinding.ActivityAddMemoBinding

class AddMemoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddMemoBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var existed = intent.getStringExtra("existed")
        binding.textEdit.setText(existed)
        binding.submitBtn.setOnClickListener {
            val reIntent = Intent(this,MainActivity::class.java)
            reIntent.putExtra("text",binding.textEdit.text.toString())

            setResult(RESULT_OK,reIntent)
            finish()
        }
    }
}