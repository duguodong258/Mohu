package com.xianrou.mohu.exception;

/**
 * @author 咸鱼
 * @date 2017/1/17
 * @des ${TODO}
 */

public class PayException extends Exception {

    public String tip;

    public PayException(String tip) {
        this.tip = tip;
    }
}
