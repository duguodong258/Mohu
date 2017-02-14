package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.activity.personalProfile.PersonalProfileActivity;
import com.xianrou.mohu.base.AppManager;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.util.ActivityUtil;
import com.xianrou.mohu.util.ToastUtil;
import com.xianrou.mohu.widget.BottomPopupWindow;

/**
 * @author 咸鱼
 * @date 2017/1/14 0014
 * @des 刚进程序的主页面(轻巧)
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout root_view;
    private BottomPopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        root_view = (LinearLayout) findViewById(R.id.activity_main);

        ImageView ivPublish = (ImageView) findViewById(R.id.iv_publish);
        ImageView ivHot = (ImageView) findViewById(R.id.iv_hot);
        ImageView ivReward = (ImageView) findViewById(R.id.iv_reward);
        ImageView ivShare = (ImageView) findViewById(R.id.iv_share);
        ImageView ivMine = (ImageView) findViewById(R.id.iv_mine);
        ImageView ivUnlocked = (ImageView) findViewById(R.id.iv_unlocked);

        ivPublish.setOnClickListener(this);
        ivHot.setOnClickListener(this);
        ivReward.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        ivMine.setOnClickListener(this);
        ivUnlocked.setOnClickListener(this);
    }


    public void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_publish ://发布 弹出选择的popupwindow
                showPop();
                break;
            case R.id.iv_hot ://热门 跳到home_activity 直接显示热门fragment
                ActivityUtil.startActivity(this,HomeActivity.class);
                break;
            case R.id.iv_reward ://打赏 跳到home_activity 直接显示我的打赏fragment
                ActivityUtil.startActivity(this,HomeActivity.class);
                break;
            case R.id.iv_share ://分享  跳到home_activity 直接显示我的分享fragment
                ActivityUtil.startActivity(this,HomeActivity.class);
                break;
            case R.id.iv_mine ://我的  跳到home_activity 直接显示我的资料fragment
                ActivityUtil.startActivity(this,HomeActivity.class);
                break;
            case R.id.iv_unlocked ://解封码
                ActivityUtil.startActivity(this,PersonalProfileActivity.class);
                break;
            case R.id.btn_firstItem://发布相册
                mPopupWindow.dismiss();
                ActivityUtil.startActivity(this,PublishPhotoActivity.class,AppConfig.PUBLISH_PHOTO);
                break;
            case R.id.btn_secondItem://发布视频
                mPopupWindow.dismiss();
                ActivityUtil.startActivity(this,PublishVideoActivity.class,AppConfig.PUBLISH_VIDEO);
                break;
        }
    }


    private void showPop() {
        mPopupWindow = new BottomPopupWindow(mContext, this);
        mPopupWindow.setFirstItemName("发布相册");
        mPopupWindow.setSecondItemName("发布视频");
        mPopupWindow.showAtLocation(root_view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }





    private long lastClickTime = 0;
    private static final int DOUBLE_CLICK_TIME = 2000;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitApp() {
        if (System.currentTimeMillis() - lastClickTime > DOUBLE_CLICK_TIME) {
            ToastUtil.showToast(mContext,R.string.exit_app);
            lastClickTime = System.currentTimeMillis();
        }else{
            AppManager.getAppManager().finishAllActivity();
        }
    }

}
