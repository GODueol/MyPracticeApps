package com.miraclehwan.miraclegithub.di

import com.miraclehwan.miraclegithub.model.SearchRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun provideSearchRepository(): SearchRepository {
        return SearchRepository()
    }
}