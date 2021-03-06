package com.xianrou.mohu.widget.province;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.xianrou.mohu.R;
import com.xianrou.mohu.bean.CityEntity;
import com.xianrou.mohu.bean.DistrictEntity;
import com.xianrou.mohu.bean.ProvinceEntity;
import com.xianrou.mohu.service.XmlParserHelper;
import com.xianrou.mohu.widget.province.adapter.ArrayWheelAdapter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author 咸鱼
 * @date 2017/2/10 0010
 * @des ${TODO}
 */

public class SelectCity implements OnWheelChangedListener, View.OnClickListener {

    private Context mContext;
    private View parentView;
    private View popupview;

    private WheelView mViewProvince;//省
    private WheelView mViewCity;    //市
    private WheelView mViewDistrict;//区
    private Button mBtnConfirm;     //确定

    /**所有省**/
    private String[] mProvinceDatas;
    /**
     * key-省 value-市
     */
    private Map<String,String[]> mCitiesDatasMap = new HashMap<>();
    /**
     * key-市 value-区
     */
    private Map<String,String[]> mDistrictDatasMap = new HashMap<>();
    /**
     * key-区 values-邮编
     */
    protected Map<String, String> mZipcodeDatasMap = new HashMap<>();
    /**当前省的名称**/
    private String mCurrentProviceName;
    /**当前市的名称**/
    private String mCurrentCityName;
    /**当前区的名称**/
    protected String mCurrentDistrictName ="";
    /**当前区的邮政编码**/
    protected String mCurrentZipCode ="";

    private CitySelectListener listener;
    private PopupWindow mPopupWindow;

    public SelectCity(Context context,View v){
        this.mContext = context;
        parentView = v;
    }


    @SuppressWarnings("deprecation")
    @SuppressLint("InflateParams")
    public SelectCity build(){
        // PopView
        popupview = LayoutInflater.from(mContext).inflate(R.layout.popup_province, null);
        initView(popupview);
        mPopupWindow = new PopupWindow(popupview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置弹窗背景
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        mPopupWindow.setBackgroundDrawable(dw);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        popupview.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float rawY = event.getRawY();
                int height = popupview.findViewById(R.id.popup_layout).getTop();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(rawY > height){
                        dismiss();
                    }
                }
                    return true;
            }
        });
        return this;
    }


    public  void initView(View contentView){
        mViewProvince = (WheelView) contentView.findViewById(R.id.id_province);
        mViewCity = (WheelView) contentView.findViewById(R.id.id_city);
        mViewDistrict = (WheelView)contentView.findViewById(R.id.id_district);
        contentView.findViewById(R.id.btn_confirm).setOnClickListener(this);


        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);


        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<>(mContext, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }


    /**
     * 显示
     */
    @SuppressLint("NewApi")
    public void show(){
        if (null != mPopupWindow){
            mPopupWindow.showAtLocation(parentView, Gravity.BOTTOM,0,0);
        }
    }

    /**
     * 撤销
     */
    public void dismiss(){
        if (null != mPopupWindow){
            mPopupWindow.dismiss();
        }
    }

    /**
     * 设置背景透明度
     * @param alpha 背景的Alpha
     */
    private void setBackgroundAlpha(float alpha){
        WindowManager.LayoutParams lp =  ((Activity)mContext).getWindow().getAttributes();
        lp.alpha = alpha;
        ((Activity)mContext).getWindow().setAttributes(lp);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitiesDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(mContext, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }
    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitiesDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[] { "" };
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<>(mContext, areas));
        mViewDistrict.setCurrentItem(0);
        mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
    }

    protected void initProvinceDatas() {
        List<ProvinceEntity> provinceList = null;
        AssetManager asset = mContext.getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHelper handler = new XmlParserHelper();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getProvinceEntityList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList!= null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityEntity> cityList = provinceList.get(0).getCityList();
                if (cityList!= null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictEntity> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i=0; i< provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityEntity> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j=0; j< cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictEntity> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictEntity[] distrinctArray = new DistrictEntity[districtList.size()];
                    for (int k=0; k<districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictEntity districtModel = new DistrictEntity(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitiesDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm :
                StringBuilder builder = new StringBuilder();
                builder.append(mCurrentProviceName);
                builder.append(mCurrentCityName);
                builder.append(mCurrentDistrictName);
                if(listener != null){
                    listener.selectOk(builder);
                }
                dismiss();
                break;
        }
    }

    //选取完成回调的接口
    public interface CitySelectListener {
        public void selectOk(StringBuilder builder);
    }

    public void setListener(CitySelectListener listener) {
        this.listener = listener;
    }
}
