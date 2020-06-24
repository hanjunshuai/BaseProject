package com.anningtex.baselibrary.base

open abstract class AbsBasePresenter<V : AbsBaseContract.View?> : AbsBaseContract.Presenter<V> {
    var attachView: V? = null
        private set

    override fun attachView(view: V) {
        attachView = view
    }

    override fun detachView() {
        attachView = null
    }

    val isViewAttached: Boolean get() = attachView != null

}