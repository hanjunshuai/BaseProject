package com.anningtex.baselibrary.listener;

import com.anningtex.baselibrary.adapter.GroupedRecyclerViewAdapter;
import com.anningtex.baselibrary.base.BaseViewHolder;

/**
 * @ClassName: OnLongChildClickListener
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/22 10:40
 */
public interface OnLongChildClickListener {
    void onLongChildClick(GroupedRecyclerViewAdapter groupedRecyclerViewAdapter, BaseViewHolder holder, int groupPosition, int childPosition);
}
