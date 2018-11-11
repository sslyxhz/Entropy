package com.sslyxhz.entropy.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by xh.zeng on 2017/1/8.
 */

public class DisplayUtil {

    // 屏幕宽度
    public static int getWidth(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
//        int sWidth = metrics.widthPixels;
//        int sHeight = metrics.heightPixels;
        return metrics.widthPixels;
    }
}
