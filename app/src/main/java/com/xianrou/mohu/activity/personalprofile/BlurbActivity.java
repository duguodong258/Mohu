package com.xianrou.mohu.activity.personalProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.util.ToastUtil;

/**
 * @author 咸鱼
 * @date 2017/2/11 0011
 * @des 简介页面
 */

public class BlurbActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout layoutTitle;
    private LinearLayout llBackLayout;
    private TextView titleCenter;
    private LinearLayout llRightLayout;
    private TextView tvRightText;
    private EditText etInputBlurb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurb);
        initView();
    }

    private void initView() {
        layoutTitle = (RelativeLayout) findViewById(R.id.layoutTitle);
        llBackLayout = (LinearLayout) findViewById(R.id.ll_backLayout);
        titleCenter = (TextView) findViewById(R.id.title_center);
        llRightLayout = (LinearLayout) findViewById(R.id.ll_rightLayout);
        tvRightText = (TextView) findViewById(R.id.tvRightText);
        etInputBlurb = (EditText) findViewById(R.id.et_input_blurb);
        titleCenter.setText(R.string.blurb);
        llBackLayout.setOnClickListener(this);
        llRightLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_backLayout :
                finish();
                break;
            case R.id.ll_rightLayout ://确定 网络请求
                String account = etInputBlurb.getText().toString();
                if(TextUtils.isEmpty(account)){
                    ToastUtil.showToast(mContext,"请输入内容");
                }else{
                    //网络请求 成功后再finish
                    finish();
                }
                break;
        }
    }
}
