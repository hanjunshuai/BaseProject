package com.anningtex.baselibrary.base

interface BaseContract {
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