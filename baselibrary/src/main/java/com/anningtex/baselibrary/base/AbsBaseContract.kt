package com.anningtex.baselibrary.base

interface AbsBaseContract {
    interface View {
        fun showLoading()
        fun dismissLoading()
        fun toast(msg: Int)
        fun toast(msg: String)
    }

    interface Presenter<V : View?> {
        fun attachView(view: V)
        fun detachView()
    }
}