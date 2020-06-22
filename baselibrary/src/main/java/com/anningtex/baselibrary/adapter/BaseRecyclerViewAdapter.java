package com.anningtex.baselibrary.adapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseRecyclerViewAdapter
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/22 9:52
 */
public abstract class BaseRecyclerViewAdapter<D, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public List<D> mData = new ArrayList<>();

    public void setData(List<D> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public List<D> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void appendItems(List<D> items) {
        if (items != null && (items.size() != 0) && mData != null) {
            mData.addAll(items);
            notifyDataSetChanged();
        }
    }

    public void appendItems(List<D> items, int index) {
        if (items != null && (items.size() != 0) && mData != null) {
            mData.addAll(index, items);
            notifyDataSetChanged();
        }
    }

    public void removeItem(D item) {
        mData.remove(item);
        notifyDataSetChanged();
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
            notifyDataSetChanged();
        }
    }
}
