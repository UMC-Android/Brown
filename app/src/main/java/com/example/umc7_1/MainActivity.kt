package com.example.umc7_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import com.example.umc7_1.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    var total = 0
    var stopped = false
    var started = false
    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val minute = String.format("%02d",total/60)
            val second = String.format("%02d",total%60)
            binding.timeNow.text = "$minute : $second"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.startButton.setOnClickListener{
            started = true
            thread(start=true) {
                total = binding.inputTime.text.toString().toInt()
                while (started) {
                    Thread.sleep(1000)
                    if (started) {
                        total -= 1
                        handler?.sendEmptyMessage(0)

                    }
                    if (total == 0)
                        break
                }
                if (!stopped)
                    runOnUiThread {
                        Toast.makeText(applicationContext, "타이머가 종료되었습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
            }
        }
        binding.stopButton.setOnClickListener{
                started = false
                stopped = true
                Toast.makeText(applicationContext, "정지되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}