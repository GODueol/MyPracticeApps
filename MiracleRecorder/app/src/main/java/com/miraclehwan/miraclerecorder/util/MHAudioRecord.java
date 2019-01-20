package com.miraclehwan.miraclerecorder.util;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;

public class MHAudioRecord {

    private int mAudioSource = MediaRecorder.AudioSource.MIC;
    private int mSampleRate = 44100;
    private int mChannalCount = AudioFormat.CHANNEL_IN_STEREO;
    private int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private int mBufferSize = AudioTrack.getMinBufferSize(mSampleRate, mChannalCount, mAudioFormat);

    private AudioRecord mAudioRecord;

    public MHAudioRecord() {
        mAudioRecord = new AudioRecord(mAudioSource, mSampleRate, mChannalCount, mAudioFormat, mBufferSize);
    }

    public boolean startRecord(){

        return false;
    }

    public MHAudioRecord setmAudioSource(int mAudioSource) {
        this.mAudioSource = mAudioSource;
        return this;
    }

    public MHAudioRecord setmSampleRate(int mSampleRate) {
        this.mSampleRate = mSampleRate;
        return this;
    }

    public MHAudioRecord setmChannalCount(int mChannalCount) {
        this.mChannalCount = mChannalCount;
        return this;
    }

    public MHAudioRecord setmAudioFormat(int mAudioFormat) {
        this.mAudioFormat = mAudioFormat;
        return this;
    }

    public MHAudioRecord setmBufferSize(int mBufferSize) {
        this.mBufferSize = mBufferSize;
        return this;
    }
}
