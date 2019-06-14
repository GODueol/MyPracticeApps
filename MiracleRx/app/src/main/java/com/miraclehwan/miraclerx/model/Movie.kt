package com.miraclehwan.miraclerx.model

import android.os.Parcelable
import com.miraclehwan.miraclerx.network.response.Genre
import com.miraclehwan.miraclerx.network.response.Result
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val title: String, val imageUrl: String, val overview: String, val genre: List<String>) : Parcelable {
    constructor(result: Result, genreList: List<String>) : this(
        result.title,
        result.backdropPath,
        result.overview,
        genreList
    )
}