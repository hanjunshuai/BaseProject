package com.anningtex.baseproject.presenter;


import com.anningtex.baselibrary.base.AbsBasePresenter;
import com.anningtex.baseproject.contract.MainContract;

/**
 * @ClassName: MainPresenter
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/20 16:11
 */
public class MainPresenter extends AbsBasePresenter<MainContract.View> implements MainContract.Presenter {
    @Override
    public String setText() {
        return "hello import";
    }
}
