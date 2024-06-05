package com.example.a17_3

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a17_3.databinding.ActivityMainBinding
import com.example.a17_3.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    companion object{
        val PREF = "my_prefs"
        val OPTION = "option"
        val LABEL = "label"
    }
    lateinit var sharedPref: SharedPreferences
    lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Setting"

        sharedPref = getSharedPreferences(PREF, MODE_PRIVATE)
        binding.Option.isChecked = sharedPref.getBoolean(OPTION, false)
        binding.label.setText(sharedPref.getString(LABEL,""))

        binding.completeBtn.setOnClickListener {
            sharedPref.edit().run {
                putBoolean(OPTION, binding.Option.isChecked)
                putString(LABEL, binding.label.text.toString())
                commit()
            }
            finish()
        }
    }
}