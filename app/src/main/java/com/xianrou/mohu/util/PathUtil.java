package com.xianrou.mohu.util;

import android.net.Uri;
import android.text.format.DateFormat;

import java.io.File;
import java.util.Date;

public class PathUtil {
    public static String getUrlLastName(String url) {
        String[] strs = url.split("/");

        return strs[strs.length - 1];
    }

    public static File getRandomFile(String filePath, String baseWord, String endWord) {
        File dirFile = new File(filePath);
        if (!dirFile.exists()) {
            if (!dirFile.mkdirs()) {
                return null;
            }
        }
        String timeStamp = DateFormat.format("yyyyMMdd_HHmmss", new Date()).toString();
        File mediaFile;
        mediaFile = new File(dirFile.getPath() + File.separator + baseWord + "_" + timeStamp + "." + endWord);
        return mediaFile;
    }

    public static File getRandomFile(String filePath, String dirName, String baseWord, String endWord) {
        File dirFile = new File(filePath, dirName);
        if (!dirFile.exists()) {
            if (!dirFile.mkdirs()) {
                return null;
            }
        }
        String timeStamp = DateFormat.format("yyyyMMdd_HHmmss", new Date()).toString();
        File mediaFile;
        mediaFile = new File(dirFile.getPath() + File.separator + baseWord + "_" + timeStamp + "." + endWord);
        return mediaFile;
    }

    public static Uri getRandomUri(String filePath, String baseWord, String endWord) {
        return Uri.fromFile(getRandomFile(filePath, baseWord, endWord));
    }

    /**
     * 分隔符替换
     * window下测试通过
     *
     * @param path
     * @return
     */
    public static String separatorReplace(String path) {
        return path.replace("\\", File.separator);
    }

}
