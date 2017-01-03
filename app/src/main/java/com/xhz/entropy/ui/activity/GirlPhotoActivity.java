package com.xhz.entropy.ui.activity;

import android.widget.ImageView;

import com.xhz.entropy.R;
import com.xhz.entropy.presenter.GirlPhotoPresenter;
import com.xhz.entropy.ui.view.IGirlPhotoView;

import butterknife.BindView;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public class GirlPhotoActivity extends BaseActivity<GirlPhotoPresenter> implements IGirlPhotoView {

    @BindView(R.id.iv_girl_photo)
    ImageView mIvGirlPhoto;

    private String url;

    @Override
    protected void initPresenter() {
        mPresenter = new GirlPhotoPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_girlphoto;
    }

    @Override
    public void onLoadFailure(Throwable e) {

    }

    @Override
    public void onLoadEmpty() {

    }

    @Override
    public void saveSuccess(String msg) {

    }

    @Override
    public void saveFail(String error) {

    }
}
