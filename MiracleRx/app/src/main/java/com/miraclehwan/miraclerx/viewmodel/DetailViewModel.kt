package com.miraclehwan.miraclerx.viewmodel

import androidx.databinding.ObservableField
import com.miraclehwan.miraclerx.model.MovieInfo


class DetailViewModel() : BaseViewModel() {
    val movie : ObservableField<MovieInfo> = ObservableField()
}