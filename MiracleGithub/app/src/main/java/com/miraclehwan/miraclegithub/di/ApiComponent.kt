package com.miraclehwan.miraclegithub.di

import android.app.Application
import com.miraclehwan.miraclegithub.model.SearchRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class)])
interface ApiComponent {
    fun inject(application: Application)
    fun inject(searchRepository: SearchRepository)
}