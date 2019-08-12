package com.miraclehwan.miraclegithub.di

import com.miraclehwan.miraclegithub.network.Api
import com.miraclehwan.miraclegithub.network.RetrofitClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class)])
interface ApiComponent {
    fun inject(retrofitClient: RetrofitClient)
}