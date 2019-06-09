package com.miraclehwan.miraclerx

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.miraclerx.adapter.MovieAdapter
import com.miraclehwan.miraclerx.model.MovieInfo

object Extensions {

    @JvmStatic
    @BindingAdapter("setItem")
    fun setMovieItem(view: RecyclerView, movieList: List<MovieInfo>) {
        val adapter = view.adapter as? MovieAdapter ?: MovieAdapter().apply { view.adapter = this }
        adapter.setItem(movieList)
    }
}