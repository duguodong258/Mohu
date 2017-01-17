package com.xianrou.mohu.util;

import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.xianrou.mohu.exception.PayException;
import com.xianrou.mohu.exception.TipException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author 咸鱼
 * @date 2017/1/17
 * @des ${TODO}
 */

public class NetUtil {

    public static void checkHttpException(Context context,Throwable throwable){
        if(throwable instanceof UnknownHostException){
            ToastUtil.showToast(context,"网络异常");
        }else if (throwable instanceof JsonSyntaxException) {
            ToastUtil.showToast(context, "数据异常");
        } else if (throwable instanceof SocketTimeoutException) {
            ToastUtil.showToast(context, "连接超时");
        } else if (throwable instanceof ConnectException) {
            ToastUtil.showToast(context, "连接服务器失败");
        } else if (throwable instanceof TipException) {
            ToastUtil.showToast(context, ((TipException) throwable).tip);
        } else if (throwable instanceof PayException) {
            ToastUtil.showToast(context, ((PayException) throwable).tip);
        } else {
            ToastUtil.showToast(context, "操作失败");
        }/* else if (mThrowable instanceof RuntimeException) {
            ToastUtil.showToast(mContext,"程序出错");
        } else {
            ToastUtil.showToast(mContext,"网络异常");
        }*/

        LogUtil.e("okhttp", throwable.toString());
        throwable = null;
    }
}
