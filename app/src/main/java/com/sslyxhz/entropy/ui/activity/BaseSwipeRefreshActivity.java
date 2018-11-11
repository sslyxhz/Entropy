package com.sslyxhz.entropy.ui.activity;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.v4.widget.SwipeRefreshLayout;

import com.sslyxhz.entropy.R;
import com.sslyxhz.entropy.presenter.BasePresenter;
import com.sslyxhz.entropy.ui.view.ISwipeRefreshView;

import butterknife.BindView;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public abstract class BaseSwipeRefreshActivity<P extends BasePresenter>
        extends BaseActivity<P> implements ISwipeRefreshView {

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSwipeLayout();
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

    /**
     * check data status
     *
     * @return return true indicate it should load data really else indicate don't refresh
     */
    protected boolean prepareRefresh() {
        return true;
    }

    /**
     * the method of get data
     */
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

    /**
     * check refresh layout is refreshing
     *
     * @return if the refresh layout is refreshing return true else return false
     */
    @CheckResult
    protected boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    @Override
    public void loadDataOver() {
        hideRefresh();
    }
}
