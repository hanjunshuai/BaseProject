package com.anningtex.baseproject.contract;

import com.anningtex.baselibrary.base.BaseContract;

/**
 * @ClassName: MainContract
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/20 16:12
 */
public interface MainContract {
    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<View> {
        String setText();
    }
}
