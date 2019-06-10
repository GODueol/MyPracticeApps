package com.miraclehwan.miraclerx.view

import com.miraclehwan.miraclerx.R
import com.miraclehwan.miraclerx.databinding.ActivityDetailBinding
import com.miraclehwan.miraclerx.model.MovieInfo
import com.miraclehwan.miraclerx.viewmodel.DetailViewModel

const val MOVIE_TAG = "movie"

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(){
    override fun getLayoutRes(): Int {
        return R.layout.activity_detail
    }

    override fun getViewModelClass(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun setViewModelToDataBinding() {
        binding.vm = viewModel
        val movie = intent.getParcelableExtra<MovieInfo>(MOVIE_TAG)
        viewModel.movie.set(movie)
    }

}