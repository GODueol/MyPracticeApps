package com.miraclehwan.miraclerx

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miraclehwan.miraclerx.Constants.BASE_IMAGE_URL
import com.miraclehwan.miraclerx.adapter.MovieAdapter
import com.miraclehwan.miraclerx.model.MovieInfo

object Extensions {

    @JvmStatic
    @BindingAdapter("setItem")
    fun setMovieItem(view: RecyclerView, movieList: List<MovieInfo>) {
        val adapter = view.adapter as? MovieAdapter ?: MovieAdapter().apply { view.adapter = this }
        adapter.setItem(movieList)
    }

    @JvmStatic
    @BindingAdapter("setImageUsingGlide")
    fun setImageUsingGlide(view: ImageView, url : String?){
        if (url == null){
            return
        }
        Glide.with(view)
            .load(BASE_IMAGE_URL + url)
            .into(view);
        Log.e("daehwan", BASE_IMAGE_URL + url)
    }
}