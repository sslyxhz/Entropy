package com.xhz.entropy.ui.view;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public interface IGirlPhotoView extends IMVPView {

    void saveSuccess(String msg);

    void saveFail(String error);
}
