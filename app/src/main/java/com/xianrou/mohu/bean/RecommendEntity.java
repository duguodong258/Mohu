package com.xianrou.mohu.bean;

/**
 * @author 咸鱼
 * @date 2017/2/15 0015
 * @des ${TODO}
 */

public class RecommendEntity {
    public int img;
    public String name;

    public RecommendEntity(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RecommendEntity{" +
                "img=" + img +
                ", name='" + name + '\'' +
                '}';
    }
}
