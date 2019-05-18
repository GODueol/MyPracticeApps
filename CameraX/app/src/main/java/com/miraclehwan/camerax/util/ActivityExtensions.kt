package com.miraclehwan.camerax.util

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

fun Activity.hasPermission(permissionList: Array<String>) : Boolean{
    for (permission in permissionList){
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            return false
        }
    }
    return true
}

fun Activity.getFolderRootPath() : File {
    return externalMediaDirs.first()
}