package com.miraclehwan.miraclerecorder.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.miraclehwan.miraclerecorder.MyApplication;
import com.miraclehwan.miraclerecorder.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MHPath {

    private final String mRecodeFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + MyApplication.getInstance().getResources().getString(R.string.app_name) + "/";

    public void checkDownloadFolder() {
        String path = mRecodeFilePath;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        MyApplication.getInstance().sendBroadcast(intent);
    }

    public String getNewFilePath() {
        String path = mRecodeFilePath + new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()) + ".pcm";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        MyApplication.getInstance().sendBroadcast(intent);
        MHLog.e(path);
        return path;
    }

    public File[] getRecodeFileArray() {
        File directory = new File(mRecodeFilePath);
        return directory.listFiles();
    }
}
