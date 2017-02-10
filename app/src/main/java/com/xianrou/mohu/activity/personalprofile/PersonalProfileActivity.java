package com.xianrou.mohu.activity.personalprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.util.ActivityUtil;
import com.xianrou.mohu.widget.province.SelectCity;

/**
 * @author 咸鱼
 * @date 2017/2/8 0008
 * @des 个人资料
 */

public class PersonalProfileActivity extends BaseActivity implements View.OnClickListener, SelectCity.CitySelectListener {
    private LinearLayout llRoot;
    private LinearLayout llBackLayout;
    private LinearLayout llRightLayout;
    private ImageView ivArrowAvatar;
    private ImageView ivAvatar;
    private ImageView ivArrowNickName;
    private ImageView ivArrowGender;
    private ImageView ivArrowArea;
    private ImageView ivArrowBlurb;
    private ImageView ivArrowAccount;
    private ImageView ivArrowPhoneNumber;
    private ImageView ivArrowAliPay;

    private TextView titleCenter;
    private TextView tvRightText;
    private TextView tvNickName;
    private TextView tvGender;
    private TextView tvArea;
    private TextView tvBlurb;
    private TextView tvAccount;
    private TextView tvPhoneNumber;
    private TextView tvAliPay;

    private RelativeLayout rlAvatar;
    private RelativeLayout rlNickName;
    private RelativeLayout rlArea;
    private RelativeLayout rlGender;
    private RelativeLayout rlBlurb;
    private RelativeLayout rlAccount;
    private RelativeLayout rlPhoneNumber;
    private RelativeLayout rlAliPay;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalprofile);
        initView();
    }

    private void initView() {
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        llBackLayout = (LinearLayout) findViewById(R.id.ll_backLayout);
        llRightLayout = (LinearLayout) findViewById(R.id.ll_rightLayout);
        titleCenter = (TextView) findViewById(R.id.title_center);
        tvRightText = (TextView) findViewById(R.id.tvRightText);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvNickName = (TextView) findViewById(R.id.tv_nickName);
        tvArea = (TextView) findViewById(R.id.tv_area);
        tvBlurb = (TextView) findViewById(R.id.tv_blurb);
        tvAccount = (TextView) findViewById(R.id.tv_account);
        tvPhoneNumber = (TextView) findViewById(R.id.tv_phoneNumber);
        tvAliPay = (TextView) findViewById(R.id.tv_AliPay);
        ivArrowAvatar = (ImageView) findViewById(R.id.iv_arrow_avatar);
        ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        ivArrowNickName = (ImageView) findViewById(R.id.iv_arrow_nickName);
        ivArrowGender = (ImageView) findViewById(R.id.iv_arrow_gender);
        ivArrowArea = (ImageView) findViewById(R.id.iv_arrow_area);
        ivArrowBlurb = (ImageView) findViewById(R.id.iv_arrow_blurb);
        ivArrowAccount = (ImageView) findViewById(R.id.iv_arrow_account);
        ivArrowPhoneNumber = (ImageView) findViewById(R.id.iv_arrow_phoneNumber);
        ivArrowAliPay = (ImageView) findViewById(R.id.iv_arrow_AliPay);

        findViewById(R.id.rl_avatar).setOnClickListener(this);
        findViewById(R.id.rl_gender).setOnClickListener(this);
        findViewById(R.id.rl_area).setOnClickListener(this);
        findViewById(R.id.rl_blurb).setOnClickListener(this);
        findViewById(R.id.rl_account).setOnClickListener(this);
        findViewById(R.id.rl_phoneNumber).setOnClickListener(this);
        findViewById(R.id.rl_AliPay).setOnClickListener(this);
        findViewById(R.id.rl_nickName).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_avatar ://头像

                break;
            case R.id.rl_nickName ://昵称
                ActivityUtil.startActivity(this,ProfileDetailActivity.class, AppConfig.NICKNAME);
                break;
            case R.id.rl_gender ://性别

                break;
            case R.id.rl_area ://地区
                SelectCity selectCity = new SelectCity(mContext,llRoot);
                selectCity = selectCity.build();
                selectCity.setListener(this);
                selectCity.show();
                break;
            case R.id.rl_blurb ://简介

                break;
            case R.id.rl_account ://账号
                ActivityUtil.startActivity(this,ProfileDetailActivity.class, AppConfig.ACCOUNT);
                break;
            case R.id.rl_phoneNumber ://手机号

                break;
            case R.id.rl_AliPay ://支付宝
                ActivityUtil.startActivity(this,ProfileDetailActivity.class, AppConfig.ALIPAY);
                break;
        }
    }


    //地址选取成功回调的方法
    @Override
    public void selectOk(StringBuilder builder) {
        String area = builder.toString();
        tvArea.setText(area);
    }
}
