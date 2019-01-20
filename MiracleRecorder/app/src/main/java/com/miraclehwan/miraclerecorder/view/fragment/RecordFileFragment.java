package com.miraclehwan.miraclerecorder.view.fragment;

import android.os.Bundle;

import com.miraclehwan.miraclerecorder.R;
import com.miraclehwan.miraclerecorder.databinding.FragmentRecordBinding;
import com.miraclehwan.miraclerecorder.databinding.FragmentRecordFileBinding;
import com.miraclehwan.miraclerecorder.view.BaseFragment;
import com.miraclehwan.miraclerecorder.viewmodel.MainViewModel;

public class RecordFileFragment extends BaseFragment<FragmentRecordFileBinding, MainViewModel>{

    public static RecordFileFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecordFileFragment fragment = new RecordFileFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getLayout() {
        return R.layout.fragment_record_file;
    }

    @Override
    protected Class setViewModelClass() {
        return MainViewModel.class;
    }
}
