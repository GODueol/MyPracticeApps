package com.miraclehwan.miraclegithub.viewmodel

import androidx.databinding.ObservableField
import com.miraclehwan.miraclegithub.network.response.Item

class RepositoryInfoVIewModel : BaseViewModel(){
    override fun inject() {}

    val item = ObservableField<Item>()
}
