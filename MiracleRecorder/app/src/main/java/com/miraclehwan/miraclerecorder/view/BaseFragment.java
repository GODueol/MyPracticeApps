package com.miraclehwan.miraclerecorder.view;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<B extends ViewDataBinding, T extends ViewModel> extends Fragment {

    protected abstract int getLayout();

    protected abstract Class<T> setViewModelClass();

    protected B mBinding;
    protected T mViewModel;

    public B getBinding() {
        return mBinding;
    }

    public T getViewModel() {
        return mViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        mViewModel = ViewModelProviders.of(getActivity()).get(setViewModelClass());
        return mBinding.getRoot();
    }
}
