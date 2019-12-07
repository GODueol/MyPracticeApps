package com.miracle.dagger.repository

import com.miracle.dagger.di.scope.ActivityScope
import com.miracle.dagger.network.Api
import dagger.Module
import dagger.Provides

@Module
class RandomUserModule {

    @ActivityScope
    @Provides
    fun provideRandomUserRepo(api : Api) : RandomUserRepo{
        return RandomUserRepo(api)
    }
}