package com.miraclehwan.miraclerx.view

import com.miraclehwan.miraclerx.R
import com.miraclehwan.miraclerx.databinding.ActivityMainBinding
import com.miraclehwan.miraclerx.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun setViewModelToDataBinding() {
        binding.vm = viewModel
    }
}
