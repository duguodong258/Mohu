package com.xianrou.mohu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseFragment;
import com.xianrou.mohu.util.DeviceUtil;
import com.xianrou.mohu.widget.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import static com.xianrou.mohu.R.id.indicator;
import static com.xianrou.mohu.R.id.vp;

/**
 * @author 咸鱼
 * @date 2017/1/19
 * @des ${TODO}
 */

public class HomeFragment extends BaseFragment {
    private String[] titles={"推荐","热门"};
    private View mView;
    private TabPageIndicator mIndicator;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecommendFragment recommendFragment = new RecommendFragment();
        HotFragment hotFragment = new HotFragment();
        mFragments = new ArrayList<>();
        mFragments.add(recommendFragment);
        mFragments.add(hotFragment);
        mIndicator = (TabPageIndicator) mView.findViewById(indicator);
        int screenWidth = DeviceUtil.getScreenWidth(getActivity());
        mIndicator.setTextColor(getResources().getColor(R.color.indicator_tvColor));
        mIndicator.setSelectTextColor(getResources().getColor(R.color.indicator_tvColor_selected));
        mIndicator.setTextSize(screenWidth/22);
        mIndicator.setTabPadding(screenWidth/22);
        mViewPager = (ViewPager) mView.findViewById(vp);
        mViewPager.setAdapter(new MyAdapter(getChildFragmentManager(),mFragments));
        mIndicator.setViewPager(mViewPager);
    }


    public class MyAdapter extends FragmentPagerAdapter{
        List<Fragment> fragments;

        public MyAdapter(FragmentManager fm,List<Fragment> fragmentList) {
            super(fm);
            fragments = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
