package com.miraclehwan.miraclespeech.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.miraclegithub.util.Log
import com.miraclehwan.miraclespeech.databinding.ItemResultBinding

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    var itemList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun setItems(items: MutableList<String>) {
        itemList = items
        notifyDataSetChanged()
    }

    inner class ResultViewHolder(val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.result = item
        }
    }
}