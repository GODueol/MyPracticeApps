package com.miraclehwan.miraclerecorder.model;

public class RecordeFile {

    private String mTitle;
    private String mPath;
    private long mDuration;
    private boolean isPlay;

    public RecordeFile(String mTitle, String mPath, long mDuration, boolean isPlay) {
        this.mTitle = mTitle;
        this.mPath = mPath;
        this.mDuration = mDuration;
        this.isPlay = isPlay;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }
}
