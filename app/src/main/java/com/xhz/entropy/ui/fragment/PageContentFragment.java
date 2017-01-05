package com.xhz.entropy.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.xhz.entropy.R;
import com.xhz.entropy.presenter.PageContentPresenter;
import com.xhz.entropy.ui.view.IPageContentView;

import butterknife.BindView;

/**
 * Created by xh.zeng on 2017/1/5.
 */

public class PageContentFragment extends BaseFragment<PageContentPresenter> implements IPageContentView{

    @BindView(R.id.tvTest)
    TextView mTvTest;

    public static PageContentFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt("ARG_PAGE", page);
        PageContentFragment fragment = new PageContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new PageContentPresenter(getActivity(), this);
    }

    @Override
    protected void init() {
        int page = getArguments().getInt("ARG_PAGE");
        mTvTest.setText("" + page);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_pagecontent;
    }

    @Override
    public void onLoadFailure(Throwable e) {

    }

    @Override
    public void onLoadEmpty() {

    }
}
