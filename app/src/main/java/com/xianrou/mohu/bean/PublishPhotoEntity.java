package com.xianrou.mohu.bean;

import java.io.Serializable;

/**
 * @author 咸鱼
 * @date 2017/2/6 0006
 * @des ${TODO}
 */

public class PublishPhotoEntity implements Serializable {

    public int image;

    public PublishPhotoEntity(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}