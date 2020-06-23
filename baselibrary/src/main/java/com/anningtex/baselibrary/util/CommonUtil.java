package com.anningtex.baselibrary.util;

import android.view.View;

/**
 * @ClassName: CommonUtil
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/23 13:41
 */
public class CommonUtil {
    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }
}
