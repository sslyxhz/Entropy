package com.xhz.entropy.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xhz.entropy.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;

    protected abstract void initPresenter();

    protected abstract void init();

    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        init();
        initPresenter();
        checkPresenterIsNull();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    public void setTitle(String strTitle, boolean showHome) {
        setTitle(strTitle);
//        getSupportActionBar().setDisplayShowHomeEnabled(showHome);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(showHome);
    }
}
