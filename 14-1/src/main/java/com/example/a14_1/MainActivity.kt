package com.example.a14_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a14_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val receiver = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Toast.makeText(context, "동적 MyReceiver", Toast.LENGTH_SHORT).show()
            }
        }

        val filter = IntentFilter("ACTION_RECEIVER")
        registerReceiver(receiver, filter, RECEIVER_EXPORTED)

        binding.implicitExec.setOnClickListener{
            val intent = Intent()
            intent.action = "ACTION_RECEIVER"
            sendBroadcast(intent)
        }
        binding.explicitExec.setOnClickListener{
            sendBroadcast(Intent(this, MyReceiver::class.java))
        }
    }
}
class MyReceiver:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"정적 MyReceiver", Toast.LENGTH_SHORT).show()
    }
}