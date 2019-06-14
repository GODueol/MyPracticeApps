package com.miraclehwan.miraclerx.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.miraclehwan.miraclerx.Constants.TAG
import com.miraclehwan.miraclerx.model.Movie
import com.miraclehwan.miraclerx.model.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers


class MainViewModel : BaseViewModel() {

    val movie = ObservableField<Movie>()
    val movieRepository = MovieRepository()

    init {
        getMovieFromServer()
    }

    fun getMovieFromServer() {
        addDisposable(
            movieRepository.getMovie()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieInfo ->
                    Log.e(TAG, movieInfo.toString())
                    movie.set(movieInfo)
                }, {
                    Log.e(TAG, "API Fail")
                })
        )
    }
}