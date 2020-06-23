package com.anningtex.baseproject;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anningtex.baselibrary.adapter.GroupedRecyclerViewAdapter;
import com.anningtex.baselibrary.base.AbsBaseActivity;
import com.anningtex.baselibrary.base.BaseViewHolder;
import com.anningtex.baselibrary.listener.OnHeaderClickListener;
import com.anningtex.baselibrary.weight.pop.CommonPopupWindow;
import com.anningtex.baselibrary.weight.titlebar.DefaultNavigationBar;
import com.anningtex.baseproject.adapter.GroupAdapter;
import com.anningtex.baseproject.contract.MainContract;
import com.anningtex.baseproject.entity.GroupEntity;
import com.anningtex.baseproject.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsBaseActivity<MainPresenter> implements MainContract.View {

    private RecyclerView mRecyclerView;
    private GroupAdapter mGroupAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;

    }

    @Override
    protected void initViews() {
        if (mPresenter == null) {
            mPresenter = new MainPresenter();
            mPresenter.attachView(this);
        }
        toast(mPresenter.setText());

        DefaultNavigationBar navigationBar = new DefaultNavigationBar
                .Builder(this)
                .setTitle("投稿")
                .setRightText("right")
                .setRightClickListener(v -> Toast.makeText(MainActivity.this, "发布", Toast.LENGTH_SHORT).show())
                .setLeftText("left")
                .setLeftClickListener(v -> Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show())
                .setLeftTextClickListener(v -> createPop(v))
                .setRightIcon(R.mipmap.ic_launcher)
                .setLeftIcon(R.mipmap.ic_launcher)
                .setLeftIconVisibility(View.GONE)
                .builder();
        mRecyclerView = findViewById(R.id.recycler_view);
        initRecycler();

    }

    private TextView tv_child;

    private void createPop(View view) {
        CommonPopupWindow popupWindow = new CommonPopupWindow.Builder(this)
                //设置PopupWindow布局
                .setView(R.layout.popup_down)
                //设置宽高
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, (int) (mMetrics.heightPixels * 0.5))
                //设置动画
                //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                .setBackGroundLevel(0.5f)
                //设置PopupWindow里的子View及点击事件
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        tv_child = view.findViewById(R.id.tv_child);
                        tv_child.setText("我是子View");
                    }
                })
                //设置外部是否可点击 默认是true
                .setOutsideTouchable(false)
                //开始构建
                .create();
        //弹出PopupWindow
        popupWindow.showAsDropDown(view);

        tv_child.setOnClickListener(v -> popupWindow.dismiss());
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGroupAdapter = new GroupAdapter();
        addData();
        mGroupAdapter.setData(mGroupEntities);
        mRecyclerView.setAdapter(mGroupAdapter);
        mGroupAdapter.setOnHeaderClickListener(new OnHeaderClickListener() {
            @Override
            public void onHeaderClick(GroupedRecyclerViewAdapter groupedRecyclerViewAdapter, BaseViewHolder holder, int groupPosition) {
                if (groupedRecyclerViewAdapter.isExpand(groupPosition)) {
                    groupedRecyclerViewAdapter.collapseGroup(groupPosition);
                } else {
                    groupedRecyclerViewAdapter.expandGroup(groupPosition);
                }
            }
        });
    }

    private List<GroupEntity> mGroupEntities;

    private void addData() {
        mGroupEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GroupEntity groupEntity = new GroupEntity("item" + i);
            List<GroupEntity.Child> childList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                GroupEntity.Child child = new GroupEntity.Child();
                child.setChildName("child: " + j);
                childList.add(child);
            }
            groupEntity.setT(childList);
            mGroupEntities.add(groupEntity);
        }
    }

    @Override
    public void onBackPressed() {
        toast("onBackPressed");
    }
}
