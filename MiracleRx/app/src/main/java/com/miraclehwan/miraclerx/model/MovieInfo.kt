package com.miraclehwan.miraclerx.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieInfo(val title: String, val imageUrl: String) : Parcelable
