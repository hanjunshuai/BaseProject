package com.anningtex.baselibrary.weight.titlebar;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @ClassName: AbsNavigationBar
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/22 11:15
 */
public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder.AbsNavigationBarParams> implements INavigationBar {
    private P mParams;
    private View mNavigationView;

    public AbsNavigationBar(P params) {
        this.mParams = params;
        createAndBindView();
    }

    public P getmParams() {
        return mParams;
    }

    /**
     * 绑定和创建view
     */
    private void createAndBindView() {
        if (getmParams().mParent == null) {
            Activity activityRoot = (Activity) getmParams().mContext;
            getmParams().mParent = activityRoot.findViewById((android.R.id.content));
        }
        if (getmParams().mParent == null) {
            return;
        }
        // 1、创建view
        mNavigationView = LayoutInflater.from(mParams.mContext).inflate(bindLayoutId(), mParams.mParent, false);

        // 2、添加
        mParams.mParent.addView(mNavigationView, 0);

        applyView();
    }

    protected void setText(int viewId, String text) {
        TextView tv = findViewById(viewId);
        if (!TextUtils.isEmpty(text)) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }

    protected void setOnClickListener(int viewId, View.OnClickListener listener) {
        findViewById(viewId).setOnClickListener(listener);
    }

    public <T extends View> T findViewById(int viewId) {
        return mNavigationView.findViewById(viewId);
    }

    // Builder 设计模式： 仿照系统写的 AbsNavigationBar Builder 参数Params
    public static abstract class Builder {
        AbsNavigationBarParams p;

        public Builder(Context context, ViewGroup parent) {
            // 创建 p = new
            p = new AbsNavigationBarParams(context, parent);
        }

        public abstract AbsNavigationBar builder();

        public static class AbsNavigationBarParams {
            public Context mContext;
            public ViewGroup mParent;

            public AbsNavigationBarParams(Context context, ViewGroup parent) {
                this.mContext = context;
                this.mParent = parent;
            }
        }
    }
}
