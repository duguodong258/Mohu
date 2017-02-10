package com.xianrou.mohu.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.PopupWindow;

import com.xianrou.mohu.R;

/**
 * @author 咸鱼
 * @date 2017/1/14
 * @des 点击发布的弹窗
 */

public class BottomPopupWindow extends PopupWindow {

    private final int DURATION = 300;
    private final View popupView;//popup的布局
    private final Button mFirstBtn;
    private final Button mSecondBtn;
    private Context mContext;

    //构造器传入一个点击监听器
    public BottomPopupWindow(Context context, View.OnClickListener clickListener) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.popup_bottom, null);
        mFirstBtn = (Button) popupView.findViewById(R.id.btn_firstItem);
        mSecondBtn = (Button) popupView.findViewById(R.id.btn_secondItem);

        mFirstBtn.setOnClickListener(clickListener);
        mSecondBtn.setOnClickListener(clickListener);
        popupView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        this.setContentView(popupView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        //设置弹窗背景
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        //添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int height = popupView.findViewById(R.id.pop_layout).getTop();
                int rawY = (int) event.getRawY();//手指点击的y坐标
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(rawY > height){ //说明点在了外边
                        popupView.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.popupwindow_out));
                    }
                }
                return true;
            }
        });
        show();
    }

    //用来显示popupwindow
    private void show() {
        popupView.startAnimation(getAlphaAnimation(DURATION,0f,1f));
//        popupView.startAnimation(getTranslateAnimationY(DURATION,1f,0f,true));
        popupView.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.popupwindow_in));
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        show();
        super.showAtLocation(parent, gravity, x, y);
    }

    /**
     * 制定 AlphaAnimation
     * @return
     */
    public static AlphaAnimation getAlphaAnimation(long durationMillis,float fromAlpha, float toAlpha) {
        AlphaAnimation aa = new AlphaAnimation(fromAlpha, toAlpha);
        aa.setDuration(durationMillis);
        aa.setFillAfter(true);
        aa.setFillEnabled(true);
        return aa;
    }

    /**
     * 制定 TranslateAnimation
     *
     * @return
     */
    public static TranslateAnimation getTranslateAnimationY(long durationMillis,
                                                            float fromYValue, float toYValue, boolean isKeepEnd) {
        TranslateAnimation ta = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, fromYValue,
                Animation.RELATIVE_TO_SELF, toYValue);
        ta.setDuration(durationMillis);
        if (isKeepEnd) {
            ta.setFillAfter(true);
            ta.setFillEnabled(true);
        }
        return ta;
    }

    //设置第一个item名字
    public void setFirstItemName(String name){
        if(!TextUtils.isEmpty(name)){
            mFirstBtn.setText(name);
        }
    }

    //设置第二个item名字
    public void setSecondItemName(String name){
        if(!TextUtils.isEmpty(name)){
            mSecondBtn.setText(name);
        }
    }
}
