package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.fragment.TestFragment;
import com.xianrou.mohu.widget.TabPageIndicator;

/**
 * @author 咸鱼
 * @date 2017/1/14
 * @des 标识码
 */

public class CodeActivity extends BaseActivity {
    private String[] titles={"上衣","裤子","衬衫","鞋子","外衣","背包","箱子","皮鞋","牛仔","鞋子","外衣","背包","箱子","皮鞋","牛仔"};

    private ViewPager mViewPager;
    private TabPageIndicator mIndicator;
    private int mScreenWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        initData();
        initView();
        updateUI();
    }

    private void initData() {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth=metrics.widthPixels;
    }

    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.vp);
        mIndicator= (TabPageIndicator) findViewById(R.id.indicator);
        mIndicator.setTextSize(mScreenWidth/22);
        mIndicator.setTabPadding(mScreenWidth/22);
    }

    private void updateUI() {
        mViewPager.setAdapter(new TestAdapter(getSupportFragmentManager()));
        mIndicator.setViewPager(mViewPager);
    }

    public class TestAdapter extends FragmentPagerAdapter {

        public TestAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestFragment.newPage(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
