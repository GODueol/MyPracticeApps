package com.miraclehwan.miraclegithub.viewmodel

import androidx.databinding.ObservableField
import com.miraclehwan.miraclegithub.network.response.Item

class RepositoryInfoVIewModel : BaseViewModel(){
    val item = ObservableField<Item>()
}
