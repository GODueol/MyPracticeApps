package com.miraclehwan.miraclerecorder.view.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miraclehwan.miraclerecorder.R;
import com.miraclehwan.miraclerecorder.adapter.TabPagerAdapter;
import com.miraclehwan.miraclerecorder.databinding.ActivityMainBinding;
import com.miraclehwan.miraclerecorder.view.BaseActivity;
import com.miraclehwan.miraclerecorder.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private TabPagerAdapter mTabAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBinding().tl.addTab(getBinding().tl.newTab().setText("녹음"));
        getBinding().tl.addTab(getBinding().tl.newTab().setText("녹음파일"));
        getBinding().tl.setTabGravity(TabLayout.GRAVITY_FILL);

        mTabAdapter = new TabPagerAdapter(getSupportFragmentManager(), getBinding().tl.getTabCount());
        getBinding().vp.setAdapter(mTabAdapter);
        getBinding().vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(getBinding().tl));

        getBinding().tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getBinding().vp.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }
}
