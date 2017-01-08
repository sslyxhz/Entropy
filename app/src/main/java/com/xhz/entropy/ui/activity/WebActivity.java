package com.xhz.entropy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xhz.entropy.Constant;
import com.xhz.entropy.R;
import com.xhz.entropy.presenter.WebPresenter;
import com.xhz.entropy.ui.view.IWebView;

import butterknife.BindView;

/**
 * Created by xh.zeng on 2017/1/8.
 */

public class WebActivity extends BaseActivity<WebPresenter> implements IWebView {

    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.wv_content) WebView mWebView;

    private String mUrl, mDesc;

    public static void actionStart(Context context, String url, String desc){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(Constant.EXTRA_URL, url);
        intent.putExtra(Constant.EXTRA_DESC, desc);
        context.startActivity(intent);
    }
    @Override
    protected void initPresenter() {
        mPresenter = new WebPresenter(this, this);
    }

    @Override
    protected void init() {
        mUrl = getIntent().getStringExtra(Constant.EXTRA_URL);
        mDesc = getIntent().getStringExtra(Constant.EXTRA_DESC);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setAppCacheEnabled(true);

        mWebView.loadUrl(mUrl);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void onLoadFailure(Throwable e) {

    }

    @Override
    public void onLoadEmpty() {

    }
}
