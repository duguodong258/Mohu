package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;

/**
 * @author 咸鱼
 * @date 2017/1/14
 * @des 打赏详情页
 */

public class RewardActivity extends BaseActivity {

    private EditText mEtMinAmount;
    private EditText mEtMaxAmount;
    private TextView mTvAwardAmountType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initView();

    }

    private void initView() {
        RelativeLayout layoutTitle = (RelativeLayout) findViewById(R.id.layoutTitle);
        LinearLayout llBackLayout = (LinearLayout) findViewById(R.id.ll_backLayout);
        LinearLayout llRightLayout = (LinearLayout) findViewById(R.id.ll_rightLayout);
        TextView titleCenter = (TextView) findViewById(R.id.title_center);
        TextView tvRightText = (TextView) findViewById(R.id.tvRightText);
        TextView tvAwardAmount = (TextView) findViewById(R.id.tv_award_amount);
        mTvAwardAmountType = (TextView) findViewById(R.id.tv_award_amount_type);
        mEtMinAmount = (EditText) findViewById(R.id.et_minAmount);
        mEtMaxAmount = (EditText) findViewById(R.id.et_maxAmount);
        int flag = (int) getIntent().getExtras().get("flag");
        switch (flag) {
            case AppConfig.FIXED_BOUNTY ://固定赏金
                mTvAwardAmountType.setText("固定");
                mEtMinAmount.setHint("请输入打赏金额");
                break;
            case AppConfig.RANDOM_BOUNTY ://随机赏金
                mTvAwardAmountType.setText("随机");
                mEtMaxAmount.setVisibility(View.VISIBLE);
                break;
        }
    }
}
