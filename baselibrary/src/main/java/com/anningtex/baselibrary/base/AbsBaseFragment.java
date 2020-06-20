package com.anningtex.baselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @ClassName: AbsBaseFragment
 * @Description: java类作用描述
 * @Author: alvis
 * @CreateDate: 2020/6/20 15:43
 */
public abstract class AbsBaseFragment<P extends BaseContract.Presenter> extends Fragment implements BaseContract.View {
    protected P mPresenter;
    protected Context mContext;
    protected WindowManager mWindowManager;
    protected DisplayMetrics mMetrics;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(mMetrics);
        View view = inflater.inflate(getLayoutResId(), container, false);
        onFragmentViewCreated();
        return view;
    }

    public void onFragmentViewCreated() {

    }

    protected abstract int getLayoutResId();


    @Override
    public void toast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(int msg) {
        Toast.makeText(mContext, getString(msg), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public void $startActivity(Class<?> cls) {
        Intent intent = new Intent(getContext(), cls);
        startActivity(intent);
    }

    public void $startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void $startActivity(Class<?> cls, String name, String value) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtra(name, value);
        startActivity(intent);
    }

}
