package com.anningtex.baselibrary.listener;

import com.anningtex.baselibrary.adapter.GroupedRecyclerViewAdapter;
import com.anningtex.baselibrary.base.BaseViewHolder;

/**
 * @ClassName: OnFooterClickListener
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/22 10:39
 */
public interface OnFooterClickListener {
    void onFooterClick(GroupedRecyclerViewAdapter groupedRecyclerViewAdapter, BaseViewHolder holder, int groupPosition);
}
