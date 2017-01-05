package com.xhz.entropy.presenter;

import android.app.Activity;

import com.xhz.entropy.data.GankResult;
import com.xhz.entropy.data.bean.GankTypeData;
import com.xhz.entropy.net.ServiceFactory;
import com.xhz.entropy.ui.view.ITypeDataView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 分类数据
 * Created by xh.zeng on 2017/1/5.
 */

public class TypeDataPresenter extends BasePresenter<ITypeDataView> {

    public static final String TAG = TypeDataPresenter.class.getSimpleName();

    private int mCurrentPage = 1;
    private int mPageSize = 20;
    private String mTypeName;

    public TypeDataPresenter(Activity context, ITypeDataView view, String mTypeName) {
        super(context, view);
        this.mTypeName = mTypeName;
    }

    public void reloadData() {
        ServiceFactory.getGankServiceInstance().getDataOfTypeRx(mTypeName, mPageSize, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankResult<GankTypeData>>() {

                    @Override
                    public void onNext(GankResult<GankTypeData> gankResult) {
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

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onLoadFailure(e);
                        mView.loadDataOver();
                    }
                });
    }

    public void loadMoreData() {
        ServiceFactory.getGankServiceInstance().getDataOfTypeRx(mTypeName, mPageSize, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankResult<GankTypeData>>() {

                    @Override
                    public void onNext(GankResult<GankTypeData> gankResult) {
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

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onLoadFailure(e);
                        mView.loadDataOver();
                    }
                });
    }

    public void resetCurrentPage() {
        mCurrentPage = 1;
    }

    public boolean shouldReloadData() {
        return mCurrentPage <= 2;
    }
}
