package com.anningtex.baselibrary.weight.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * dialog view 的 辅助处理类
 *
 * @author alvis
 */
public class DialogViewHelper {
    private View mContentView = null;
    // 防止霸气侧漏
    private SparseArray<WeakReference<View>> mViews;

    public DialogViewHelper(Context mContext, int layoutResId) {
        this();
        mContentView = LayoutInflater.from(mContext).inflate(layoutResId, null);
    }

    public DialogViewHelper() {
        mViews = new SparseArray<>();
    }

    /**
     * 设置布局View
     *
     * @param contentView
     */
    public void setContentView(View contentView) {
        this.mContentView = contentView;
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        // 每次都 findViewById  减少findViewById 的次数
        TextView tv = getView(viewId);
        if (tv != null) {
            tv.setText(text);
        }
    }

    public <T extends View> T getView(int viewId) {
        WeakReference<View> viewReference = mViews.get(viewId);
        // 侧漏的问题
        View view = null;
        if (viewReference != null) {
            view = viewReference.get();
        }
        if (view == null) {
            view = mContentView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<>(view));
            }
        }
        return (T) view;
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    /**
     * 获取contentView
     *
     * @return
     */
    public View getContentView() {
        return mContentView;
    }
}
