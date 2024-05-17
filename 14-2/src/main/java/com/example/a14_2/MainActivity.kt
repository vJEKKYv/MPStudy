package com.example.a14_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a14_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var batteryStatusMessage:String = ""
        var battery:String = ""

        val battery_receiver = object :BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                when(batteryStatus?.action){
                    Intent.ACTION_BATTERY_OKAY -> batteryStatusMessage = "배터리 상태: OK"
                    Intent.ACTION_BATTERY_LOW -> batteryStatusMessage = "배터리 상태: LOW"
                    Intent.ACTION_POWER_CONNECTED -> batteryStatusMessage = "배터리 상태: CONNECTED"
                    Intent.ACTION_POWER_DISCONNECTED -> batteryStatusMessage = "배터리 상태: DISCONNECTED"
                    Intent.ACTION_SCREEN_OFF -> batteryStatusMessage = Toast.makeText(applicationContext, "Screen OFF", Toast.LENGTH_SHORT).show()
                    Intent.ACTION_SCREEN_ON -> batteryStatusMessage = Toast.makeText(applicationContext, "Screen ON", Toast.LENGTH_SHORT).show()
                    else->{
                        val level = batteryStatus!!.getInExtra(BatteryManager.EXTRA_LEVEL, -1)
                        val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                        val batteryPct = level / scale.toFloat()*100
                        battery = "배터리량: $batteryPct %"
                    }
                }
                binding.batteryLevel.text = batteryStatusMessage + "\n" + battery
            }
        }
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED).apply {
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_SCREEN_ON)
        }
        registerReceiver(battery_receiver, intentFilter, RECEIVER_EXPORTED)
    }
}