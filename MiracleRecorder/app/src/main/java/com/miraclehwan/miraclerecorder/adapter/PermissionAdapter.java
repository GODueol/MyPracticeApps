package com.miraclehwan.miraclerecorder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.miraclehwan.miraclerecorder.databinding.ItemPermissionBinding;
import com.miraclehwan.miraclerecorder.model.Permission;

import java.util.List;

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.PermissionViewHolder> {

    private Context mContext;
    private List<Permission> mPermissionList;

    public PermissionAdapter(Context mContext, List<Permission> mPermissionList) {
        this.mContext = mContext;
        this.mPermissionList = mPermissionList;
    }

    @NonNull
    @Override
    public PermissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PermissionViewHolder(ItemPermissionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionViewHolder holder, int position) {
        holder.onBind(mPermissionList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPermissionList.size();
    }

    public class PermissionViewHolder extends RecyclerView.ViewHolder {

        public ItemPermissionBinding mBinding;

        public PermissionViewHolder(ItemPermissionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void onBind(Permission permission) {
            Glide.with(mContext)
                    .load(permission.getIcon())
                    .into(mBinding.ivIcon);
            mBinding.tvTitle.setText(permission.getTitle());
            mBinding.tvInfo.setText(permission.getInfo());
        }
    }
}
