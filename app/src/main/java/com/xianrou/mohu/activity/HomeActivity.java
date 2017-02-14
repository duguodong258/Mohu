package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.fragment.FindFragment;
import com.xianrou.mohu.fragment.HomeFragment;
import com.xianrou.mohu.fragment.MineFragment;
import com.xianrou.mohu.util.ActivityUtil;
import com.xianrou.mohu.widget.BottomPopupWindow;
import com.xianrou.mohu.widget.NaviBottomBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 咸鱼
 * @date 2017/1/14 0014
 * @des ${TODO}
 */

public class HomeActivity extends BaseActivity implements NaviBottomBar.OnTabClickListener, View.OnClickListener {

    private LinearLayout llFgContainer;
    private List<Fragment> mFragmentList;
    public NaviBottomBar mBottomBar;
    private HomeFragment mHomeFragment;
    private FindFragment mFindFragment;
    private MineFragment mMineFragment;
    private BottomPopupWindow mPopupWindow;
    private RelativeLayout rlRoot;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initFragment();
    }

    private void initView() {
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        llFgContainer = (LinearLayout) findViewById(R.id.ll_fg_container);
        mBottomBar = (NaviBottomBar) findViewById(R.id.navi_bottom_bar);
        mBottomBar.setOnTabClickListener(this);
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mHomeFragment = HomeFragment.getInstance();
        mFindFragment = FindFragment.getInstance();
        mMineFragment = MineFragment.getInstance();
        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mFindFragment);
        mFragmentList.add(mMineFragment);
        //默认显示首页
        replace(mHomeFragment);
        mBottomBar.setHomeSelected();
    }



    @Override
    public void onHomeClick() {
        replace(mHomeFragment);
        mBottomBar.setHomeSelected();
    }

    @Override
    public void onFindClick() {
        replace(mFindFragment);
        mBottomBar.setFindSelcted();
    }

    @Override
    public void onPublishClick() {
        showPop();
    }

    @Override
    public void onMeClick() {
        replace(mMineFragment);
        mBottomBar.setMeSelcted();
    }

    @Override
    public void onEasyClick() {
        ActivityUtil.startActivity(this,MainActivity.class,true);
    }


    private void replace(Fragment f){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_fg_container,f);
        transaction.commitAllowingStateLoss();
    }

    private void showPop() {
        mPopupWindow = new BottomPopupWindow(mContext, this);
        mPopupWindow.setFirstItemName("发布相册");
        mPopupWindow.setSecondItemName("发布视频");
        mPopupWindow.showAtLocation(rlRoot, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_firstItem://发布相册
                mPopupWindow.dismiss();
                ActivityUtil.startActivity(this,PublishPhotoActivity.class, AppConfig.PUBLISH_PHOTO);
                break;
            case R.id.btn_secondItem://发布视频
                mPopupWindow.dismiss();
                ActivityUtil.startActivity(this,PublishVideoActivity.class,AppConfig.PUBLISH_VIDEO);
                break;
        }
    }
}
