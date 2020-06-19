package com.anningtex.baseproject.base;

/**
 * @ClassName: BaseBaseContract
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/19 16:49
 */
public interface BaseContract {
    interface View {
        void showLoading();

        void dismissLoading();

        void toast(String msg);

        void toast(int msg);
    }

    interface Presenter<V extends View> {
        void attachView(V view);

        void detachView();
    }
}
