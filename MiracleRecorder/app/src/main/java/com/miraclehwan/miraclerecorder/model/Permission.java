package com.miraclehwan.miraclerecorder.model;

public class Permission {

    private String mPermission;
    private int mIcon;
    private String mTitle;
    private String mInfo;

    public Permission(String mPermission, int mIcon, String mTitle, String mInfo) {
        this.mPermission = mPermission;
        this.mIcon = mIcon;
        this.mTitle = mTitle;
        this.mInfo = mInfo;
    }

    public String getPermission() {
        return mPermission;
    }

    public int getIcon() {
        return mIcon;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getInfo() {
        return mInfo;
    }
}
