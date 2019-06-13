package com.miraclehwan.miraclerx

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miraclehwan.miraclerx.Constants.BASE_IMAGE_URL
import com.miraclehwan.miraclerx.adapter.MovieAdapter
import com.miraclehwan.miraclerx.model.Movie
import me.gujun.android.taggroup.TagGroup

object Extensions {

    @JvmStatic
    @BindingAdapter("setItem")
    fun setMovieItem(view: RecyclerView, movie: Movie?) {
        if (movie == null) {
            return
        }
        val adapter = view.adapter as? MovieAdapter ?: MovieAdapter().apply { view.adapter = this }
        adapter.setItem(movie)
    }

    @JvmStatic
    @BindingAdapter("setImageUsingGlide")
    fun setImageUsingGlide(view: ImageView, url: String?) {
        if (url == null) {
            return
        }
        Glide.with(view)
            .load(BASE_IMAGE_URL + url)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("setGenre")
    fun setGenre(view: TagGroup, genre: List<String>?) {
        if (genre == null) {
            return
        }
        view.setTags(genre)
    }
}