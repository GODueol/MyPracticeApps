package com.miraclehwan.miraclerx.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.miraclerx.databinding.ItemMovieBinding
import com.miraclehwan.miraclerx.model.MovieInfo
import com.miraclehwan.miraclerx.view.DetailActivity
import com.miraclehwan.miraclerx.view.MOVIE_TAG

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    var movieList = ArrayList<MovieInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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
            notifyItemInserted(movieList.size - 1)
        }
    }

    inner class MovieHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val context = it.context
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra(MOVIE_TAG, movieList[adapterPosition])
                }
                context.startActivity(intent)
            }
        }

        fun onBInd(movie: MovieInfo) {
            binding.movie = movie
        }
    }
}