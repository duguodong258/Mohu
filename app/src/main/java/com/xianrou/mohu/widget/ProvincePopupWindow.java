package com.xianrou.mohu.widget;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.xianrou.mohu.R;
import com.xianrou.mohu.bean.CityEntity;
import com.xianrou.mohu.bean.DistrictEntity;
import com.xianrou.mohu.bean.ProvinceEntity;
import com.xianrou.mohu.service.XmlParserHelper;
import com.xianrou.mohu.widget.province.OnWheelChangedListener;
import com.xianrou.mohu.widget.province.WheelView;
import com.xianrou.mohu.widget.province.adapter.ArrayWheelAdapter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author 咸鱼
 * @date 2017/2/8 0008
 * @des ${TODO}
 */

public class ProvincePopupWindow extends PopupWindow implements OnWheelChangedListener, View.OnClickListener {

    private Context mContext;
    private final View mPopup_layout;
    View mLlRoot;//父布局
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

    public ProvincePopupWindow(Context context,View llRoot) {
        super(context);
        mLlRoot = llRoot;
        mContext = context;
        mPopup_layout = LayoutInflater.from(context).inflate(R.layout.popup_province, null);
        initView(mPopup_layout);

        this.setContentView(mPopup_layout);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        mPopup_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int height = mPopup_layout.findViewById(R.id.popup_layout).getTop();
                float rawY = event.getRawY();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(rawY > height){
                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.popupwindow_out);
                        mPopup_layout.startAnimation(animation);
                        //退出动画执行完毕后dismiss();
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                dismiss();
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }
                }
                return true;
            }
        });
        show();
    }

    private void initView(View contentView) {
        mViewProvince = (WheelView) mPopup_layout.findViewById(R.id.id_province);
        mViewCity = (WheelView) mPopup_layout.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) mPopup_layout.findViewById(R.id.id_district);
        mBtnConfirm = (Button) mPopup_layout.findViewById(R.id.btn_confirm);
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mBtnConfirm.setOnClickListener(this);

        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }


    public void show() {
        mPopup_layout.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.popupwindow_in));
    }

//    @Override
//    public void showAtLocation(View parent, int gravity, int x, int y) {
//        show();
//        super.showAtLocation(mLlRoot, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
//    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
        } else if (wheel == mViewCity) {
            updateAreas();
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
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
        mViewCity.setViewAdapter(new ArrayWheelAdapter<>(mContext, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                showSelectedResult();
                break;
            default:
                break;
        }
    }

    private void showSelectedResult() {
        Toast.makeText(mContext, "当前选中:"+mCurrentProviceName+","+mCurrentCityName+","
                +mCurrentDistrictName+","+mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }


    /**
     * 解析省市区的XML数据
     */
    private void initProvinceDatas(){
        List<ProvinceEntity> provinceList = null;
        try {
            AssetManager assets = mContext.getAssets();
            InputStream input = assets.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            XmlParserHelper helper = new XmlParserHelper();
            saxParser.parse(input,helper);
            input.close();
            // 获取解析出来的数据
            provinceList = helper.getProvinceEntityList();

            //初始化默认选中的省、市、区
            if(provinceList != null && !provinceList.isEmpty()){
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityEntity> cityList = provinceList.get(0).getCityList();
                if(cityList != null && !cityList.isEmpty()){
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictEntity> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            mProvinceDatas = new String[provinceList.size()];
            for(int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityEntity> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for(int j = 0; j < cityList.size(); j++) {
                    cityNames[i] = cityList.get(j).getName();
                    List<DistrictEntity> districtList = cityList.get(j).getDistrictList();
                    String[] districtNames = new String[districtList.size()];
                    DistrictEntity[] districtEntities = new DistrictEntity[districtList.size()];
                    for(int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictEntity districtEntity = new DistrictEntity(districtList.get(k).getName(),districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        districtEntities[k] = districtEntity;
                        districtNames[k] = districtEntity.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mCitiesDatasMap.put(cityNames[j],districtNames);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitiesDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
