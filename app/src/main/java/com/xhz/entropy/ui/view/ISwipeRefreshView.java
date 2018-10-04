package com.xhz.entropy.ui.view;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public interface ISwipeRefreshView extends IMVPView {

    void loadDataOver();

    void showRefresh();

    void hideRefresh();
}
