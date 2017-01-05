package com.xhz.entropy.ui.activity;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.xhz.entropy.R;
import com.xhz.entropy.data.bean.GankTypeData;
import com.xhz.entropy.presenter.DataByDayPresenter;
import com.xhz.entropy.ui.adapter.DataByDayAdapter;
import com.xhz.entropy.ui.view.IDataByDayView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public class DataByDayActivity extends BaseSwipeRefreshActivity<DataByDayPresenter>
        implements IDataByDayView<GankTypeData>, DataByDayAdapter.IClickItem {

    public static final String TAG = DataByDayActivity.class.getSimpleName();
    private static final int SPAN_COUNT = 2;

    @BindView(R.id.rcv_content)
    RecyclerView mRvContent;

    private DataByDayAdapter mAdapter;
    private boolean mHasMoreData = true;

    @Override
    protected void init() {
        initRecycleView();
        setTitle("干果", false);

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                showRefresh();
//            }
//        }, 500);
        mPresenter.reloadData();
    }

    private void initRecycleView() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(SPAN_COUNT,
                StaggeredGridLayoutManager.VERTICAL);
        mRvContent.setLayoutManager(layoutManager);
        mAdapter = new DataByDayAdapter(this);
        mAdapter.setIClickItem(this);
        mRvContent.setAdapter(mAdapter);
        mRvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1] >= mAdapter.getItemCount() - 4;
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom && mHasMoreData) {
                    showRefresh();
                    mPresenter.loadMoreData();
                }
            }
        });
    }

    @Override
    @CheckResult
    protected boolean prepareRefresh() {
        if (mPresenter.shouldReloadData()) {
            mPresenter.resetCurrentPage();
            if (!isRefreshing()) {
                showRefresh();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_databyday;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new DataByDayPresenter(this, this);
    }

    @Override
    protected void onRefreshStarted() {
        mPresenter.reloadData();
    }

    @Override
    public void fillData(List<GankTypeData> data) {
        mAdapter.setDataSource(data);
    }

    @Override
    public void loadMoreData(List<GankTypeData> data) {
        mAdapter.appendToDataSource(data);
    }

    @Override
    public void onLoadEmpty() {
        Snackbar.make(mRvContent, "加载数据为空", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadFailure(Throwable throwable) {
        throwable.printStackTrace();

        final Snackbar errorSnack = Snackbar.make(mRvContent,
                "无法加载数据，请检查网络连接。点击可重试。", Snackbar.LENGTH_INDEFINITE);
        errorSnack.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorSnack.dismiss();
                onRefreshStarted();
            }
        });
        errorSnack.show();
    }

    @Override
    public void hasNoMoreData() {
        mHasMoreData = false;
        Snackbar.make(mRvContent, "没有更多数据了", Snackbar.LENGTH_SHORT)
                .setAction("回到顶部", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        (mRvContent.getLayoutManager()).smoothScrollToPosition(mRvContent, null, 0);
                    }
                })
                .show();
    }

    @Override
    public void onClickPhoto(int position, View view, View textView) {
        GankTypeData clickData = mAdapter.getGankData(position);
        if (clickData != null) {
            GirlPhotoActivity.actionStart(this, clickData.getUrl());
        }
    }
}
