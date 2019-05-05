package com.miraclehwan.miraclerecorder.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModel;

import com.miraclehwan.miraclerecorder.R;
import com.miraclehwan.miraclerecorder.model.Permission;
import com.miraclehwan.miraclerecorder.util.MHPermission;

import java.util.ArrayList;
import java.util.List;

public class SplashViewModel extends ViewModel {

    private MHPermission mPermission;

    private String[] mPermissionArray = new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public SplashViewModel() {
        super();
        mPermission = new MHPermission();
    }

    public boolean hasPermission(Activity activity) {
        return mPermission.hasPermission(activity, mPermissionArray);
    }

    public void requestPermission(Activity activity, int requestCode) {
        mPermission.requestPermission(activity, requestCode, mPermissionArray);
    }

    public List<Permission> getPermissionList() {
        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(new Permission(Manifest.permission.RECORD_AUDIO, R.drawable.ic_mic, "마이크 입력", "녹음을 위해 필요해요!"));
        permissionList.add(new Permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, R.drawable.ic_folder, "폴더 접근", "녹음파일 저장 및 읽기를 위해 필요해요!"));
        return permissionList;
    }
}
