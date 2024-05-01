package com.example.ch11_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch11_4.databinding.ActivityMainBinding
import com.example.ch11_4.databinding.ItemMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for (i in 1..10){
            datas.add("Item $i")
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(datas)
    }
}
class MyViewHolder(val binding: ItemMainBinding):RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas:MutableList<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }
    override fun getItemCount(): Int {
        return datas.size
    }
}