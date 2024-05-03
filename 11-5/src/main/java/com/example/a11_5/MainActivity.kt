package com.example.a11_5

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a11_5.databinding.ActivityMainBinding
import com.example.a11_5.databinding.ItemPagerBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = mutableListOf("pager1","pager2","pager3" )
        binding.viewpager.adapter = MyPagerAdapter(data)
    }
}

class MyPagerViewHolder(val binding: ItemPagerBinding):
        RecyclerView.ViewHolder(binding.root)
class MyPagerAdapter(val data:MutableList<String>):
        RecyclerView.Adapter<MyPagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPagerViewHolder {
        return MyPagerViewHolder(
            ItemPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyPagerViewHolder, position: Int) {
        val binding = (holder as MyPagerViewHolder).binding
        binding.itemPagerTextView.text = data[position]
        when(position%3){
            0-> binding.itemPagerTextView.setBackgroundColor(Color.RED)
            1-> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
            2-> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
        }
    }
}
