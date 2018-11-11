package com.sslyxhz.entropy;

import android.app.Application;

/**
 * Created by xh.zeng on 2017/1/2.
 */

public class App extends Application {
    private static App mInstance = new App();
    public boolean log = true;

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
