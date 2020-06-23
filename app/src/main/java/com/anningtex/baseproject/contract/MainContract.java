package com.anningtex.baseproject.contract;

import com.anningtex.baselibrary.base.AbsBaseContract;

/**
 * @ClassName: MainContract
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/20 16:12
 */
public interface MainContract {
    interface View extends AbsBaseContract.View {

    }

    interface Presenter extends AbsBaseContract.Presenter<View> {
        String setText();
    }
}
