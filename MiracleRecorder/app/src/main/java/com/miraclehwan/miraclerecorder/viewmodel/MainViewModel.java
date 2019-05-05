package com.miraclehwan.miraclerecorder.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.miraclehwan.miraclerecorder.model.RecordeFile;
import com.miraclehwan.miraclerecorder.util.MHAudioRecord;
import com.miraclehwan.miraclerecorder.util.MHPath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainViewModel extends ViewModel {

    private MHAudioRecord mAudioRecord;
    private MHPath mPath;

    private long mRecodingStartTime;
    private boolean mIsRecoding;

    private MutableLiveData<File[]> mRecordeFileArray;
    public MutableLiveData<File[]> getRecordeFileArray() {
        if (mRecordeFileArray == null) {
            mRecordeFileArray = new MutableLiveData<>();
        }
        return mRecordeFileArray;
    }

    public MainViewModel() {
        super();
        mPath = new MHPath();
        mPath.checkDownloadFolder();
        mAudioRecord = new MHAudioRecord(mPath);
        updateRecodeFileList();
    }

    private void updateRecodeFileList(){
        getRecordeFileArray().postValue(mPath.getRecodeFileArray());
    }

    public void startRecoding(){
        mRecodingStartTime = System.currentTimeMillis();
        mAudioRecord.startRecord();
    }

    public void stopRecoding(){
        mAudioRecord.stopRecord();
        updateRecodeFileList();
    }

    public boolean isRecoding() {
        return mIsRecoding;
    }

    public void setRecoding(boolean mIsRecoding) {
        this.mIsRecoding = mIsRecoding;
    }

    public String getDurationTime(){
        long duration = System.currentTimeMillis() - mRecodingStartTime;
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(duration) % TimeUnit.MINUTES.toSeconds(1));
    }

    public List<RecordeFile> getRecordeFileList(File[] recordeFileArray){
        List<RecordeFile> recordeFileList = new ArrayList<>();
        for (int i = 0; i < recordeFileArray.length; i++) {
            recordeFileList.add(new RecordeFile(recordeFileArray[i].getAbsolutePath().replace("/storage/emulated/0/MiracleRecorder/", "").replace(".pcm", ""),
                    recordeFileArray[i].getAbsolutePath(),
                    0,
                    false));
        }
        return recordeFileList;
    }
}
