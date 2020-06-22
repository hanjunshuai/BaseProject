package com.anningtex.baselibrary.weight.titlebar;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.anningtex.baselibrary.R;

/**
 * @ClassName: DefaultNavigationBar
 * @Description: 头部的Builder基类
 * @Author: alvis
 * @CreateDate: 2020/6/22 11:17
 */
public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder.DefaultNavigationBarParams> {
    public DefaultNavigationBar(Builder.DefaultNavigationBarParams params) {
        super(params);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.title_bar;
    }

    @Override
    public void applyView() {
        // 绑定效果
        setText(R.id.title, getmParams().mTitle);
        setText(R.id.left_text, getmParams().mLeftText);
        setText(R.id.right_text, getmParams().mRightText);

        setIcon(R.id.left_icon, getmParams().mLeftIconRes);
        setIcon(R.id.right_icon, getmParams().mRightIconRes);
        setLeftVisibility(R.id.left_icon, getmParams().mLeftIconVisibility);

        setOnClickListener(R.id.right_text, getmParams().mRightClickListener);
        setOnClickListener(R.id.left_icon, getmParams().mLeftClickListener);
        setOnClickListener(R.id.left_text, getmParams().mLeftTextClickListener);
    }

    public static class Builder extends AbsNavigationBar.Builder {
        DefaultNavigationBarParams p;

        public Builder(Context context) {
            super(context, null);
            p = new DefaultNavigationBarParams(context, null);
        }

        public Builder(Context context, ViewGroup parent) {
            super(context, parent);
            p = new DefaultNavigationBarParams(context, parent);
        }

        @Override
        public DefaultNavigationBar builder() {
            DefaultNavigationBar navigationBar = new DefaultNavigationBar(p);
            return navigationBar;
        }

        // 1、设置所有效果
        public DefaultNavigationBar.Builder setTitle(String title) {
            p.mTitle = title;
            return this;
        }

        // right
        public DefaultNavigationBar.Builder setRightText(String rightText) {
            p.mRightText = rightText;
            return this;
        }

        public DefaultNavigationBar.Builder setRightIcon(int rightIconRes) {
            p.mRightIconRes = rightIconRes;
            return this;
        }

        public DefaultNavigationBar.Builder setLeftIcon(int leftIconRes) {
            p.mLeftIconRes = leftIconRes;
            return this;
        }

        public DefaultNavigationBar.Builder setLeftIconVisibility(int leftIconVisibility) {
            p.mLeftIconVisibility = leftIconVisibility;
            return this;
        }

        public DefaultNavigationBar.Builder setRightClickListener(View.OnClickListener rightClickListener) {
            p.mRightClickListener = rightClickListener;
            return this;
        }

        // left
        public DefaultNavigationBar.Builder setLeftText(String leftText) {
            p.mLeftText = leftText;
            return this;
        }

        public DefaultNavigationBar.Builder setLeftTextClickListener(View.OnClickListener leftTextClickListener) {
            p.mLeftTextClickListener = leftTextClickListener;
            return this;
        }

        public DefaultNavigationBar.Builder setLeftClickListener(View.OnClickListener leftClickListener) {
            p.mLeftClickListener = leftClickListener;
            return this;
        }

        public static class DefaultNavigationBarParams extends AbsNavigationBar.Builder.AbsNavigationBarParams {
            public String mTitle;
            public String mRightText;
            public int mRightIconRes;
            public int mLeftIconRes;
            public int mLeftIconVisibility;
            public View.OnClickListener mRightClickListener;
            public String mLeftText;
            public View.OnClickListener mLeftTextClickListener;
            public View.OnClickListener mLeftClickListener = v -> {
                ((Activity) mContext).finish();
            };

            public DefaultNavigationBarParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}
