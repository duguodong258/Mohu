package com.xianrou.mohu.util;

import android.util.Log;

/**
 * @author 咸鱼
 * @date 2017/1/17
 * @des ${TODO}
 */
public class LogUtil {

    private static boolean ifLog = true;//是否开启log

    public static void closeLog(){
        ifLog = false;
    }

    public static void openLog(){
        ifLog = true;
    }


    public static void v(String tag,String msg){
        if(ifLog)
            Log.v(tag,msg);
    }

    public static void d(String tag,String msg){
        if(ifLog)
            Log.v(tag,msg);
    }

    public static void i(String tag,String msg){
        if(ifLog)
            Log.v(tag,msg);
    }

    public static void w(String tag,String msg){
        if(ifLog)
            Log.v(tag,msg);
    }

    public static void e(String tag,String msg){
        if(ifLog)
            Log.v(tag,msg);
    }

    /***************************************************下面是tag为类名*************************************************/
    public static void v(String msg){
        if(ifLog)
            Log.v(getTag(),msg);
    }

    public static void i(String msg){
        if(ifLog)
            Log.v(getTag(),msg);
    }

    public static void w(String msg){
        if(ifLog)
            Log.v(getTag(),msg);
    }

    public static void e(String msg){
        if(ifLog)
            Log.v(getTag(),msg);
    }

    public static void d(String msg){
        if(ifLog)
            Log.v(getTag(),msg);
    }


    private static String getTag() {
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String callingClass="";
        for(int i = 2; i < stackTrace.length; i++) {
            Class<?> clz = stackTrace[i].getClass();
            if(!clz.equals(LogUtil.class)){
                callingClass = stackTrace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.')+1);
                break;
            }
        }
        return callingClass;
    }
}
