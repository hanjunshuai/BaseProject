package com.anningtex.baselibrary.base;

/**
 * @ClassName: BasePresenter
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/19 16:49
 */
public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {
    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getAttachView() {
        return view;
    }
}
