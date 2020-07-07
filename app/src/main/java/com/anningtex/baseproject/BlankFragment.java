package com.anningtex.baseproject;


import android.view.View;

import com.anningtex.baselibrary.base.AbsBaseFragment;

import org.jetbrains.annotations.Nullable;

public class BlankFragment extends AbsBaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void onFragmentViewCreated(@Nullable View view) {

    }
}
