package com.xhz.entropy.ui.view;

/**
 * Created by xh.zeng on 2017/1/7.
 */

public interface IImageView extends IMVPView {

    void saveSuccess(String msg);

    void saveFail(String error);
}
