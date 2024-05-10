package com.example.a10_3_1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.a10_3_1.databinding.ActivityMainBinding
import com.example.a10_3_1.databinding.DialogInputBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dialog.setOnClickListener{
            val dialogBinding = DialogInputBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setTitle("Input")
                setView(dialogBinding.root)
                setPositiveButton("닫기"){_,_ ->
                    val message = dialogBinding.editText.text.toString() +
                            when(dialogBinding.gender.checkedRadioButtonId){
                                R.id.male -> "(male)"
                                R.id.female -> "(female)"
                                else -> "()"
                            }
                    Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }
    }
}