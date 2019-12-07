package com.miracle.dagger.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miracle.dagger.databinding.ItemRandomUserBinding
import com.miracle.dagger.response.Result

class RandomUserAdapter : RecyclerView.Adapter<RandomUserAdapter.RandomUserViewHodler>() {

    var item: List<Result> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomUserViewHodler {
        return RandomUserViewHodler(
            ItemRandomUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: RandomUserViewHodler, position: Int) {
        holder.onBind(item[position])
    }

    inner class RandomUserViewHodler(private val binding: ItemRandomUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(result: Result) {
            binding.user = result
        }
    }
}