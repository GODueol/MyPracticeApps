package com.miraclehwan.miraclegithub.network

import com.miraclehwan.miraclegithub.di.DaggerApiComponent
import javax.inject.Inject

class RetrofitClient {
    @Inject
    lateinit var getApi: Api

    init {
        DaggerApiComponent.create().inject(this)
    }
}