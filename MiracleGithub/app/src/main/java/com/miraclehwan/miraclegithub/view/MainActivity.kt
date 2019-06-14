package com.miraclehwan.miraclegithub.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miraclehwan.miraclegithub.R
import com.miraclehwan.miraclegithub.viewmodel.MainViewModel
import com.miraclehwan.miraclegithub.databinding.ActivityMainBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
