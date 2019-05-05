package com.miraclehwan.miraclerecorder.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.miraclehwan.miraclerecorder.R;
import com.miraclehwan.miraclerecorder.adapter.RecordeFileAdapter;
import com.miraclehwan.miraclerecorder.adapter.SimpleDividerItemDecoration;
import com.miraclehwan.miraclerecorder.databinding.FragmentRecordFileBinding;
import com.miraclehwan.miraclerecorder.model.RecordeFile;
import com.miraclehwan.miraclerecorder.util.MHAudioTrack;
import com.miraclehwan.miraclerecorder.util.MHHandlerThread;
import com.miraclehwan.miraclerecorder.util.MHLog;
import com.miraclehwan.miraclerecorder.view.BaseFragment;
import com.miraclehwan.miraclerecorder.viewmodel.MainViewModel;

import java.util.List;

public class RecordFileFragment extends BaseFragment<FragmentRecordFileBinding, MainViewModel> {

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

    private MHHandlerThread mAudioPlayHandlerThread;
    private RecordeFileAdapter mRecordeFileAdapter;
    private MHAudioTrack mAudioTrack;

    private int mLastClickItemPosition = -1;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getBinding().rvRecodefile.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        initLiveData();
    }

    private void initLiveData(){
        getViewModel().getRecordeFileArray().observe(RecordFileFragment.this, recordeFileArray -> {
            if (recordeFileArray == null){
                return;
            }
            MHLog.e("파일사이즈 - " + recordeFileArray.length);
            List<RecordeFile> recordeFileList = getViewModel().getRecordeFileList(recordeFileArray);
            if (mRecordeFileAdapter == null){
                mAudioPlayHandlerThread = new MHHandlerThread("AUDIO-PLAY-WORKER");
                mRecordeFileAdapter = new RecordeFileAdapter(getActivity(), recordeFileList, position -> {
                    if (mLastClickItemPosition != -1){
                        mRecordeFileAdapter.disableView(mLastClickItemPosition);
                    }
                    mLastClickItemPosition = position;
                    mRecordeFileAdapter.enableView(mLastClickItemPosition);
                    if (mAudioTrack == null){
                        mAudioTrack = new MHAudioTrack(mRecordeFileAdapter.getRecordeFile(position).getPath());
                        mAudioTrack.playTrack();
                    }else{
                        mAudioPlayHandlerThread.getHandler().post(() -> mAudioTrack.changeTrack(mRecordeFileAdapter.getRecordeFile(position).getPath()));
                    }
                });
                getBinding().rvRecodefile.setAdapter(mRecordeFileAdapter);
            }else{
                mRecordeFileAdapter.changeRecodeFileList(recordeFileList);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAudioTrack != null){
            mAudioTrack.stopTrack();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mAudioTrack != null){
            mAudioTrack.release();
            mAudioTrack = null;
        }
        if (mAudioPlayHandlerThread != null){
            mAudioPlayHandlerThread.quit();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            return;
        }
        if (mAudioTrack == null){
            return;
        }
        mAudioTrack.stopTrack();
    }
}
