package com.example.a10_3

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a10_3.databinding.ActivityMainBinding
import java.io.DataInput

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val items = arrayOf("사과","복숭아", "수박", "딸기")
        binding.setItem.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(items,object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(applicationContext,"선택된 과일: ${items[which]}",
                            Toast.LENGTH_SHORT).show()
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }
        }
        binding.multiChoice.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setMultiChoiceItems(items, booleanArrayOf(true,false,true,false),
                    object : DialogInterface.OnMultiChoiceClickListener{
                        override fun onClick(
                            dialog: DialogInterface?,
                            which: Int,
                            isChecked: Boolean
                        ) {
                            Toast.makeText(
                                applicationContext,
                                "${items[which]}이 ${if (isChecked) "선택되었습니다" else "해제되었습니다. "}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                setPositiveButton("닫기",null)
                show()
            }
        }
        binding.singleChoice.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setSingleChoiceItems(items, 1, object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(applicationContext,"${items[which]}이 선택되었습니다. ",
                            Toast.LENGTH_SHORT).show()
                    }
                })
                setCancelable(false)
                setPositiveButton("닫기", null)
                show()
            }
        }
    }
}