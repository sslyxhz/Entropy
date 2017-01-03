package com.xhz.entropy.presenter;

import com.xhz.entropy.ui.view.IMVPView;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public interface IMVPPresenter<V extends IMVPView> {

    void attachView(V view);

    void detachView();
}
