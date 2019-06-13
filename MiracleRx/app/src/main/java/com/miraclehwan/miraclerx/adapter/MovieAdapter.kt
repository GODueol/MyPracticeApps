package com.miraclehwan.miraclerx.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.miraclerx.databinding.ItemMovieBinding
import com.miraclehwan.miraclerx.model.Movie
import com.miraclehwan.miraclerx.view.DetailActivity
import com.miraclehwan.miraclerx.view.MOVIE_TAG
import com.miraclehwan.miraclerx.view.MainActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    var movieList = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.onBInd(movieList[position])
    }

    fun setItem(movie: Movie) {
        movieList.add(movie)
        notifyItemInserted(movieList.size - 1)
    }

    inner class MovieHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val context = it.context
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra(MOVIE_TAG, movieList[adapterPosition])
                }
                val p1 = Pair(binding.ivBackground as View, "poster")
                val p2 = Pair(binding.vGray, "gray")
                val p3 = Pair(binding.tvTitle as View, "title")
                val option = ActivityOptionsCompat.makeSceneTransitionAnimation(context as MainActivity, p1, p2, p3)
                context.startActivity(intent, option.toBundle())
            }
        }

        fun onBInd(movie: Movie) {
            binding.movie = movie
        }
    }
}