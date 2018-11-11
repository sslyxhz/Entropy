package com.sslyxhz.entropy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sslyxhz.entropy.R;
import com.sslyxhz.entropy.presenter.BasePresenter;
import com.sslyxhz.entropy.ui.view.ISwipeRefreshView;

import butterknife.BindView;

/**
 * Created by xh.zeng on 2017/1/9.
 */

public abstract class BaseSwipeRefreshFragment <P extends BasePresenter>
        extends BaseFragment<P> implements ISwipeRefreshView {

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initSwipeLayout();
        return view;
    }

    private void initSwipeLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (prepareRefresh()) {
                    onRefreshStarted();
                } else {
                    hideRefresh();
                }
            }
        });
    }

    protected abstract boolean prepareRefresh();

    protected abstract void onRefreshStarted();

    @Override
    public void hideRefresh() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 1000);
    }

    @Override
    public void showRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @CheckResult
    protected boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    @Override
    public void loadDataOver() {
        hideRefresh();
    }
}
