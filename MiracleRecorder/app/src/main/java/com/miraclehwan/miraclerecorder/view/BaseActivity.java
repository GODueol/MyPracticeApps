package com.miraclehwan.miraclerecorder.view;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.miraclehwan.miraclerecorder.R;

public abstract class BaseActivity<B extends ViewDataBinding, T extends ViewModel> extends AppCompatActivity {

    protected abstract int getLayout();
    protected abstract Class<T> getViewModelClass();

    protected B mBinding;
    protected T mViewModel;

    public B getBinding() {
        return mBinding;
    }
    public T getViewModel() {
        return mViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayout());
        mViewModel = ViewModelProviders.of(this).get(getViewModelClass());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void finishWithFade(){
        finish();
        overridePendingTransition(0, R.anim.fade_out);
    }
}
