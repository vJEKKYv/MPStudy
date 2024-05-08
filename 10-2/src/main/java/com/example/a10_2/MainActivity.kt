package com.example.a10_2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a10_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*
        binding.settingDate.setOnClickListener{
            DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d("kkang", "date: $year, ${month + 1}, $dayOfMonth")
                }
            },2023,5,1).show()
        }
        binding.settingTime.setOnClickListener{
            TimePickerDialog(this,object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Log.d("kkang", "Time: $hourOfDay, $minute")
                }
            },15,30,true).show()
        }
        binding.settingInfo.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("test dialog")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("OK"){dialog,which->Log.d("kkang", "OK!!")}
                setNegativeButton("Cancel"){dialog,which->Log.d("kkang", "Cancel!!")}
                setNeutralButton("More"){dialog,which->Log.d("kkang", "More!!")}
                show()
            }
        }
         */
        binding.settingDate.setOnClickListener{
            DatePickerDialog(this,
                {view, year,month,dayOfMonth->Log.d("kkang", "date: $year, ${month + 1}, $dayOfMonth")}
                ,2023,5,1).show()
        }
        binding.settingTime.setOnClickListener{
            TimePickerDialog(this,
                {view, hourOfDay, minute ->Log.d("kkang", "Time: $hourOfDay, $minute")},
                15,30,true).show()
        }
        val handler = DialogInterface.OnClickListener{dialog, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE->Log.d("kkang", "OK!!")
                DialogInterface.BUTTON_NEGATIVE->Log.d("kkang", "Cancel!!")
                DialogInterface.BUTTON_NEUTRAL->Log.d("kkang", "More!!")

            }
        }
        binding.settingInfo.setOnClickListener{
            AlertDialog.Builder(this).run {
                setTitle("test dialog")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("OK"){dialog,which->Log.d("kkang", "OK!!")}
                setNegativeButton("Cancel"){dialog,which->Log.d("kkang", "Cancel!!")}
                setNeutralButton("More"){dialog,which->Log.d("kkang", "More!!")}
                show()
            }
        }
    }
}