package com.miraclehwan.miraclerecorder.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.miraclehwan.miraclerecorder.R;
import com.miraclehwan.miraclerecorder.databinding.ItemRecordefileBinding;
import com.miraclehwan.miraclerecorder.model.RecordeFile;

import java.util.List;

public class RecordeFileAdapter extends RecyclerView.Adapter<RecordeFileAdapter.RecodeFileHolder>{

    public interface OnItemClickListener{
        void onClick(int position);
    }

    private Context mContext;
    private List<RecordeFile> mRecordeFileList;
    private OnItemClickListener mOnItemClickListener;

    public void changeRecodeFileList(List<RecordeFile> recordeFileList){
        mRecordeFileList = recordeFileList;
        notifyDataSetChanged();
    }

    public RecordeFileAdapter(Context mContext, List<RecordeFile> mRecordeFileList, OnItemClickListener mOnItemClickListener) {
        this.mContext = mContext;
        this.mRecordeFileList = mRecordeFileList;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public RecodeFileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecodeFileHolder(ItemRecordefileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecodeFileHolder recodeFileHolder, int position) {
        recodeFileHolder.onBind(mRecordeFileList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecordeFileList.size();
    }

    public RecordeFile getRecordeFile(int position){
        return mRecordeFileList.get(position);
    }

    public void enableView(int position){
        mRecordeFileList.get(position).setPlay(true);
        notifyItemChanged(position);
    }

    public void disableView(int position){
        mRecordeFileList.get(position).setPlay(false);
        notifyItemChanged(position);
    }

    public class RecodeFileHolder extends RecyclerView.ViewHolder{

        private ItemRecordefileBinding mBinding;

        public RecodeFileHolder(ItemRecordefileBinding itemRecodefileBinding) {
            super(itemRecodefileBinding.getRoot());
            mBinding = itemRecodefileBinding;
        }

        public void onBind(RecordeFile recordeFile){
            mBinding.tvTitle.setText(recordeFile.getTitle());
            if (recordeFile.isPlay()){
                mBinding.clParent.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                mBinding.tvTitle.setTextColor(Color.WHITE);
            }else {
                mBinding.clParent.setBackgroundColor(Color.WHITE);
                mBinding.tvTitle.setTextColor(Color.BLACK);
            }
            mBinding.getRoot().setOnClickListener(v -> {
                mOnItemClickListener.onClick(getAdapterPosition());
            });
        }
    }
}
