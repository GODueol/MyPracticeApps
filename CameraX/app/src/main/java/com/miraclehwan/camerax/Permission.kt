package com.miraclehwan.camerax

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Activity.hasPermission(permissionList: Array<String>) : Boolean{
    for (permission in permissionList){
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            return false
        }
    }
    return true
}

fun Activity.requestMyPermission(requestCode: Int, permissionList: Array<String>){
    var requestPermissionList = ArrayList<String>()
    for (permission in permissionList){
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED){
            requestPermissionList.add(permission)
        }
    }
    if (requestPermissionList.size > 0){
        ActivityCompat.requestPermissions(this, requestPermissionList.toTypedArray(), requestCode)
    }
}