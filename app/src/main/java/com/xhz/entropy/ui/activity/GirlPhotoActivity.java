package com.xhz.entropy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xhz.entropy.Constant;
import com.xhz.entropy.R;
import com.xhz.entropy.presenter.GirlPhotoPresenter;
import com.xhz.entropy.ui.view.IGirlPhotoView;

import butterknife.BindView;

/**
 * 图片展示界面
 * Created by xh.zeng on 2017/1/3.
 */

public class GirlPhotoActivity extends BaseActivity<GirlPhotoPresenter> implements IGirlPhotoView {

    @BindView(R.id.iv_girl_photo)
    ImageView mIvGirlPhoto;

    private String mImageUrl;

    private void loadImage(){
        Glide.with(this)
                .load(mImageUrl)
                .centerCrop()
                .into(mIvGirlPhoto);
    }

    public static void actionStart(Context context, String url){
        Intent intent = new Intent(context, GirlPhotoActivity.class);
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
