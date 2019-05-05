package com.miraclehwan.miraclerecorder.util;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MHAudioRecord {

    private MHPath mPath;

    private int mAudioSource = MediaRecorder.AudioSource.MIC;
    private int mSampleRate = 44100;
    private int mChannalCount = AudioFormat.CHANNEL_IN_STEREO;
    private int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private int mBufferSize = AudioTrack.getMinBufferSize(mSampleRate, mChannalCount, mAudioFormat);

    private AudioRecord mAudioRecord;
    private boolean isRecording;

    public MHAudioRecord(MHPath path) {
        mPath = path;
        mAudioRecord = new AudioRecord(mAudioSource, mSampleRate, mChannalCount, mAudioFormat, mBufferSize);
    }

    public void startRecord() {
        isRecording = true;
        if (mAudioRecord == null) {
            mAudioRecord = new AudioRecord(mAudioSource, mSampleRate, mChannalCount, mAudioFormat, mBufferSize);
        }
        mAudioRecord.startRecording();
        new Thread(() -> {
            byte[] readData = new byte[mBufferSize];
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mPath.getNewFilePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (isRecording) {
                int ret = mAudioRecord.read(readData, 0, mBufferSize);  //  AudioRecord의 read 함수를 통해 pcm data 를 읽어옴
                MHLog.e("read bytes is " + ret);

                try {
                    fos.write(readData, 0, mBufferSize);    //  읽어온 readData 를 파일에 write 함
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            mAudioRecord.stop();

            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "RECODE-WORKER").start();
    }

    public void stopRecord() {
        isRecording = false;
    }

    public void releaseRecord() {
        if (mAudioRecord != null) {
            mAudioRecord.release();
            mAudioRecord = null;
        }
    }

    public MHAudioRecord setAudioSource(int mAudioSource) {
        this.mAudioSource = mAudioSource;
        return this;
    }

    public MHAudioRecord setSampleRate(int mSampleRate) {
        this.mSampleRate = mSampleRate;
        return this;
    }

    public MHAudioRecord setChannalCount(int mChannalCount) {
        this.mChannalCount = mChannalCount;
        return this;
    }

    public MHAudioRecord setAudioFormat(int mAudioFormat) {
        this.mAudioFormat = mAudioFormat;
        return this;
    }

    public MHAudioRecord setBufferSize(int mBufferSize) {
        this.mBufferSize = mBufferSize;
        return this;
    }
}
