package com.sslyxhz.entropy.ui.view;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public interface IMVPView {

    /**
     * 加载数据失败
     *
     * @param e
     */
    void onLoadFailure(Throwable e);

    /**
     * 加载数据为空
     */
    void onLoadEmpty();
}
