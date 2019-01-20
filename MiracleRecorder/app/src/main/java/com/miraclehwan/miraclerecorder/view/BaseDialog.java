package com.miraclehwan.miraclerecorder.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.miraclehwan.miraclerecorder.MyApplication;
import com.miraclehwan.miraclerecorder.R;

public abstract class BaseDialog<B extends ViewDataBinding> extends Dialog {

    protected abstract int getLayout();

    protected B mBinding;
    public B getBinding() {
        return mBinding;
    }

    public BaseDialog(@NonNull Context context) {
        super(context);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getLayout(), null, false);
        setContentView(mBinding.getRoot());
        setDialog();
    }

    protected void setDialog(){
        WindowManager.LayoutParams lpWindow = getWindow().getAttributes();

        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.width = (int) (MyApplication.getInstance().getDisplayMetrics().widthPixels * 0.8f);
        lpWindow.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lpWindow.dimAmount = 0.8f;
        if (getWindow() != null) {
            getWindow().setAttributes(lpWindow);
        }
        setCancelable(false);
    }
}
