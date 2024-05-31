package com.example.a17_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a17_1.databinding.ResultItemBinding

class ResultHolder(val binding: ResultItemBinding):RecyclerView.ViewHolder(binding.root)
class ResultAdpater(val names:MutableList<String>, val phones: MutableList<String>):
        RecyclerView.Adapter<ResultHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder =
        ResultHolder(ResultItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    override fun getItemCount(): Int = names.size + 1
    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        if (position == 0){
            holder.binding.name.text = "[[ NAME ]]"
            holder.binding.phone.text = "[[ PHONE ]]"
        } else{
            holder.binding.name.text = names[position - 1]
            holder.binding.phone.text = phones[position - 1]
        }
    }

}