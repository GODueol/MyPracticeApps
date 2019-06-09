package com.miraclehwan.miraclerx.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.miraclerx.databinding.ItemMovieBinding
import com.miraclehwan.miraclerx.model.MovieInfo

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    var movieList = ArrayList<MovieInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.onBInd(movieList[position])
    }

    fun setItem(movieList: List<MovieInfo>) {
        this.movieList = movieList as ArrayList<MovieInfo>
        if (movieList.size == 1) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeChanged(0, movieList.size - 1)
        }
    }

    inner class MovieHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBInd(movie: MovieInfo) {
            binding.movie = movie
        }
    }
}