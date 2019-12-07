package com.miracle.dagger.di

import com.miracle.dagger.di.scope.ActivityScope
import com.miracle.dagger.repository.RandomUserModule
import com.miracle.dagger.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [RandomUserModule::class])
    abstract fun bindMainActivity() : MainActivity
}