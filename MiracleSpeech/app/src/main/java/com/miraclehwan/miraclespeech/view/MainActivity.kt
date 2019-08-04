package com.miraclehwan.miraclespeech.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager
import com.miraclehwan.miraclegithub.util.Log
import com.miraclehwan.miraclespeech.R
import com.miraclehwan.miraclespeech.databinding.ActivityMainBinding
import com.miraclehwan.miraclespeech.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun setViewModelToDataBinding() {
        mBinding.vm = mViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkAndRequestPermission(Manifest.permission.RECORD_AUDIO)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SpeechRecognizerManager.getInstance().finalizeLibrary();
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != PERMISSION_REQUEST_CODE) {
            return
        }
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                finish()
                return
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkAndRequestPermission(Manifest.permission.RECORD_AUDIO)
        }
    }

    private val PERMISSION_REQUEST_CODE = 9999;

    @RequiresApi(Build.VERSION_CODES.M)
    fun Context.checkAndRequestPermission(permission: String) {
        val hasPermission = (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
        if (hasPermission) {
            SpeechRecognizerManager.getInstance().initializeLibrary(this)
        } else {
            requestPermissions(listOf(permission).toTypedArray(), PERMISSION_REQUEST_CODE)
        }
    }
}
