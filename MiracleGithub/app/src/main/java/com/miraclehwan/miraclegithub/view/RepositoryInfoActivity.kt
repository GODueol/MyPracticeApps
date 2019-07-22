package com.miraclehwan.miraclegithub.view

import com.miraclehwan.miraclegithub.R
import com.miraclehwan.miraclegithub.databinding.ActivityRepositoryInfoBinding;
import com.miraclehwan.miraclegithub.network.response.Item
import com.miraclehwan.miraclegithub.viewmodel.RepositoryInfoVIewModel

class RepositoryInfoActivity : BaseActivity<ActivityRepositoryInfoBinding, RepositoryInfoVIewModel>(){
    override fun getLayoutRes(): Int {
        return R.layout.activity_repository_info
    }

    override fun getViewModelClass(): Class<RepositoryInfoVIewModel> {
        return RepositoryInfoVIewModel::class.java
    }

    override fun setViewModelToDataBinding() {
        binding.vm = viewModel
        intent.getParcelableExtra<Item>("item")?.let { viewModel.item.set(it) }
    }
}
