package com.anningtex.baselibrary.manager;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;

import com.anningtex.baselibrary.adapter.GroupedRecyclerViewAdapter;

/**
 * @ClassName: GroupedGridLayoutManager
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/4/24 16:26
 */
public class GroupedGridLayoutManager extends GridLayoutManager {

    private GroupedRecyclerViewAdapter mAdapter;

    public GroupedGridLayoutManager(Context context, int spanCount,
                                    GroupedRecyclerViewAdapter adapter) {
        super(context, spanCount);
        mAdapter = adapter;
        setSpanSizeLookup();
    }

    public GroupedGridLayoutManager(Context context, int spanCount, int orientation,
                                    boolean reverseLayout, GroupedRecyclerViewAdapter adapter) {
        super(context, spanCount, orientation, reverseLayout);
        this.mAdapter = adapter;
        setSpanSizeLookup();
    }

    public GroupedGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
                                    int defStyleRes, GroupedRecyclerViewAdapter adapter) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mAdapter = adapter;
        setSpanSizeLookup();
    }

    private void setSpanSizeLookup() {
        super.setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int count = getSpanCount();
                if (mAdapter != null) {
                    int type = mAdapter.judgeType(position);
                    //只对子项做Grid效果
                    if (type == GroupedRecyclerViewAdapter.TYPE_CHILD) {
                        int groupPosition = mAdapter.getGroupPositionForPosition(position);
                        int childPosition =
                                mAdapter.getChildPositionForPosition(groupPosition, position);
                        return getChildSpanSize(groupPosition, childPosition);
                    }
                }

                return count;
            }
        });
    }

    /**
     * 提供这个方法可以使外部改变子项的SpanSize。
     * 这个方法的作用跟{@link SpanSizeLookup#getSpanSize(int)}一样。
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    public int getChildSpanSize(int groupPosition, int childPosition) {
        return 1;
    }

    @Override
    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {

    }
}