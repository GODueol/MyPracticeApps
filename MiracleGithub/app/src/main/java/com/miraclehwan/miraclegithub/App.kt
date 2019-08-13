package com.miraclehwan.miraclegithub

import android.app.Application
import com.miraclehwan.miraclegithub.di.ApiComponent
import com.miraclehwan.miraclegithub.di.DaggerApiComponent

class App : Application() {

    companion object {
        lateinit var mInstance: App
            private set
    }

    lateinit var mDaggerApiComponent: ApiComponent

    override fun onCreate() {
        super.onCreate()
        mInstance = this;
        mDaggerApiComponent = DaggerApiComponent.create()
    }
}