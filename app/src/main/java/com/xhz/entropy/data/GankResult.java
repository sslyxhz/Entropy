package com.xhz.entropy.data;

import java.util.List;

/**
 * 一般数据形式
 * Created by xh.zeng on 2017/1/2.
 */

public class GankResult<T> {
    public boolean error;
    public List<T> results;

    @Override
    public String toString() {
        return "GankResult{" +
                "error=" + error +
                ", results='" + results +
                "}\n";
    }
}
