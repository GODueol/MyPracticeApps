package com.miraclehwan.miraclerecorder.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.airbnb.lottie.LottieDrawable;
import com.miraclehwan.miraclerecorder.R;
import com.miraclehwan.miraclerecorder.databinding.FragmentRecordBinding;
import com.miraclehwan.miraclerecorder.util.MHAudioRecord;
import com.miraclehwan.miraclerecorder.view.BaseFragment;
import com.miraclehwan.miraclerecorder.viewmodel.MainViewModel;

public class RecordFragment extends BaseFragment<FragmentRecordBinding, MainViewModel> {

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

    private Thread mTimeUpdateThread;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBinding().lavRecode.setOnClickListener(v -> {
            if (getViewModel().isRecoding()){
                getViewModel().setRecoding(false);
                getViewModel().stopRecoding();
                mTimeUpdateThread.interrupt();
                getBinding().lavRecode.cancelAnimation();
                getBinding().lavRecode.setProgress(0.0f);
            }else{
                getViewModel().setRecoding(true);
                getViewModel().startRecoding();
                getBinding().lavRecode.playAnimation();
                mTimeUpdateThread = new Thread(() -> {
                    while (getViewModel().isRecoding()){
                        getActivity().runOnUiThread(() -> getBinding().tvTime.setText(getViewModel().getDurationTime()));
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            getActivity().runOnUiThread(() -> getBinding().tvTime.setText("아이콘을 눌러서 녹음을 시작하세요."));
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "TIME-UPDATE-WORKER");
                mTimeUpdateThread.start();
            }
        });

    }
}
