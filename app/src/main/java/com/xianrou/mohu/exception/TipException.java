package com.xianrou.mohu.exception;

/**
 * @author 咸鱼
 * @date 2017/1/17 0017
 * @des ${TODO}
 */

public class TipException extends RuntimeException {

    public String tip;

    public TipException(String tip) {
        if (tip == null)
            tip = "请稍候再试";
        this.tip = tip;
    }
}
