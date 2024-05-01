package com.example.mpstudy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mpstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_content, OneFragment())
        transaction.commit()
        binding.button1.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_content,OneFragment())
            transaction.commit()
        }
        binding.button2.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_content,TwoFragment())
            transaction.commit()
        }
    }
}
