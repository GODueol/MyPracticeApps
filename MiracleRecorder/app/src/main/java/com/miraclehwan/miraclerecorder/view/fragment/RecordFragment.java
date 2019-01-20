package com.miraclehwan.miraclerecorder.view.fragment;

import android.os.Bundle;

import com.miraclehwan.miraclerecorder.R;
import com.miraclehwan.miraclerecorder.databinding.FragmentRecordBinding;
import com.miraclehwan.miraclerecorder.view.BaseFragment;
import com.miraclehwan.miraclerecorder.viewmodel.MainViewModel;

public class RecordFragment extends BaseFragment<FragmentRecordBinding, MainViewModel>{

    public static RecordFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecordFragment fragment = new RecordFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getLayout() {
        return R.layout.fragment_record;
    }

    @Override
    protected Class setViewModelClass() {
        return MainViewModel.class;
    }


}
