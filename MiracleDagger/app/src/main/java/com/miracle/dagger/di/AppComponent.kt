package com.miracle.dagger.di

import android.content.Context
import com.miracle.dagger.MyApplication
import com.miracle.dagger.network.BaseURL
import com.miracle.dagger.network.RetrofitModule
import com.miracle.dagger.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        RetrofitModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {

    override fun inject(myApplication: MyApplication)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            @BindsInstance @BaseURL baseUrl: String
        ): AppComponent
    }
}