package com.anningtex.baselibrary.base;

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
