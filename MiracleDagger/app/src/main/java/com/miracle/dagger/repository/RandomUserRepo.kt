package com.miracle.dagger.repository

import com.miracle.dagger.network.Api
import com.miracle.dagger.response.RandomUserResponse
import retrofit2.Call
import javax.inject.Inject

class RandomUserRepo @Inject constructor(
    val api: Api
) {

    fun getRandomUser(count: Int): Call<RandomUserResponse> {
        return api.getRandomUser(count)
    }
}