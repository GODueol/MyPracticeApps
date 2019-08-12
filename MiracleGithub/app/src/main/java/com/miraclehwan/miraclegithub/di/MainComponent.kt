package com.miraclehwan.miraclegithub.di

import com.miraclehwan.miraclegithub.model.SearchRepository
import com.miraclehwan.miraclegithub.viewmodel.MainViewModel
import dagger.Component

@Component(modules = [(MainModule::class)])
interface MainComponent {
    fun inject(mainViewModel: MainViewModel)
}