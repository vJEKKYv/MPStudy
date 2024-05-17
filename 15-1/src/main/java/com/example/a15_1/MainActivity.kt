package com.example.a15_1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a15_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startService.setOnClickListener{
            startService(Intent(this, MyService::class.java))
        }
        binding.stopService.setOnClickListener{
            stopService(Intent(this, MyService::class.java))
        }
        var serviceBinder:MyService.MyBinder
        var connection: ServiceConnection = object : ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Toast.makeText(applicationContext, "onServiceConnected", Toast.LENGTH_SHORT).show()
                serviceBinder = service as MyService.MyBinder
                binding.resultText.text = serviceBinder.funB(10).toString()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Toast.makeText(applicationContext, "onServiceDisConnected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.bindService.setOnClickListener{
            bindService(Intent(this, MyService::class.java),connection, Context.BIND_AUTO_CREATE)
        }
        binding.unbindService.setOnClickListener{
            unbindService(connection)
        }
    }
}