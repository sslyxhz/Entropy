package com.sslyxhz.entropy.presenter;

import com.sslyxhz.entropy.ui.view.IMVPView;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public interface IMVPPresenter<V extends IMVPView> {

    void attachView(V view);

    void detachView();
}
