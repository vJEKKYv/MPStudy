package com.example.a15_3

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a15_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val JOBID = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receiver = object:BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                binding.resultText.text = intent?.getStringExtra("RESULT_TITLE") + ":" +
                        intent?.getIntExtra("RESULT", 0).toString()
            }
        }
        val filter = IntentFilter("SEND_RESULT")
        registerReceiver(receiver, filter, RECEIVER_EXPORTED)

        val jobScheduler:JobScheduler? = getSystemService<JobScheduler>()
        lateinit var jobInfo: JobInfo
        binding.startJobService.setOnClickListener {
            binding.resultText.text = "RESULT ?"
            JobInfo.Builder(JOBID, ComponentName(this, MyJobService::class.java)).run {
                val extra = PersistableBundle()
                extra.putString("TITLE", "Hello Job")
                setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                setExtras(extra)
                jobInfo = build()
                jobScheduler?.schedule(jobInfo)
            }
        }
        binding.cancelJobService.setOnClickListener {
            jobScheduler?.cancel(JOBID)
        }
    }
}