package com.example.a10_4

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a10_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ringtonBtn.setOnClickListener{
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext,notification)
            ringtone.play()
        }
        binding.soundBtn.setOnClickListener{
            val player = MediaPlayer.create(this,R.raw.fallbackring)
            player.start()
        }
    }
}