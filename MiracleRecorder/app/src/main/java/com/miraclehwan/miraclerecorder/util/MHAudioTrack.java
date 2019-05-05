package com.miraclehwan.miraclerecorder.util;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaRecorder;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MHAudioTrack {

    private int mAudioSource = MediaRecorder.AudioSource.MIC;
    private int mSampleRate = 44100;
    private int mChannalCount = AudioFormat.CHANNEL_IN_STEREO;
    private int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private int mBufferSize = AudioTrack.getMinBufferSize(mSampleRate, mChannalCount, mAudioFormat);

    private AudioTrack mAudioTrack;
    private String mFilePath;
    private boolean isPlaying;

    public MHAudioTrack(String filePath) {
        mFilePath = filePath;
        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, mSampleRate, mChannalCount, mAudioFormat, mBufferSize, AudioTrack.MODE_STREAM);
    }

    public void playTrack() {
        new Thread(() -> {
            isPlaying = true;
            byte[] writeData = new byte[mBufferSize];
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(mFilePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            DataInputStream dis = new DataInputStream(fis);
            mAudioTrack.play();  // write 하기 전에 play 를 먼저 수행해 주어야 함

            while (isPlaying) {
                try {
                    int ret = dis.read(writeData, 0, mBufferSize);
                    if (ret <= 0) {
                        //종료
                        isPlaying = false;
                        break;
                    }
                    mAudioTrack.write(writeData, 0, ret); // AudioTrack 에 write 를 하면 스피커로 송출됨
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            mAudioTrack.stop();

            try {
                dis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "Play-Worker").start();
    }

    public void stopTrack() {
        isPlaying = false;
    }

    public void changeTrack(String path){
        if (isPlaying){
            stopTrack();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        mFilePath = path;
        playTrack();
    }

    public void release() {
        if (mAudioTrack != null) {
            mAudioTrack.release();
            mAudioTrack = null;
        }
    }
}
