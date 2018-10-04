package com.xhz.entropy.ui.view;

import com.xhz.entropy.data.bean.GankTypeData;

import java.util.List;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public interface IDataByDayView<T extends GankTypeData> extends ISwipeRefreshView {

    void fillData(List<T> data);

    void loadMoreData(List<T> data);

    void hasNoMoreData();
}