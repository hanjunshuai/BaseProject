package com.anningtex.baseproject;

import com.anningtex.baselibrary.base.AbsBaseActivity;
import com.anningtex.baselibrary.base.BaseContract;
import com.anningtex.baselibrary.base.BasePresenter;

/**
 * @Description: des
 * @ClassName: MainActivity
 * @Author: alvis
 * @CreateDate:2020-6-19 16:48:52
 */
public class MainActivity extends AbsBaseActivity<BasePresenter> implements BaseContract.View {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

}
