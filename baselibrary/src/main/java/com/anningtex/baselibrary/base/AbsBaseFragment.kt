package com.anningtex.baselibrary.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * @ClassName: AbsBaseFragment
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/20 15:43
 */
abstract class AbsBaseFragment<P : AbsBaseContract.Presenter<*>?> : Fragment(), AbsBaseContract.View {
    @JvmField
    protected var mPresenter: P? = null

    @JvmField
    protected var mContext: Context? = null

    @JvmField
    protected var mWindowManager: WindowManager? = null

    @JvmField
    protected var mMetrics: DisplayMetrics? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mWindowManager = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mMetrics = DisplayMetrics()
        mWindowManager!!.defaultDisplay.getMetrics(mMetrics)
        var view = inflater.inflate(layoutResId, container, false)
        onFragmentViewCreated(view)
        return view
    }

    protected abstract fun onFragmentViewCreated(view: View?)

    protected abstract val layoutResId: Int

    override fun toast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun toast(msg: Int) {
        Toast.makeText(mContext, getString(msg), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
    }

    fun startAct(cls: Class<*>?) {
        val intent = Intent(context, cls)
        startActivity(intent)
    }

    fun startAct(cls: Class<*>?, bundle: Bundle?) {
        val intent = Intent(context, cls)
        intent.putExtras(bundle!!)
        startActivity(intent)
    }

    fun startAct(cls: Class<*>?, name: String?, value: String?) {
        val intent = Intent(activity, cls)
        intent.putExtra(name, value)
        startActivity(intent)
    }

    override fun showLoading() {}

    override fun dismissLoading() {}
}