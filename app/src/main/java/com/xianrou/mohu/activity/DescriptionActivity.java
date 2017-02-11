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
import com.xianrou.mohu.util.EditTextUtil;

/**
 * @author 咸鱼
 * @date 2017/1/18 0018
 * @des 视频、相册描述详情页
 */

public class DescriptionActivity extends BaseActivity implements EditTextUtil.IEditTextChangeListener, View.OnClickListener {

    private LinearLayout llRoot;//根布局
    private RelativeLayout layoutTitle;
    private LinearLayout llBackLayout;//返回
    private TextView titleCenter;//标题
    private LinearLayout llRightLayout;//右边的title按钮
    private TextView tvRightText;

    private EditText etInputTitle;//请输入标题
    private EditText etInputDes;//请输入描述
    private TextView tvTitle;//图片标题or视频标题
    private TextView tvDescription;//图片描述or视频描述
    private TextView tvTitleWordNumber;//标题字数tv
    private TextView tvDesWordNumber;//描述字数tv
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        initView();

    }

    private void initView() {
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        layoutTitle = (RelativeLayout) findViewById(R.id.layoutTitle);
        llBackLayout = (LinearLayout) findViewById(R.id.ll_backLayout);
        titleCenter = (TextView) findViewById(R.id.title_center);
        llRightLayout = (LinearLayout) findViewById(R.id.ll_rightLayout);
        tvRightText = (TextView) findViewById(R.id.tvRightText);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvTitleWordNumber = (TextView) findViewById(R.id.tv_title_wordNumber);
        tvDesWordNumber = (TextView) findViewById(R.id.tv_des_wordNumber);
        etInputTitle = (EditText) findViewById(R.id.et_input_title);
        etInputDes = (EditText) findViewById(R.id.et_input_des);
        llBackLayout.setOnClickListener(this);
        EditTextUtil editTextUtil = new EditTextUtil();
        editTextUtil.setListener(this);
        editTextUtil.change(etInputTitle);
        editTextUtil.change(etInputDes);

        int flag = (int) getIntent().getExtras().get("flag");
        switch (flag) {
            case AppConfig.PUBLISH_PHOTO ://相册描述
                tvTitle.setText("图片标题");
                tvDescription.setText("图片描述");
                break;
            case AppConfig.PUBLISH_VIDEO ://视频描述
                tvTitle.setText("视频标题");
                tvDescription.setText("视频描述");
                break;
        }
    }

    @Override
    public void getContentLength(int length, EditText editText) {
        if(editText == etInputTitle){//标题的et监听
                tvTitleWordNumber.setText(length+"/20");
        }else if(editText == etInputDes){//描述的et监听
                tvDesWordNumber.setText(length+"/50");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_backLayout :
                finish();
                break;
            case R.id.tvRightText :

                break;
        }
    }
}
