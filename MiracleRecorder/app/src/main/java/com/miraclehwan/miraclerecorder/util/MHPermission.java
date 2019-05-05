package com.miraclehwan.miraclerecorder.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MHPermission {

    public boolean hasPermission(Activity activity, String[] permissionArray) {
        for (int i = 0; i < permissionArray.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, permissionArray[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void requestPermission(Activity activity, int requestCode, String[] permissionArray) {
        List<String> permissions = new ArrayList<>();
        for (int i = 0; i < permissionArray.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, permissionArray[i]) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(permissionArray[i]);
            }
        }
        if (permissions.size() > 0) {
            String[] needPermissionArray = new String[permissions.size()];
            needPermissionArray = permissions.toArray(needPermissionArray);
            ActivityCompat.requestPermissions(activity, needPermissionArray, requestCode);
        }
    }
}
