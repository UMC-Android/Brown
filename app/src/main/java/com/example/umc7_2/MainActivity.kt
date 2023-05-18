package com.example.umc7_2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc7_2.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.waltz)
        binding.seekBar.max = mediaPlayer.duration
        var pausePosition = 0
        binding.playButton.setOnClickListener {
            mediaPlayer.seekTo(pausePosition)
            mediaPlayer.start()

            Thread {
                while (mediaPlayer.isPlaying) {
                    Thread.sleep(1000)
                    runOnUiThread {
                        binding.seekBar.progress = mediaPlayer.currentPosition
                        val minute = String.format("%02d",mediaPlayer.currentPosition/60000)
                        val second = String.format("%02d",(mediaPlayer.currentPosition%60000)/1000)
                        binding.timeText.text = "$minute : $second"
                    }
                }
            }.start()
            binding.pauseButton.setOnClickListener {
                pausePosition = mediaPlayer.currentPosition
                mediaPlayer.pause()
            }
            binding.stopButton.setOnClickListener {
                pausePosition=0
                mediaPlayer.pause()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
