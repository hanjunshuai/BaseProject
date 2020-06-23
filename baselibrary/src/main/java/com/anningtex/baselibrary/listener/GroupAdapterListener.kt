package com.anningtex.baselibrary.listener

import com.anningtex.baselibrary.adapter.GroupedRecyclerViewAdapter
import com.anningtex.baselibrary.base.BaseViewHolder

/**
 *
 * @ClassName:      GroupAdapterListener.kt
 * @Description:     Group 适配器点击事件
 * @Author:         alvis
 * @CreateDate:     2020/6/23 17:40
 */
interface OnChildClickListener {
    fun onChildClick(groupedRecyclerViewAdapter: GroupedRecyclerViewAdapter<*>?, holder: BaseViewHolder<*>?, groupPosition: Int, childPosition: Int)
}

interface OnLongChildClickListener {
    fun onLongChildClick(groupedRecyclerViewAdapter: GroupedRecyclerViewAdapter<*>?, holder: BaseViewHolder<*>?, groupPosition: Int, childPosition: Int)
}

interface OnFooterClickListener {
    fun onFooterClick(groupedRecyclerViewAdapter: GroupedRecyclerViewAdapter<*>?, holder: BaseViewHolder<*>?, groupPosition: Int)
}

interface OnHeaderClickListener {
    fun onHeaderClick(groupedRecyclerViewAdapter: GroupedRecyclerViewAdapter<*>?, holder: BaseViewHolder<*>?, groupPosition: Int)
}

interface OnLongHeaderClickListener {
    fun onLongHeaderClick(groupedRecyclerViewAdapter: GroupedRecyclerViewAdapter<*>?, holder: BaseViewHolder<*>?, groupPosition: Int)
}
