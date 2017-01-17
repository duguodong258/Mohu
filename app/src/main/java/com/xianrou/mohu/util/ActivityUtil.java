package com.xianrou.mohu.util;

import android.app.Activity;
import android.content.Intent;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;

/**
 * @author 咸鱼
 * @date 2017/1/16
 * @des ${TODO}
 */

public class ActivityUtil {

    public static void startActivity(Activity activity, Class<? extends BaseActivity> clz){
        Intent intent = new Intent(activity,clz);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity, Class<? extends BaseActivity> clz,int flag){
        Intent intent = new Intent(activity,clz);
        intent.putExtra("flag",flag);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
    }

    public static void startActivity(Activity activity, Class<? extends BaseActivity> clz, boolean isFinishSelf){
        Intent intent = new Intent(activity,clz);
        activity.startActivity(intent);
        if(isFinishSelf) {
            activity.finish();
        }
    }
}
