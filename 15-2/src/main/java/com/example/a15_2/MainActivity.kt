package com.example.a15_2

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
import com.example.a15_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var aidlService: MyAidlInterface
        var connection: ServiceConnection = object :ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Toast.makeText(applicationContext,"onServiceConnected", Toast.LENGTH_SHORT).show()
                aidlService = MyAidlInterface.Stub.asInterface(service)
                binding.resultText.text = aidlService.funB().toString()
            }
            override fun onServiceDisconnected(name: ComponentName?) {
                Toast.makeText(applicationContext,"onServiceDisconnected", Toast.LENGTH_SHORT).show()
            }
        }
        binding.bindService.setOnClickListener {
            bindService(Intent(this, MyAIDLService::class.java), connection, Context.BIND_AUTO_CREATE)
        }
        binding.unbindService.setOnClickListener {
            unbindService(connection)
        }
    }
}