package com.anningtex.baseproject.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * @ClassName: AppManager
 * @Description: 应用程序Activity管理类：用于Activity管理和应用程序退出
 * @Author: alvis
 * @CreateDate: 2020/6/19 16:53
 */
public class AppManager {
    private static Stack<Activity> mStackActivity;
    private static AppManager m_instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (m_instance == null) {
            m_instance = new AppManager();
        }
        return m_instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mStackActivity == null) {
            mStackActivity = new Stack<>();
        }
        mStackActivity.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = mStackActivity.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        // 防止 NoSuchElementException
        if (mStackActivity == null || mStackActivity.size() == 0) {
            return;
        }
        Activity activity = mStackActivity.lastElement();
        finishCurrentActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishCurrentActivity(Activity activity) {
        if (activity != null) {
            mStackActivity.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishCurrentActivity(Class<?> cls) {
        for (Activity activity : mStackActivity) {
            if (activity.getClass().equals(cls)) {
                finishCurrentActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (mStackActivity == null || mStackActivity.size() == 0) {
            return;
        }
        for (int i = 0, size = mStackActivity.size(); i < size; i++) {
            if (null != mStackActivity.get(i)) {
                mStackActivity.get(i).finish();
            }
        }
        mStackActivity.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
//            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
