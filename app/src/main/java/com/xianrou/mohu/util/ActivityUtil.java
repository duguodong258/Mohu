package com.xianrou.mohu.util;

import android.app.Activity;
import android.content.Intent;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;

import java.io.Serializable;
import java.util.List;

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

    //需要传递集合的跳转
    public static void startActivity(Activity activity, Class<? extends BaseActivity> clz, List datas,String flag){
        Intent intent = new Intent(activity,clz);
        intent.putExtra(flag,(Serializable)datas);
        activity.startActivity(intent);
    }

    //传递标记的跳转
    public static void startActivity(Activity activity, Class<? extends BaseActivity> clz,int flag){
        Intent intent = new Intent(activity,clz);
        intent.putExtra("flag",flag);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    //是否关闭自己的跳转
    public static void startActivity(Activity activity, Class<? extends BaseActivity> clz, boolean isFinishSelf){
        Intent intent = new Intent(activity,clz);
        activity.startActivity(intent);
        if(isFinishSelf) {
            activity.finish();
        }
    }



    public static void startActivityForResult(Activity activity, Class<? extends BaseActivity> clz,int reqCode){
        Intent intent = new Intent(activity,clz);
        intent.putExtra("reqCode",reqCode);
        activity.startActivityForResult(intent,reqCode);
    }
}
