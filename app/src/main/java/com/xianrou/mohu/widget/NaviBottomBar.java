package com.xianrou.mohu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.MohuApplication;

/**
 * @author 咸鱼
 * @date 2017/2/14 0014
 * @des 首页底部栏
 */

public class NaviBottomBar extends LinearLayout implements View.OnClickListener {

    private LinearLayout mHome;
    private LinearLayout mFind;
    private LinearLayout mMine;
    private LinearLayout mEasy;
    private ImageView tabPublish;
    private View selectedView;
    private OnTabClickListener mListener;

    public NaviBottomBar(Context context) {
        super(context);
        init();
    }

    public NaviBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.navi_bottom_bar, this, true);
        mHome = (LinearLayout) v.findViewById(R.id.tab_home);
        mFind = (LinearLayout) v.findViewById(R.id.tab_find);
        tabPublish = (ImageView) v.findViewById(R.id.tab_publish);
        mMine = (LinearLayout) v.findViewById(R.id.tab_me);
        mEasy = (LinearLayout) v.findViewById(R.id.tab_easy);

        mHome.setOnClickListener(this);
        mFind.setOnClickListener(this);
        tabPublish.setOnClickListener(this);
        mMine.setOnClickListener(this);
        mEasy.setOnClickListener(this);

        selectedView = mHome;
        mHome.setSelected(true);
    }

    public void setOnTabClickListener(OnTabClickListener listener) {
        mListener = listener;
    }


    //首页
    public void setHomeSelected() {
        selectedView.setSelected(false);
        mHome.setSelected(true);
        selectedView = mHome;
    }

    //发现
    public void setFindSelcted() {
        selectedView.setSelected(false);
        mFind.setSelected(true);
        selectedView = mFind;
    }

    //我的
    public void setMeSelcted() {
        selectedView.setSelected(false);
        mMine.setSelected(true);
        selectedView = mMine;
    }

    //轻巧
    public void setEasySelcted() {
        selectedView.setSelected(false);
        mEasy.setSelected(true);
        selectedView = mEasy;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_home :
                selectedView.setSelected(false);
                mHome.setSelected(true);
                selectedView = mHome;
                if(mListener != null){
                    mListener.onHomeClick();
                }
                break;
            case R.id.tab_find :
                selectedView.setSelected(false);
                mFind.setSelected(true);
                selectedView = mFind;
                if(mListener != null){
                    mListener.onFindClick();
                }
                break;
            case R.id.tab_publish :
                if(mListener != null){
                    mListener.onPublishClick();
                }
                break;
            case R.id.tab_me :
                if(MohuApplication.getsInstance().isLogin()){
                    selectedView.setSelected(false);
                    mMine.setSelected(true);
                    selectedView = mMine;
                }
                if(mListener != null){
                    mListener.onMeClick();
                }
                break;
            case R.id.tab_easy :
                selectedView.setSelected(false);
                mEasy.setSelected(true);
                selectedView = mEasy;
                if(mListener != null){
                    mListener.onEasyClick();
                }
                break;
        }
    }



    public interface OnTabClickListener {

        void onHomeClick();

        void onFindClick();

        void onPublishClick();

        void onMeClick();

        void onEasyClick();
    }
}
