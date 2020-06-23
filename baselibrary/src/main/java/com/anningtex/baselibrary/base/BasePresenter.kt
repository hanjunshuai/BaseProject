package com.anningtex.baselibrary.base

open class BasePresenter<V : BaseContract.View?> : BaseContract.Presenter<V> {
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