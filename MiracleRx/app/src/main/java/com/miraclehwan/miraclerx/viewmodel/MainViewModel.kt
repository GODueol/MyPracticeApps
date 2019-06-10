package com.miraclehwan.miraclerx.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.databinding.ObservableArrayList
import com.miraclehwan.miraclerx.Constants.TAG
import com.miraclehwan.miraclerx.model.MovieInfo
import com.miraclehwan.miraclerx.model.getPopularityMovie
import com.miraclehwan.miraclerx.view.DetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers


class MainViewModel : BaseViewModel() {

    val movieList = ObservableArrayList<MovieInfo>()

    init{
        getMovieFromServer()
    }

    fun getMovieFromServer() {
        addDisposable(
            getPopularityMovie()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieInfo ->
                    Log.e(TAG, movieInfo.toString())
                    movieList.add(movieInfo)
                }, {
                    Log.e(TAG, "API Fail")
                })
        )
    }
}