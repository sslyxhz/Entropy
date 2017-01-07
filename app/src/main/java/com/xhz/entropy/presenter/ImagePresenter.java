package com.xhz.entropy.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.xhz.entropy.ui.view.IImageView;

/**
 * Created by xh.zeng on 2017/1/7.
 */

public class ImagePresenter extends BasePresenter<IImageView>{

    private String mGrilImageUrl;

    public ImagePresenter(Activity context, IImageView view) {
        super(context, view);
    }

    public String getSDPath(){
        return Environment.getExternalStorageDirectory().toString();
    }

    public void savePhoto(String url){
        if(!TextUtils.isEmpty(url)){
            String fileName = url.substring(url.lastIndexOf("/")+1);
            savePhotoToSdCard(mContext, url, fileName);
        }
    }

    private void savePhotoToSdCard(final Context context, final  String url, final  String title) {

    }

    public String getmGrilImageUrl() {
        return mGrilImageUrl;
    }

    public void setmGrilImageUrl(String mGrilImageUrl) {
        this.mGrilImageUrl = mGrilImageUrl;
    }
}
