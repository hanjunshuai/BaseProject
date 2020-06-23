package com.anningtex.baselibrary.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

/**
 * @ClassName: BaseRecyclerViewAdapter
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/22 9:52
 */
abstract class BaseRecyclerViewAdapter<D, VH : ViewHolder?> : RecyclerView.Adapter<VH>() {
    @JvmField
    var mData: MutableList<D>? = ArrayList()

    fun setData(data: MutableList<D>?) {
        mData = data
        notifyDataSetChanged()
    }

    val data: List<D>?
        get() = mData

    override fun getItemCount(): Int {
        return if (mData == null) 0 else mData!!.size
    }

    fun appendItems(items: List<D>?) {
        if (items != null && items.size != 0) {
            mData?.addAll(items)
            notifyDataSetChanged()
        }
    }

    fun appendItems(items: List<D>?, index: Int) {
        if (items != null && items.size != 0) {
            mData?.addAll(index, items)
            notifyDataSetChanged()
        }
    }

    fun removeItem(item: D) {
        mData!!.remove(item)
        notifyDataSetChanged()
    }

    fun clear() {
        mData?.clear()
        notifyDataSetChanged()
    }
}