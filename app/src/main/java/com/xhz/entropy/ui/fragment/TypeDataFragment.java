package com.xhz.entropy.ui.fragment;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xhz.entropy.R;
import com.xhz.entropy.presenter.TypeDataPresenter;
import com.xhz.entropy.ui.adapter.TypeDataAdapter;
import com.xhz.entropy.ui.view.ITypeDataView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xh.zeng on 2017/1/5.
 */

public class TypeDataFragment extends BaseFragment<TypeDataPresenter>
        implements ITypeDataView, TypeDataAdapter.IClickItem{

    public static final String TAG = TypeDataFragment.class.getSimpleName();

    @BindView(R.id.tvTest) TextView mTvTest;
    @BindView(R.id.rv_content) RecyclerView mRvContent;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;

    private String mTagName;
    private TypeDataAdapter mAdapter;
    private boolean mHasMoreData = true;

    public static TypeDataFragment newInstance(String tagName){
        Log.v(TAG, "newInstance, tagName = "+tagName);
        TypeDataFragment fragment = new TypeDataFragment();
        fragment.setTagName(tagName);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void init() {
        Log.v(TAG, "init");

        mTvTest.setText(mTagName);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (prepareRefresh()) {
                    mPresenter.reloadData();
                } else {
                    hideRefresh();
                }
            }
        });

        mAdapter = new TypeDataAdapter(getContext());
        mAdapter.setIClickItem(this);
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.setItemAnimator(new DefaultItemAnimator());
        mRvContent.setHasFixedSize(true);
        mRvContent.setAdapter(mAdapter);
        mRvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // RecyclerView.canScrollVertically(1)的值表示是否能向上滚动，false表示已经滚动到底部
                // RecyclerView.canScrollVertically(-1)的值表示是否能向下滚动，false表示已经滚动到顶部
                boolean isBottom = !recyclerView.canScrollVertically(1);
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom && mHasMoreData) {
                    showRefresh();
                    mPresenter.loadMoreData();
                }
            }
        });

        mPresenter.reloadData();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new TypeDataPresenter(getActivity(), this, mTagName);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_type_data;
    }

    @Override
    public void onLoadFailure(Throwable e) {
        Snackbar.make(mRvContent, "加载数据失败", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadEmpty() {
        Snackbar.make(mRvContent, "加载数据为空", Snackbar.LENGTH_SHORT).show();
    }

    public String getTagName(){
        return mTagName;
    }

    public void setTagName(String name){
        mTagName = name;
    }

    @Override
    public void onClickItem(int position, View view, View textView) {
        Log.v(TAG, "onClickItem-" + position);
    }

    @Override
    public void fillData(List data) {
        Log.v(TAG, "fillData-" + data);
        mAdapter.setDataSource(data);
    }

    @Override
    public void loadMoreData(List data) {
        Log.v(TAG, "loadMoreData-" + data);
        mAdapter.appendToDataSource(data);
    }

    @Override
    public void hasNoMoreData() {
        Log.v(TAG, "hasNoMoreData-"+false);
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
    public void loadDataOver() {
        Log.v(TAG, "loadDataOver");
        hideRefresh();
    }

    @Override
    public void showRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

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

    protected boolean prepareRefresh() {
        if (mPresenter.shouldReloadData()) {
            mPresenter.resetCurrentPage();
            if (!mSwipeRefreshLayout.isRefreshing()) {
                showRefresh();
            }
            return true;
        } else {
            return false;
        }
    }
}
