package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.util.ActivityUtil;
import com.xianrou.mohu.util.ToastUtil;
import com.xianrou.mohu.widget.BottomPopupWindow;

/**
 * @author 咸鱼
 * @date 2017/1/14
 * @des 发布信息填写页面
 */

public class PublishActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private BottomPopupWindow mPopupWindow;
    private SeekBar seekBar;//拖动进度条
    private RelativeLayout rlAwardAmount;//打赏金额的rl
    private RelativeLayout rlPhotoDescription;//相册描述的rl
    private Button btnSaveShare; //保存分享
    private Button btnAdjustPhoto;//调整照片
    private TextView mTv_percentage;//透明度百分比
    private TextView mTvAwardAmount;//打赏金额
    private TextView mTvAwardAmountType;//打赏金额类型 固定or随机
    private TextView mTvDescription;//描述内容
    private RelativeLayout mRoot_view;//根布局


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = (int) getIntent().getExtras().get("flag");//区分是发布相册还是视频(在创建布局之前)
        setContentView(R.layout.activity_publish);
        initView();
    }

    private void initView() {
        mRoot_view = (RelativeLayout) findViewById(R.id.rl_root);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        RelativeLayout rlAwardAmount = (RelativeLayout) findViewById(R.id.rl_award_amount);
        RelativeLayout rlPhotoDescription = (RelativeLayout) findViewById(R.id.rl_photo_description);
        mTv_percentage = (TextView) findViewById(R.id.tv_percentage);
        mTvAwardAmount = (TextView) findViewById(R.id.tv_award_amount);
        mTvAwardAmountType = (TextView) findViewById(R.id.tv_award_amount_type);
        mTvDescription = (TextView) findViewById(R.id.tv_description);
        Button btnSaveShare = (Button) findViewById(R.id.btn_SaveShare);
        Button btnAdjustPhoto = (Button) findViewById(R.id.btn_adjustPhoto);
        seekBar.setOnSeekBarChangeListener(this);
        rlAwardAmount.setOnClickListener(this);
        rlPhotoDescription.setOnClickListener(this);
        btnSaveShare.setOnClickListener(this);
        btnAdjustPhoto.setOnClickListener(this);
    }


    //进度值改变事件监听
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        mTv_percentage.setText(""+progress);
    }
    //开始拖动事件监听
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    //停止拖动事件监听
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_award_amount ://打赏金额
                showPop();
                break;
            case R.id.rl_photo_description ://相册描述
                mPopupWindow.dismiss();
                break;
            case R.id.btn_adjustPhoto ://调整照片
                ToastUtil.showToast(mContext,"啊哈哈哈");
                break;
            case R.id.btn_SaveShare ://保存分享

                break;
            case R.id.btn_firstItem://固定赏金
                mPopupWindow.dismiss();
                ActivityUtil.startActivity(this,EditActivity.class, AppConfig.FIXED_BOUNTY);
                break;
            case R.id.btn_secondItem://随机赏金
                mPopupWindow.dismiss();
                ActivityUtil.startActivity(this,EditActivity.class,AppConfig.RANDOM_BOUNTY);
                break;
        }
    }

    private void showPop() {
        mPopupWindow = new BottomPopupWindow(mContext, this);
        mPopupWindow.setFirstItemName("固定赏金");
        mPopupWindow.setSecondItemName("随机赏金");
        mPopupWindow.showAtLocation(mRoot_view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }
}
