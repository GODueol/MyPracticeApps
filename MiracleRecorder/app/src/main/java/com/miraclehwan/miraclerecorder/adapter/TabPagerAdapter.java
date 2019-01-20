package com.miraclehwan.miraclerecorder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.miraclehwan.miraclerecorder.view.fragment.RecordFileFragment;
import com.miraclehwan.miraclerecorder.view.fragment.RecordFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private int mTabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        mTabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return RecordFragment.newInstance();
            case 1:
                return RecordFileFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
