package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.fragment.FindFragment;
import com.xianrou.mohu.fragment.HomeFragment;
import com.xianrou.mohu.fragment.MineFragment;
import com.xianrou.mohu.fragment.PublishFragment;
import com.xianrou.mohu.util.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 咸鱼
 * @date 2017/1/14 0014
 * @des ${TODO}
 */

public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private LinearLayout llFgContainer;
    private RadioGroup radioGroupBottom;
    private RadioButton rbHome;
    private RadioButton rbFind;
    private RadioButton rbPublish;
    private RadioButton rbMine;
    private RadioButton rbEasy;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initFragment();
    }

    private void initView() {
        llFgContainer = (LinearLayout) findViewById(R.id.ll_fg_container);
        radioGroupBottom = (RadioGroup) findViewById(R.id.radioGroup_bottom);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbFind = (RadioButton) findViewById(R.id.rb_find);
        rbPublish = (RadioButton) findViewById(R.id.rb_publish);
        rbMine = (RadioButton) findViewById(R.id.rb_mine);
        rbEasy = (RadioButton) findViewById(R.id.rb_easy);
        radioGroupBottom.setOnCheckedChangeListener(this);
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        FindFragment findFragment = new FindFragment();
        PublishFragment publishFragment = new PublishFragment();
        MineFragment mineFragment = new MineFragment();
        mFragmentList.add(homeFragment);
        mFragmentList.add(findFragment);
        mFragmentList.add(publishFragment);
        mFragmentList.add(mineFragment);
        //默认显示首页
        change(AppConfig.FRAGMENT_HOME);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home :
                change(AppConfig.FRAGMENT_HOME);
                break;
            case R.id.rb_find :
                change(AppConfig.FRAGMENT_FIND);
                break;
            case R.id.rb_publish :
                change(AppConfig.FRAGMENT_PUBLISH);
                break;
            case R.id.rb_mine :
                change(AppConfig.FRAGMENT_MINE);
                break;
            case R.id.rb_easy :
                ActivityUtil.startActivity(this,MainActivity.class,true);
                break;
        }
    }

    private void change(int position) {
        for(int i = 0; i < radioGroupBottom.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroupBottom.getChildAt(i);
            radioButton.setSelected(false);
        }
        ((RadioButton) radioGroupBottom.getChildAt(position)).setSelected(true);
        replace(mFragmentList.get(position));
    }


    private void replace(Fragment f){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_fg_container,f);
        transaction.commitAllowingStateLoss();
    }
}
