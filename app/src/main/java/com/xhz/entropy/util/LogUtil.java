package com.xhz.entropy.util;

import com.orhanobut.logger.Logger;

/**
 * Created by xh.zeng on 2017/1/7.
 */

public class LogUtil {
    public static final int LOG_LEVEL = 0;

    public static final int LEVEL_VERBOSE = 1;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_WARN = 4;
    public static final int LEVEL_ERROR = 5;
    public static final int LEVEL_WTF = 6;

    public static void v(String msg){
        if(LEVEL_VERBOSE > LOG_LEVEL){
            Logger.v(msg);
        }
    }

    public static void d(String msg){
        if(LEVEL_DEBUG > LOG_LEVEL){
            Logger.d(msg);
        }
    }

    public static void i(String msg){
        if(LEVEL_INFO > LOG_LEVEL){
            Logger.i(msg);
        }
    }

    public static void w(String msg){
        if(LEVEL_WARN > LOG_LEVEL){
            Logger.w(msg);
        }
    }

    public static void e(String msg){
        if(LEVEL_ERROR > LOG_LEVEL){
            Logger.e(msg);
        }
    }

    public static void wtf(String msg){
        if(LEVEL_WTF > LOG_LEVEL){
            Logger.wtf(msg);
        }
    }
}
