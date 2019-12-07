package com.miracle.dagger

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miracle.dagger.adapter.RandomUserAdapter
import com.miracle.dagger.response.Result

class BindingAdapter {
    companion object {

        @JvmStatic
        @BindingAdapter("setImageWithUrl")
        fun setImageWithURL(view: ImageView, url: String?) {
            if (url == null) {
                return
            }
            Glide.with(view)
                .load(url)
                .into(view)
        }

        @JvmStatic
        @BindingAdapter("setUserItem")
        fun setUserItemToAdapter(view: RecyclerView, item: List<Result>?) {
            if (item == null) {
                return
            }
            val adapter = view.adapter as? RandomUserAdapter ?: RandomUserAdapter().apply {
                view.adapter = this
            }
            adapter.item = item
        }
    }
}