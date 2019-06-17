package com.miraclehwan.miraclegithub.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.miraclegithub.R
import com.miraclehwan.miraclegithub.databinding.ActivityMainBinding
import com.miraclehwan.miraclegithub.util.Log
import com.miraclehwan.miraclegithub.viewmodel.MainViewModel

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
