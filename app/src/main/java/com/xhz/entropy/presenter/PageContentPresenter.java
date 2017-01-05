package com.xhz.entropy.presenter;

import android.app.Activity;

import com.xhz.entropy.ui.view.IPageContentView;

/**
 * Created by xh.zeng on 2017/1/5.
 */

public class PageContentPresenter extends BasePresenter<IPageContentView>{

    public PageContentPresenter(Activity context, IPageContentView view) {
        super(context, view);
    }
}
