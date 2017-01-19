package com.xianrou.mohu.util;

import android.content.Context;

/**
 * @author 咸鱼
 * @date 2017/1/19 0019
 * @des ${TODO}
 */

public class DeviceUtil {

    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
