package com.sslyxhz.entropy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sslyxhz.entropy.Constant;
import com.sslyxhz.entropy.R;
import com.sslyxhz.entropy.presenter.ImagePresenter;
import com.sslyxhz.entropy.ui.view.IImageView;

import butterknife.BindView;

/**
 * Created by xh.zeng on 2017/1/7.
 */

public class ImageActivity extends BaseActivity<ImagePresenter> implements IImageView {

    @BindView(R.id.iv_activity_image)
    ImageView mIvGirlPhoto;

    private String mImageUrl;

    private void loadImage(){
        Glide.with(this)
                .load(mImageUrl)
                .into(mIvGirlPhoto);
    }

    public static void actionStart(Context context, String url){
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(Constant.EXTRA_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        if(getIntent()!=null){
            mImageUrl = getIntent().getStringExtra(Constant.EXTRA_URL);
        }
        loadImage();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ImagePresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_image;
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
