package com.miraclehwan.miraclerx.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.miraclehwan.miraclerx.Constants.TAG
import com.miraclehwan.miraclerx.model.Movie
import com.miraclehwan.miraclerx.model.getMovie
import io.reactivex.android.schedulers.AndroidSchedulers


class MainViewModel : BaseViewModel() {

    val movie = ObservableField<Movie>()

    init {
        getMovieFromServer()
    }

    fun getMovieFromServer() {
        addDisposable(
            getMovie()
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