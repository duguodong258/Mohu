package com.xianrou.mohu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.widget.PublishPopupWindow;

/**
 * @author 咸鱼
 * @date 2017/1/14 0014
 * @des 刚进程序的主页面(轻巧)
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout root_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        root_view = (LinearLayout) findViewById(R.id.activity_main);
        TextView tvPublish = (TextView) findViewById(R.id.tv_publish);
        TextView tvHome = (TextView) findViewById(R.id.tv_home);
        TextView tvMyReward = (TextView) findViewById(R.id.tv_my_reward);
        TextView tvMyShare = (TextView) findViewById(R.id.tv_my_share);
        TextView tvMine = (TextView) findViewById(R.id.tv_mine);
        TextView tvCode = (TextView) findViewById(R.id.tv_code);

        tvPublish.setOnClickListener(this);
        tvHome.setOnClickListener(this);
        tvMyReward.setOnClickListener(this);
        tvMyShare.setOnClickListener(this);
        tvMine.setOnClickListener(this);
        tvCode.setOnClickListener(this);
    }


    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_publish ://发布 弹出选择的popupwindow
                showPop();
                break;
            case R.id.tv_home ://首页 跳到home_activity 直接显示首页fragment
                jump2Activity(HomeActivity.class);
                break;
            case R.id.tv_my_reward ://我的打赏 跳到home_activity 直接显示我的打赏fragment
                jump2Activity(HomeActivity.class);
                break;
            case R.id.tv_my_share ://我的分享  跳到home_activity 直接显示我的分享fragment
                jump2Activity(LoginActivity.class);
                break;
            case R.id.tv_mine ://我的  跳到home_activity 直接显示我的资料fragment
                jump2Activity(LoginActivity.class);
                break;
            case R.id.tv_code ://标识码
                jump2Activity(CodeActivity.class);
                break;
            case R.id.btn_publishPhoto ://发布相册
                jump2Activity(PublishActivity.class);
                break;
            case R.id.btn_publishVideo ://发布视频
                jump2Activity(PublishActivity.class);
                break;
        }
    }


    private void showPop() {
        PublishPopupWindow popupWindow = new PublishPopupWindow(mContext, this);
        popupWindow.showAtLocation(root_view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }


    private void jump2Activity(Class clz) {
        Intent intent = new Intent(this,clz);
        startActivity(intent);
    }
}
