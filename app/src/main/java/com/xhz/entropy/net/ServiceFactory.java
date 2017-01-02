package com.xhz.entropy.net;

/**
 * Created by xh.zeng on 2017/1/2.
 */

public class ServiceFactory {

    private static GankService mGankService;

    protected static final Object monitor = new Object();

    public static GankService getGankServiceInstance() {
        synchronized (monitor) {
            if (mGankService == null) {
                mGankService = new RetrofitManager().getGankService();
            }
            return mGankService;
        }
    }
}
