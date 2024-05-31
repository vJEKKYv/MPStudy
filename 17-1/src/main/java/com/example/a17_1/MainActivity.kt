package com.example.a17_1

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a17_1.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    val names = mutableListOf<String>()
    val phones = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "전화번호부"
        val path: File = getDatabasePath("testdb")
        if(path.exists().not()){
            val db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE, null)
            db.execSQL("create table USER_TB (_id integer primary key autoincrement, name text not null, phone text)")
            db.close()
        }
        binding.resultList.layoutManager = LinearLayoutManager(this)
        val adpater = ResultAdpater(names, phones)
        binding.resultList.adapter = adpater
        binding.resultList.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))

        binding.searchAllBtn.setOnClickListener {
            val db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE, null)
            val cursor = db.rawQuery("select name, phone from USER_TB", null)
            names.clear()
            phones.clear()
            while (cursor.moveToNext()){
                names.add(cursor.getString(0))
                phones.add(cursor.getString(1))
            }
            db.close()
            adpater.notifyDataSetChanged()
        }
        binding.insertBtn.setOnClickListener {
            val db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE, null)

            db.execSQL("insert into USER_TB(name, phone) values(?,?)",
                arrayOf(binding.inputName.text.toString(), binding.inputPhone.text.toString()))
            db.close()
        }
        binding.deleteBtn.setOnClickListener {
            val db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE, null)
            db.execSQL("delete from USER_TB")
            db.close()
        }
    }
}