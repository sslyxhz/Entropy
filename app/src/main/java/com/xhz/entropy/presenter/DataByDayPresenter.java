package com.xhz.entropy.presenter;

import android.app.Activity;
import android.util.Log;

import com.xhz.entropy.data.GankResult;
import com.xhz.entropy.data.bean.GankTypeData;
import com.xhz.entropy.net.GankService;
import com.xhz.entropy.net.ServiceFactory;
import com.xhz.entropy.ui.view.IDataByDayView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 一日一图 列表呈现
 * Created by xh.zeng on 2017/1/3.
 */

public class DataByDayPresenter extends BasePresenter<IDataByDayView> {
    public static final String TAG = DataByDayPresenter.class.getSimpleName();

    private int mCurrentPage = 1;
    private int mPageSize = 10;

    public DataByDayPresenter(Activity context, IDataByDayView view) {
        super(context, view);
    }

    public void resetCurrentPage() {
        mCurrentPage = 1;
    }

    public boolean shouldReloadData() {
        return mCurrentPage <= 2;
    }

    public void reloadData() {
        ServiceFactory.getGankServiceInstance().getDataOfTypeRx(GankService.GankType.福利.name(), mPageSize, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankResult<GankTypeData>>() {

                    @Override
                    public void onNext(GankResult<GankTypeData> gankResult) {
                        Log.v(TAG, "reloadData-onNext, Thread id=" + Thread.currentThread().getId());
                        Log.v(TAG, "reloadData, response:" + gankResult.toString());

                        if (gankResult.results.isEmpty()) {
                            mView.onLoadEmpty();
                        } else if (gankResult.results.size() < mPageSize) {
                            mView.fillData(gankResult.results);
                            mView.hasNoMoreData();
                        } else if (gankResult.results.size() == mPageSize) {
                            mView.fillData(gankResult.results);
                            ++mCurrentPage;
                        }
                        mView.loadDataOver();
                    }

                    @Override
                    public void onCompleted() {
                        Log.v(TAG, "reloadData-onCompleted, Thread id=" + Thread.currentThread().getId());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG, "reloadData-onError, Thread id=" + Thread.currentThread().getId());
                        mView.onLoadFailure(e);
                        mView.loadDataOver();
                    }
                });
    }

    public void loadMoreData() {
        ServiceFactory.getGankServiceInstance().getDataOfTypeRx(GankService.GankType.福利.name(), mPageSize, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankResult<GankTypeData>>() {

                    @Override
                    public void onNext(GankResult<GankTypeData> gankResult) {
                        Log.v(TAG, "loadMoreData-onNext, Thread id=" + Thread.currentThread().getId());
                        Log.v(TAG, "loadMoreData, response:" + gankResult.toString());

                        if (gankResult.results.isEmpty()) {
                            mView.hasNoMoreData();
                        } else if (gankResult.results.size() < mPageSize) {
                            mView.loadMoreData(gankResult.results);
                            mView.hasNoMoreData();
                        } else if (gankResult.results.size() == mPageSize) {
                            mView.loadMoreData(gankResult.results);
                            ++mCurrentPage;
                        }
                        mView.loadDataOver();
                    }

                    @Override
                    public void onCompleted() {
                        Log.v(TAG, "loadMoreData-onCompleted, Thread id=" + Thread.currentThread().getId());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG, "loadMoreData-onError, Thread id=" + Thread.currentThread().getId());
                        mView.onLoadFailure(e);
                        mView.loadDataOver();
                    }
                });
    }

}
