package com.xianrou.mohu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * @author 咸鱼
 * @date 2017/1/13
 * @des ${TODO}
 */

public abstract class BaseActivity extends AutoLayoutActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getAppManager().addActivity(this);
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        View titleView = inflater.inflate(R.layout.layout_title,null);
//        titleView.findViewById(R.id.ll_backLayout);
    }

}
