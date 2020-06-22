package com.anningtex.baselibrary.weight.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author alvis
 */
public class AlertController {
    private CustomAlertDialog mDialog;
    private Window mWindow;
    private DialogViewHelper mDialogViewHelper;

    public AlertController(CustomAlertDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public void setDialogViewHelper(DialogViewHelper dialogViewHelper) {
        this.mDialogViewHelper = dialogViewHelper;
    }

    // 设置文本
    public void setText(int viewId, CharSequence text) {
        mDialogViewHelper.setText(viewId, text);
    }

    public <T extends View> T getView(int viewId) {
        return mDialogViewHelper.getView(viewId);
    }

    //设置点击事件
    public void setOnClickListener(int view, View.OnClickListener listener) {
        mDialogViewHelper.setOnClickListener(view, listener);
    }

    /**
     * 获取dialog
     *
     * @return
     */
    public CustomAlertDialog getDialog() {
        return mDialog;
    }

    /**
     * 获取Dialog的window
     *
     * @return
     */
    public Window getWindow() {
        return mWindow;
    }

    public static class AlertParams {
        public Context mContext;
        public int mThemeResId;
        /**
         * 点击空白是否能够取消
         */
        public boolean mCancelable = true;
        //dialog Cancel 监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        // dialog Dismiss 监听
        public DialogInterface.OnDismissListener mOnDismissListener;
        // dialog key 监听
        public DialogInterface.OnKeyListener mOnKeyListener;
        // 布局 view
        public View mView;
        // 布局 layoutId
        public int mViewLayoutResId;
        // 存放字体的修改
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        // 存放点击事件
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        // 默认的宽度
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        //动画
        public int mAnimations = 0;
        //位置
        public int mGravity = Gravity.CENTER;
        //高度
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;


        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        /**
         * 绑定和设置参数
         *
         * @param mAlert
         */
        public void apply(AlertController mAlert) {
            // 设置参数

            //1、设置dialog布局  dialogViewHelper
            DialogViewHelper viewHelper = null;
            if (mViewLayoutResId != 0) {
                viewHelper = new DialogViewHelper(mContext, mViewLayoutResId);
            }

            if (mView != null) {
                viewHelper = new DialogViewHelper();
                viewHelper.setContentView(mView);
            }

            if (viewHelper == null) {
                throw new IllegalArgumentException("请设置布局setContentView()");
            }

            // 给dialog 设置布局
            mAlert.getDialog().setContentView(viewHelper.getContentView());

            //2、设置文本
            int textArraySize = mTextArray.size();
            for (int i = 0; i < textArraySize; i++) {
                mAlert.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }

            //3、设置点击
            int clickArraySize = mClickArray.size();
            for (int i = 0; i < clickArraySize; i++) {
                mAlert.setOnClickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }

            // 设置Controller 的辅助类
            mAlert.setDialogViewHelper(viewHelper);

            //4、配置自定义的效果      全屏  从底部弹出  默认动画
            Window window = mAlert.getWindow();
            window.setGravity(mGravity);
            if (mAnimations != 0) {
                window.setWindowAnimations(mAnimations);
            }

            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);

        }


    }
}
