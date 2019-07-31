package com.miraclehwan.miraclespeech.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<VDB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int
    abstract fun getViewModelClass(): Class<VM>

    abstract fun setViewModelToDataBinding()

    val mBinding by lazy { DataBindingUtil.setContentView(this, getLayoutRes()) as VDB }
    val mViewModel by lazy { ViewModelProviders.of(this).get(getViewModelClass()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModelToDataBinding()
    }
}