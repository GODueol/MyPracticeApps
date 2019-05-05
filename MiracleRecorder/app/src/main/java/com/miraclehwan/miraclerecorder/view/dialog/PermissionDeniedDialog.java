package com.miraclehwan.miraclerecorder.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.miraclehwan.miraclerecorder.R;
import com.miraclehwan.miraclerecorder.databinding.DialogPermissionDeniedBinding;
import com.miraclehwan.miraclerecorder.view.BaseDialog;

public class PermissionDeniedDialog extends BaseDialog<DialogPermissionDeniedBinding> {

    @FunctionalInterface
    public interface IClickOk {
        void onCallback();
    }

    @FunctionalInterface
    public interface IClickNo {
        void onCallback();
    }

    private IClickOk iClickOk;
    private IClickNo iClickNo;

    @Override
    protected int getLayout() {
        return R.layout.dialog_permission_denied;
    }

    public PermissionDeniedDialog(@NonNull Context context) {
        super(context);
        getBinding().btnY.setOnClickListener(v -> {
            if (iClickOk != null) {
                iClickOk.onCallback();
            }
        });
        getBinding().btnN.setOnClickListener(v -> {
            if (iClickNo != null) {
                iClickNo.onCallback();
            }
        });
    }

    public PermissionDeniedDialog setClickOkCallback(IClickOk iClickOk) {
        this.iClickOk = iClickOk;
        return this;
    }

    public PermissionDeniedDialog setClickNoCallback(IClickNo iClickNo) {
        this.iClickNo = iClickNo;
        return this;
    }
}
