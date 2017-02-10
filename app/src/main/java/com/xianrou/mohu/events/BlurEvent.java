package com.xianrou.mohu.events;

/**
 * @author 咸鱼
 * @date 2017/2/6 0006
 * @des 照片模糊值
 */

public class BlurEvent {

    private int blur;

    public BlurEvent(int blur) {
        this.blur = blur;
    }

    public int getBlur() {
        return blur;
    }
}
