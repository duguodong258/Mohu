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
import com.xianrou.mohu.widget.CodeButton;

/**
 * @author 咸鱼
 * @date 2017/2/11 0011
 * @des 绑定手机号页面
 */

public class PhoneActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout layoutTitle;
    private LinearLayout llBackLayout;
    private TextView titleCenter;
    private LinearLayout llRightLayout;
    private TextView tvRightText;
    private EditText etPhoneNumber;
    private EditText etPhoneCode;
    private CodeButton btnGetCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initView();
    }

    private void initView() {
        layoutTitle = (RelativeLayout) findViewById(R.id.layoutTitle);
        llBackLayout = (LinearLayout) findViewById(R.id.ll_backLayout);
        titleCenter = (TextView) findViewById(R.id.title_center);
        llRightLayout = (LinearLayout) findViewById(R.id.ll_rightLayout);
        tvRightText = (TextView) findViewById(R.id.tvRightText);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        etPhoneCode = (EditText) findViewById(R.id.et_phone_code);
        btnGetCode = (CodeButton) findViewById(R.id.btn_getCode);
        llBackLayout.setOnClickListener(this);
        llRightLayout.setOnClickListener(this);
        btnGetCode.setOnClickListener(this);
        btnGetCode.setEditText(etPhoneNumber);
        titleCenter.setText("绑定手机号");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_backLayout ://返回
                finish();
                break;
            case R.id.btn_getCode ://获取验证码
                checkPhoneNumber();
                break;
            case R.id.ll_rightLayout://确定 绑定手机号的时候网络请求 并且关闭此界面
                bindPhoneNumber();
                break;
        }
    }

    private void checkPhoneNumber() {
        String phoneNumber = etPhoneNumber.getText().toString();
        if(btnGetCode.isMobleNO(phoneNumber)){
            /**如果手机格式正确 网络请求获取验证码**/
        }
    }


    private void bindPhoneNumber() {
        String phoneNum = etPhoneNumber.getText().toString();
        String code = etPhoneCode.getText().toString();
        if (!TextUtils.isEmpty(code) && !TextUtils.isEmpty(phoneNum)) {
            login(code, phoneNum);
        } else {
            ToastUtil.showToast(mContext,R.string.please_get_sms_code);
        }
    }


    /**网络请求上传手机号和验证码**/
    private void login(String code, String phoneNum) {

    }
}
