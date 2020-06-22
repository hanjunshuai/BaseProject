package com.anningtex.baselibrary.weight.titlebar;

/**
 * @ClassName: INavigationBar
 * @Description: 导航条的规范
 * @Author: alvis
 * @CreateDate: 2020/6/22 11:15
 */
public interface INavigationBar {
    // 头部的规范
    public int bindLayoutId();

    // 绑定头部的参数
    public void applyView();
}
