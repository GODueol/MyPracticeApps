package com.miracle.dagger.network

import com.miracle.dagger.response.RandomUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/api")
    fun getRandomUser(@Query("results") count : Int): Call<RandomUserResponse>
}