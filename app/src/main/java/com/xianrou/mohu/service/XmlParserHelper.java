package com.xianrou.mohu.service;

import com.xianrou.mohu.bean.CityEntity;
import com.xianrou.mohu.bean.DistrictEntity;
import com.xianrou.mohu.bean.ProvinceEntity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 咸鱼
 * @date 2017/2/9 0009
 * @des ${TODO}
 */

public class XmlParserHelper extends DefaultHandler {
    /**
     * 存储所有的解析对象
     */
    private List<ProvinceEntity> mProvinceEntityList = new ArrayList<>();

    public List<ProvinceEntity> getProvinceEntityList() {
        return mProvinceEntityList;
    }

    private ProvinceEntity mProvinceEntity;//省
    private CityEntity mCityEntity;        //市
    private DistrictEntity mDistrictEntity;//县

    public XmlParserHelper() {
    }

    @Override
    public void startDocument() throws SAXException {
        // 当读到第一个开始标签的时候，会触发这个方法
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 当遇到开始标记的时候，调用这个方法
        if (qName.equals("province")) {
            mProvinceEntity = new ProvinceEntity();
            mProvinceEntity.setName(attributes.getValue(0));
            mProvinceEntity.setCityList(new ArrayList<CityEntity>());
        } else if (qName.equals("city")) {
            mCityEntity = new CityEntity();
            mCityEntity.setName(attributes.getValue(0));
            mCityEntity.setDistrictList(new ArrayList<DistrictEntity>());
        } else if (qName.equals("district")) {
            mDistrictEntity = new DistrictEntity();
            mDistrictEntity.setName(attributes.getValue(0));
            mDistrictEntity.setZipcode(attributes.getValue(1));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // 遇到结束标记的时候，会调用这个方法
        if(qName.equals("district")){
            mCityEntity.getDistrictList().add(mDistrictEntity);
        }else if(qName.equals("city")){
            mProvinceEntity.getCityList().add(mCityEntity);
        }else if(qName.equals("province")){
            mProvinceEntityList.add(mProvinceEntity);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    }
}
