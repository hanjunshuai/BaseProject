package com.anningtex.baselibrary.listener;

import com.anningtex.baselibrary.adapter.GroupedRecyclerViewAdapter;
import com.anningtex.baselibrary.base.BaseViewHolder;

/**
 * @ClassName: OnLongHeaderClickListener
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/22 10:04
 */
public interface OnLongHeaderClickListener {
    void onLongHeaderClick(GroupedRecyclerViewAdapter groupedRecyclerViewAdapter, BaseViewHolder holder, int groupPosition);
}
