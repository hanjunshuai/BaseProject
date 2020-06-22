package com.anningtex.baseproject;

import android.view.Window;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anningtex.baselibrary.adapter.GroupedRecyclerViewAdapter;
import com.anningtex.baselibrary.base.AbsBaseActivity;
import com.anningtex.baselibrary.base.BaseViewHolder;
import com.anningtex.baselibrary.listener.OnHeaderClickListener;
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
                .setRightText("发布")
                .setRightClickListener(v -> Toast.makeText(MainActivity.this, "发布", Toast.LENGTH_SHORT).show())
                .setLeftText("left")
                .setLeftClickListener(v -> Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show())
                .setLeftTextClickListener(v -> Toast.makeText(this, "left", Toast.LENGTH_SHORT).show())
                .builder();
        mRecyclerView = findViewById(R.id.recycler_view);
        initRecycler();
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

}
