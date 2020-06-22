package com.anningtex.baselibrary.weight.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * @ClassName: PopupController
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/22 17:45
 */
public class PopupController {
    private int mLayoutResId;
    private Context mContext;
    private PopupWindow mPopupWindow;
    /**
     * 弹窗布局View
     */
    private View mPopupView;
    private View mView;
    private Window mWindow;

    public PopupController(Context context, View popupView) {
        mContext = context;
        mPopupView = popupView;
    }

    public void setLayoutResId(int layoutResId) {
        this.mLayoutResId = layoutResId;
        this.mView = null;
        installContent();
    }

    public void setView(View view) {
        mView = view;
        this.mLayoutResId = 0;
        installContent();
    }

    private void installContent() {
        if (mLayoutResId != 0) {
            mPopupView = LayoutInflater.from(mContext).inflate(mLayoutResId, null);
        } else {
            mPopupView = mView;
        }
        mPopupWindow.setContentView(mPopupView);
    }

    /**
     * 设置宽高
     *
     * @param width
     * @param height
     */
    private void setWidthAndHeight(int width, int height) {
        if (width == 0 || height == 0) {
            // 如果没设置宽高，默认 WRAP_CONTENT
            mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            mPopupWindow.setWidth(width);
            mPopupWindow.setHeight(height);
        }
    }

    /**
     * 设置背景灰色程度
     *
     * @param level
     */
    private void setBackGroundLevel(float level) {
        mWindow = ((Activity) mContext).getWindow();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        params.alpha = level;
        mWindow.setAttributes(params);
    }

    /**
     * 设置Outside是否可点击
     *
     * @param touchable
     */
    private void setOutsideTouchable(boolean touchable) {
        // 设置透明背景
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        // 设置Outside是否点击
        mPopupWindow.setOutsideTouchable(touchable);
        mPopupWindow.setFocusable(touchable);
    }

    /**
     * 设置动画
     */
    private void setAnimationStyle(int animationStyle) {
        mPopupWindow.setAnimationStyle(animationStyle);
    }

    public static class PopupParams {
        public int mLayoutResId;
        public Context mContext;
        public int mWidth, mHeight;
        public boolean isShowBg, isShowAnim;
        public int animationStyle;
        public float bgLevel;
        public View mView;
        public boolean isTouchable = true;

        public PopupParams(Context context) {
            mContext = context;
        }

        public void apply(PopupController controller) {
            if (mView != null) {
                controller.setView(mView);
            } else if (mLayoutResId != 0) {
                controller.setLayoutResId(mLayoutResId);
            } else {
                throw new IllegalArgumentException("pop contentView is null");
            }

            controller.setWidthAndHeight(mWidth, mHeight);
            controller.setOutsideTouchable(isTouchable);

            if (isShowBg) {
                controller.setBackGroundLevel(bgLevel);
            }

            if (isShowAnim) {
                controller.setAnimationStyle(animationStyle);
            }
        }
    }
}
