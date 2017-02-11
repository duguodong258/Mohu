package com.xianrou.mohu.activity.personalProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.util.ToastUtil;

/**
 * @author 咸鱼
 * @date 2017/2/10 0010
 * @des 账号 支付宝 昵称 共用同一activity
 */

public class ProfileDetailActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout layoutTitle;
    private LinearLayout llBackLayout;
    private TextView titleCenter;
    private LinearLayout llRightLayout;
    private TextView tvRightText;
    private EditText etAccount;
    private EditText etAliPay;
    private EditText etNickName;
    private ImageView ivCleanContent;
    int flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledetail);
        flag = (int) getIntent().getExtras().get("flag");//区分是哪个点击跳转过来的
        initView();
    }


    private void initView() {
        layoutTitle = (RelativeLayout) findViewById(R.id.layoutTitle);
        llBackLayout = (LinearLayout) findViewById(R.id.ll_backLayout);
        llRightLayout = (LinearLayout) findViewById(R.id.ll_rightLayout);
        ivCleanContent = (ImageView) findViewById(R.id.iv_clean_content);
        titleCenter = (TextView) findViewById(R.id.title_center);
        tvRightText = (TextView) findViewById(R.id.tvRightText);
        etAccount = (EditText) findViewById(R.id.et_account);
        etAliPay = (EditText) findViewById(R.id.et_AliPay);
        etNickName = (EditText) findViewById(R.id.et_nickName);
        ivCleanContent.setOnClickListener(this);
        llBackLayout.setOnClickListener(this);
        llRightLayout.setOnClickListener(this);

        switch (flag) {
            case AppConfig.ACCOUNT :
                etAccount.setVisibility(View.VISIBLE);
                titleCenter.setText("账号");
                break;
            case AppConfig.ALIPAY :
                etAliPay.setVisibility(View.VISIBLE);
                titleCenter.setText("支付宝");
                break;
            case AppConfig.NICKNAME :
                etNickName.setVisibility(View.VISIBLE);
                titleCenter.setText("昵称");
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_backLayout :
                finish();
                break;
            case R.id.iv_clean_content :
                etAccount.setText("");
                etAliPay.setText("");
                etNickName.setText("");
                break;
            case R.id.ll_rightLayout://确定的时候网络请求 并且关闭此界面
                update();
                break;
        }
    }

    private void update() {

        switch (flag) {
            case AppConfig.ACCOUNT :
                String account = etAccount.getText().toString();
                if(TextUtils.isEmpty(account)){
                    ToastUtil.showToast(mContext,"请输入账号");
                }else{
                    //网络请求 成功后发送消息到PersonalProfileActivity(个人资料)更新界面信息
                    finish();
                }
                break;
            case AppConfig.ALIPAY :
                String alipay = etAliPay.getText().toString();
                if(TextUtils.isEmpty(alipay)){
                    ToastUtil.showToast(mContext,"请输入支付宝");
                }else{
                    //网络请求 成功后发送消息到PersonalProfileActivity(个人资料)更新界面信息
                    finish();
                }
                break;
            case AppConfig.NICKNAME :
                String nickName = etNickName.getText().toString();
                if(TextUtils.isEmpty(nickName)){
                    ToastUtil.showToast(mContext,"请输入昵称");
                }else{
                    //网络请求 成功后发送消息到PersonalProfileActivity(个人资料)更新界面信息
                    finish();
                }
                break;
        }
    }

}
