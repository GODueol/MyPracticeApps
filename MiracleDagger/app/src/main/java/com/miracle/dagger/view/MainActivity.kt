package com.miracle.dagger.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.miracle.dagger.R
import com.miracle.dagger.databinding.ActivityMainBinding
import com.miracle.dagger.viewmodel.MainViewModel
import com.miracle.dagger.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            vm = ViewModelProviders.of(this@MainActivity, viewModelFactory)[MainViewModel::class.java]
        }
    }
}
