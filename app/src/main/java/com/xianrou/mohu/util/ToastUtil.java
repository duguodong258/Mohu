package com.xianrou.mohu.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author 咸鱼
 * @date 2017/1/17 0017
 * @des ${TODO}
 */

public class ToastUtil {

    private static Toast mToast;

    public static void showToast(Context context,String msg){
        if(mToast == null){
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showToast(Context context,int res){
        if(mToast == null){
            mToast = Toast.makeText(context, res, Toast.LENGTH_SHORT);
        }
        mToast.setText(context.getResources().getString(res));
        mToast.show();
    }

}
