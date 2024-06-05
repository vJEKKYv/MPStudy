package com.example.a17_2

import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a17_2.databinding.ActivityMainBinding
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED)
            Toast.makeText(this, "외부저장소 사용 가능", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "외부저장소 사용 불가", Toast.LENGTH_SHORT).show()
        // 외부 저장소에서 쓰려면 아래의 file을 대신 쓰면 된다.
        /*
        val file = File(getExternalFilesDir(null), "test.txt")
         */
        binding.writeBtn.setOnClickListener {
            // 내부 저장소에서 쓰기
            val file = File(filesDir, "test.txt")

            val writeStream:OutputStreamWriter = file.writer()
            writeStream.write("Hello World! ")
            writeStream.flush()
        }
        binding.readBtn.setOnClickListener {
            var resultString = ""
            try {
                val file = File(filesDir, "test.txt")
                val readStream: BufferedReader = file.reader().buffered()
                readStream.forEachLine {
                    resultString += it
                }
            }catch (e:Exception){ resultString = "File이 없습니다. "}
            binding.result.text = resultString
        }
        binding.outputBtn.setOnClickListener {
            val output: FileOutputStream = openFileOutput("test.txt", MODE_APPEND)
            output.write("\nHello World".toByteArray())
        }
        binding.inputBtn.setOnClickListener {
            var resultString = ""
            try {
                openFileInput("test.txt").bufferedReader().forEachLine {
                    resultString += it + "\n"
                }
            }catch (e:Exception){ resultString = "File이 없습니다. "}
            binding.result.text = resultString
        }
    }
}