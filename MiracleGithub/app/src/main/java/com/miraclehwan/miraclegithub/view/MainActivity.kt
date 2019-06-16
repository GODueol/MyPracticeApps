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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager
                val totalItemCount = layoutManager?.itemCount ?: 0
                val lastVisibleItem =
                    layoutManager?.let { (layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 1 } ?: 0

                Log.e("size log | $totalItemCount / $lastVisibleItem")

                if (!viewModel.loading && totalItemCount!! <= lastVisibleItem!!) {
                    viewModel.currentPage++
                    viewModel.pageSubject.onNext(viewModel.currentPage)
                }
            }
        })
    }
}
