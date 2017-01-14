package com.xianrou.mohu.base;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author 咸鱼
 * @date 2017/1/14 0014
 * @des ${TODO}
 */

public class MohuApplication extends Application {

    Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        Fresco.initialize(appContext);
    }
}
