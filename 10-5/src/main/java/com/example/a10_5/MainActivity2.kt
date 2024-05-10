package com.example.a10_5

import android.app.NotificationManager
import android.app.RemoteInput
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a10_5.databinding.ActivityMain2Binding
import com.example.a10_5.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "알림 결과"
         val replyTxt = RemoteInput.getResultsFromIntent(intent)
             ?.getCharSequence("key_text_reply")

        binding.result.text = replyTxt

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(11)
    }
}