package com.miraclehwan.miraclegithub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.miraclegithub.databinding.ItemRespositoryBinding
import com.miraclehwan.miraclegithub.network.response.Item

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchResultViewHolder>() {

    var itemList = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            ItemRespositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItems(items: List<Item>) {
        if (items.size == 0) {
            itemList.clear()
            notifyDataSetChanged()
        } else {
            val originPos = itemList.size
            itemList.addAll(items)
            notifyItemRangeInserted(originPos, itemList.size - 1)
        }
    }

    inner class SearchResultViewHolder(val binding: ItemRespositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.item = item
        }
    }
}