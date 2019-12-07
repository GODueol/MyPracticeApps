package com.miracle.dagger

import com.miracle.dagger.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(
            this,
            "https://randomuser.me/"
        ).apply {
            inject(this@MyApplication)
        }
    }
}