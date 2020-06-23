package com.anningtex.baselibrary.base

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anningtex.baselibrary.manager.AppManager
import java.util.*

abstract class AbsBaseActivity<P : AbsBaseContract.Presenter<*>?> : AppCompatActivity(), AbsBaseContract.View {
    @JvmField
    protected var mPresenter: P? = null
    protected var mWindowManager: WindowManager? = null

    @JvmField
    protected var mMetrics: DisplayMetrics? = null
    protected var mBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        setLanguage()
        mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mMetrics = DisplayMetrics()
        mWindowManager!!.defaultDisplay.getMetrics(mMetrics)
        mBundle = intent.extras
        AppManager.getAppManager().addActivity(this)
        initViews()
    }

    protected abstract val layoutResId: Int

    /**
     * 初始化控件，设置控件事件
     */
    protected abstract fun initViews()

    fun startAct(claz: Class<*>?) {
        val intent = Intent(this, claz)
        startActivity(intent)
    }

    fun startAct(cls: Class<*>?, name: String?, value: String?) {
        val intent = Intent(this, cls)
        intent.putExtra(name, value)
        startActivity(intent)
    }

    fun startAct(cls: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, cls)
        intent.putExtras(bundle!!)
        startActivity(intent)
    }

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun toast(msg: Int) {
        Toast.makeText(this, getString(msg), Toast.LENGTH_SHORT).show()
    }

    /**
     * 跟随手机系统判断转换中英文
     */
    fun setLanguage() {
        val locale: Locale
        locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList.getDefault()[0]
        } else {
            Locale.getDefault()
        }
        val language = locale.language
        val resources = resources
        val config = resources.configuration
        val dm = resources.displayMetrics
        if (language == "zh") {
            config.locale = Locale.SIMPLIFIED_CHINESE
        } else {
            config.locale = Locale.ENGLISH
        }
        resources.updateConfiguration(config, dm)
    }

    override fun showLoading() {}
    override fun dismissLoading() {}
    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        AppManager.getAppManager().finishCurrentActivity()
    }
}