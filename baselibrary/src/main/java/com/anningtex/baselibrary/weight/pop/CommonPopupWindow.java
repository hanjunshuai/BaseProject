package com.anningtex.baselibrary.weight.pop;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @ClassName: CommonPopupWindow
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/22 17:44
 */
public class CommonPopupWindow extends PopupWindow {
    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }

    public static class Builder {
        private final PopupController.PopupParams params;
        private ViewInterface listener;

        public Builder(Context context) {
            this.params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */
        public Builder setView(int layoutResId) {
            params.mView = null;
            params.mLayoutResId = layoutResId;
            return this;
        }
    }
}
