package com.miraclehwan.translate.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miraclehwan.translate.R
import com.miraclehwan.translate.databinding.ActivityMainBinding
import com.miraclehwan.translate.viewmodel.MainViewModel
import com.miraclehwan.translate.viewmodel.TranslateViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(){

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }
    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun initView() {}

    override fun setViewModelToDataBinding() {
        binding.translateVM = mTranslateViewModel
    }

    private val mTranslateViewModel by lazy { ViewModelProviders.of(this).get(TranslateViewModel::class.java) }
}
