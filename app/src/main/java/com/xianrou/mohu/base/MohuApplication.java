package com.xianrou.mohu.base;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * @author 咸鱼
 * @date 2017/1/14 0014
 * @des ${TODO}
 */

public class MohuApplication extends Application {

    Context appContext;
    private static MohuApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        appContext = this;
        mInstance = this;
        AutoLayoutConifg.getInstance().useDeviceSize();
        Fresco.initialize(appContext);
    }

    public static MohuApplication getsInstance() {
        return mInstance;
    }

    public boolean isLogin() {
//        return -1 != SharePrefUtils.getId(this);
        return false;
    }
}
