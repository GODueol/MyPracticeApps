package com.miraclehwan.miraclerecorder.view.activity;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.widget.Toast;

import com.miraclehwan.miraclerecorder.R;
import com.miraclehwan.miraclerecorder.adapter.PermissionAdapter;
import com.miraclehwan.miraclerecorder.anim.MHTransition;
import com.miraclehwan.miraclerecorder.databinding.ActivitySplashBeforeBinding;
import com.miraclehwan.miraclerecorder.util.MHPermission;
import com.miraclehwan.miraclerecorder.view.BaseActivity;
import com.miraclehwan.miraclerecorder.view.dialog.PermissionDeniedDialog;
import com.miraclehwan.miraclerecorder.viewmodel.SplashViewModel;

public class SplashActivity extends BaseActivity<ActivitySplashBeforeBinding, SplashViewModel> {

    private final int mPermissionRequestCode = 935;
    private final int PERMISSION_DENIED_REQUEST_CODE = 936;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash_before;
    }

    @Override
    protected Class<SplashViewModel> getViewModelClass() {
        return SplashViewModel.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBinding().lavSplash.addAnimatorListener(mSplashAnimatorListener);
        getBinding().lavSplash.playAnimation();
    }

    private Animator.AnimatorListener mSplashAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationCancel(Animator animation) {}
        @Override
        public void onAnimationRepeat(Animator animation) {}
        @Override
        public void onAnimationStart(Animator animation) {}
        @Override
        public void onAnimationEnd(Animator animation) {
            if (getViewModel().hasPermission(SplashActivity.this)){
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finishWithFade();
            }else{
                getBinding().rvPermissionList.setLayoutManager(new LinearLayoutManager(SplashActivity.this){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                getBinding().rvPermissionList.setAdapter(new PermissionAdapter(SplashActivity.this, getViewModel().getPermissionList()));

                getBinding().btnRequestPermission.setOnClickListener(v -> {
                    getViewModel().requestPermission(SplashActivity.this, mPermissionRequestCode);
                });

                TransitionManager.beginDelayedTransition(getBinding().clSplashBefore, getAutoTransition());
                ConstraintSet constraintAfterSet = new ConstraintSet();
                constraintAfterSet.clone(SplashActivity.this, R.layout.activity_splash_after);
                constraintAfterSet.applyTo(getBinding().clSplashBefore);
            }
        }
    };

    private Transition getAutoTransition(){
        MHTransition transition = new MHTransition(MHTransition.AUTO)
                .setDuration(1000)
                .setEndCallback(() -> {
                    getBinding().tvNoti.setTextWithFadeAnim("필수 권한을 허용해주세요.");

                    MHTransition permissionTransition = new MHTransition(MHTransition.AUTO)
                            .setDuration(1000);

                    TransitionManager.beginDelayedTransition(getBinding().clPermission, permissionTransition.getTransition());
                    ConstraintSet constraintAfterSet = new ConstraintSet();
                    constraintAfterSet.clone(getBinding().clPermission);
                    constraintAfterSet.connect(R.id.rv_permission_list, ConstraintSet.BOTTOM,
                            R.id.btn_request_permission, ConstraintSet.TOP);
                    constraintAfterSet.setVisibility(R.id.btn_request_permission, ConstraintSet.VISIBLE);
                    constraintAfterSet.applyTo(getBinding().clPermission);
                });
        return transition.getTransition();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (getViewModel().hasPermission(this)){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finishWithFade();
        }else{
            new PermissionDeniedDialog(this)
                    .setClickOkCallback(() -> {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, PERMISSION_DENIED_REQUEST_CODE);
                    })
                    .setClickNoCallback(() -> {
                        finishWithFade();
                    })
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != PERMISSION_DENIED_REQUEST_CODE){
            return;
        }
        if (getViewModel().hasPermission(this)){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finishWithFade();
        }else{
            finishWithFade();
        }
    }
}
