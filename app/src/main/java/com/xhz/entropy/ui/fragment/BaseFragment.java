package com.xhz.entropy.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhz.entropy.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by xh.zeng on 2017/1/5.
 */

public abstract class BaseFragment <P extends BasePresenter> extends Fragment {

    protected P mPresenter;
    protected View mContentView;
    protected Context mContext;

    protected abstract void initPresenter();

    protected abstract void init();

    protected abstract int getLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, mContentView);
        init();
        initPresenter();
        checkPresenterIsNull();
        return mContentView;
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }
}
