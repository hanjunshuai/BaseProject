package com.anningtex.baselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anningtex.baselibrary.manager.AppManager;

import java.util.Locale;

public abstract class AbsBaseActivity<P extends BaseContract.Presenter> extends AppCompatActivity implements BaseContract.View {
    protected P mPersenter;

    protected WindowManager mWindowManager;
    protected DisplayMetrics mMetrics;
    protected Bundle mBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        setLangue();
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(mMetrics);
        mBundle = getIntent().getExtras();
        AppManager.getAppManager().addActivity(this);
        initViews();
    }

    protected abstract int getLayoutResId();

    /**
     * 初始化控件，设置控件事件
     */
    protected abstract void initViews();

    public void $startActivity(Class<?> claz) {
        Intent intent = new Intent(this, claz);
        startActivity(intent);
    }

    public void $startActivity(Class<?> cls, String name, String value) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(name, value);
        startActivity(intent);
    }

    public void $startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(int msg) {
        Toast.makeText(this, getString(msg), Toast.LENGTH_SHORT).show();
    }

    /**
     * 跟随手机系统判断转换中英文
     */
    public void setLangue() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        String language = locale.getLanguage();
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals("zh")) {
            config.locale = Locale.SIMPLIFIED_CHINESE;
        } else {
            config.locale = Locale.ENGLISH;
        }
        resources.updateConfiguration(config, dm);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPersenter != null) {
            mPersenter.detachView();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppManager.getAppManager().finishCurrentActivity();
    }
}

