package com.anningtex.baseproject.adapter;

import com.anningtex.baselibrary.base.BaseViewHolder;
import com.anningtex.baselibrary.base.GroupedRecyclerViewAdapter;
import com.anningtex.baseproject.R;
import com.anningtex.baseproject.entity.GroupEntity;

import java.util.List;

/**
 * @ClassName: GroupAdapter
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/20 17:04
 */
public class GroupAdapter extends GroupedRecyclerViewAdapter<GroupEntity> {

    @Override
    public int getGroupCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (isExpand(groupPosition)) {
            return 0;
        }
        List<GroupEntity.Child> childs = mData.get(groupPosition).getT();
        return childs != null ? childs.size() : 0;
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.item_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_child;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        holder.setText(R.id.item_tv_header, mData.get(groupPosition).header);
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        holder.setText(R.id.item_tv_child, mData.get(groupPosition).getT().get(childPosition).getChildName());
    }
}
