package com.xianrou.mohu.bean;

import java.util.List;

/**
 * @author 咸鱼
 * @date 2017/2/9 0009
 * @des 市 实体
 */
public class CityEntity {
    private String name;
    private List<DistrictEntity> districtList;

    public CityEntity() {
        super();
    }

    public CityEntity(List<DistrictEntity> districtList, String name) {
        this.districtList = districtList;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictEntity> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictEntity> districtList) {
        this.districtList = districtList;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "name='" + name + '\'' +
                ", districtList=" + districtList +
                '}';
    }
}
