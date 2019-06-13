package com.miraclehwan.miraclerx.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val title: String, val imageUrl: String, val overview: String, val genre: List<String>) : Parcelable
