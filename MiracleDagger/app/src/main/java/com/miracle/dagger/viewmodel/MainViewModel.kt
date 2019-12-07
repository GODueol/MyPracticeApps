package com.miracle.dagger.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.miracle.dagger.repository.RandomUserRepo
import com.miracle.dagger.response.RandomUserResponse
import com.miracle.dagger.response.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val randomUserRepo: RandomUserRepo
) : ViewModel() {

    val userResult = ObservableField<List<Result>>()

    init {
        randomUserRepo
            .getRandomUser(10)
            .enqueue(object : Callback<RandomUserResponse> {
                override fun onResponse(
                    call: Call<RandomUserResponse>,
                    response: Response<RandomUserResponse>
                ) {
                    userResult.set(response.body()?.results)
                }

                override fun onFailure(call: Call<RandomUserResponse>, t: Throwable) {
                    Log.e("onFailure", t.message)
                }
            })
    }
}