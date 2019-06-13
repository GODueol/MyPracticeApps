package com.miraclehwan.miraclerx.viewmodel

import androidx.databinding.ObservableField
import com.miraclehwan.miraclerx.model.Movie


class DetailViewModel : BaseViewModel() {
    val movie = ObservableField<Movie>()
}