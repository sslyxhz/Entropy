package com.xhz.entropy.presenter;

import android.app.Activity;
import android.content.Context;

import com.xhz.entropy.ui.view.IMVPView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public abstract class BasePresenter<V extends IMVPView> implements IMVPPresenter<V> {

    protected V mView;
    protected Context mContext;
    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter(Activity context, V view) {
        mContext = context;
        mView = view;
    }

    public BasePresenter(Context context, V view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
        this.mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        this.mView = null;
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public V getMvpView() {
        return mView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }
}
