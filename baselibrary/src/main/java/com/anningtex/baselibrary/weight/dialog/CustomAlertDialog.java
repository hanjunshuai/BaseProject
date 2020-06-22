package com.anningtex.baselibrary.weight.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.anningtex.baselibrary.R;

/**
 * @author alvis
 */
public class CustomAlertDialog extends Dialog {
    private AlertController mAlert;


    public CustomAlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }

    // 设置文本
    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId, text);
    }

    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }

    //设置点击事件
    public void setOnClickListener(int view, View.OnClickListener listener) {
        mAlert.setOnClickListener(view, listener);
    }


    public static class Builder {
        private final AlertController.AlertParams P;

        public Builder(Context context) {
            this(context, R.style.dialog);
        }


        /**
         * Creates a builder for an alert dialog that uses an explicit theme
         * resource.
         * <p>
         * The specified theme resource ({@code themeResId}) is applied on top
         * of the parent {@code context}'s theme. It may be specified as a
         * style resource containing a fully-populated theme, such as
         * {@link R.style#Theme_AppCompat_Dialog}, to replace all
         * attributes in the parent {@code context}'s theme including primary
         * and accent colors.
         * <p>
         * To preserve attributes such as primary and accent colors, the
         * {@code themeResId} may instead be specified as an overlay theme such
         * as {@link R.style#ThemeOverlay_AppCompat_Dialog}. This will
         * override only the window attributes necessary to style the alert
         * window as a dialog.
         * <p>
         * Alternatively, the {@code themeResId} may be specified as {@code 0}
         * to use the parent {@code context}'s resolved value for
         * {@link android.R.attr#alertDialogTheme}.
         *
         * @param context    the parent context
         * @param themeResId the resource ID of the theme against which to inflate
         *                   this dialog, or {@code 0} to use the parent
         *                   {@code context}'s default alert dialog theme
         */
        public Builder(@NonNull Context context, int themeResId) {
            P = new AlertController.AlertParams(context, themeResId);
        }

        /**
         * Sets a custom view to be the contents of the alert dialog.
         * <p>
         * When using a pre-Holo theme, if the supplied view is an instance of
         * a {@link ListView} then the light background will be used.
         * <p>
         * <strong>Note:</strong> To ensure consistent styling, the custom view
         * should be inflated or constructed using the alert dialog's themed
         * context obtained via {@link #getContext()}.
         *
         * @param view the view to use as the contents of the alert dialog
         * @return this Builder object to allow for chaining of calls to set
         * methods
         */
        public Builder setContetnView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        // 设置布局内容的layoutId
        public Builder setContetnView(int layoutId) {
            P.mView = null;
            P.mViewLayoutResId = layoutId;
            return this;
        }

        //
        public Builder fullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 从底部弹出
         *
         * @param isAnimation 是否有动画
         * @return
         */
        public Builder formBottom(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimations = R.style.dialog_from_bottom_anim;
                P.mGravity = Gravity.BOTTOM;
            }
            return this;
        }

        /**
         * 设置dialog 的宽高
         *
         * @param width
         * @param height
         * @return
         */
        public Builder setWidthAndHeight(int width, int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        /**
         * 添加设置默认动画
         *
         * @return
         */
        public Builder addDefaultAnimation() {
            P.mAnimations = R.style.dialog_scale_anim;
            return this;
        }

        /**
         * 设置动画
         *
         * @param styleAnimation
         * @return
         */
        public Builder setAnimations(int styleAnimation) {
            P.mAnimations = styleAnimation;
            return this;
        }


        // 设置文本
        public Builder setText(int viewId, CharSequence text) {
            P.mTextArray.put(viewId, text);
            return this;
        }

        //设置点击事件
        public Builder setOnClickListener(int view, View.OnClickListener listener) {
            P.mClickArray.put(view, listener);
            return this;
        }

        /**
         * Returns a {@link Context} with the appropriate theme for dialogs created by this Builder.
         * Applications should use this Context for obtaining LayoutInflaters for inflating views
         * that will be used in the resulting dialogs, as it will cause views to be inflated with
         * the correct theme.
         *
         * @return A Context for built Dialogs.
         */
        @NonNull
        public Context getContext() {
            return P.mContext;
        }

        /**
         * Creates an {@link CustomAlertDialog} with the arguments supplied to this
         * builder.
         * <p>
         * Calling this method does not display the dialog. If no additional
         * processing is needed, {@link #show()} may be called instead to both
         * create and display the dialog.
         */
        public CustomAlertDialog create() {
            // We can't use Dialog's 3-arg constructor with the createThemeContextWrapper param,
            // so we always have to re-set the theme
            final CustomAlertDialog dialog = new CustomAlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        /**
         * Creates an {@link CustomAlertDialog} with the arguments supplied to this
         * builder and immediately displays the dialog.
         * <p>
         * Calling this method is functionally identical to:
         * <pre>
         *     CustomAlertDialog dialog = builder.create();
         *     dialog.show();
         * </pre>
         */
        public CustomAlertDialog show() {
            final CustomAlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

}
