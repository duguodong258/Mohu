package com.xianrou.mohu.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xianrou.mohu.R;

/**
 * @author 咸鱼
 * @date 2017/1/17 0017
 * @des ${TODO}
 */

public class TabPageIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private int mTabPaddingLeft=16;
    private int mTabPaddingRight=16;

    private int mTextColor = Color.BLACK;

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public void setSelectTextColor(int selectTextColor) {
        mSelectTextColor = selectTextColor;
    }

    private int mSelectTextColor = Color.BLUE;
    private int mTextSize = 16;

    private int mCurrentPosition;
    private int mSelectPosition;

    private int mTabCount;
    private float mOffset;

    private Paint mSelectLinePaint;
    private int mLineColor= getResources().getColor(R.color.indicator_tvColor_selected);
    private int mLineHeight=5;

    private LinearLayout mLinearLayout;

    private ViewPager mPager;

    private ViewPager.OnPageChangeListener mListener;

    public TabPageIndicator(Context context) {
        this(context,null);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TabPageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFillViewport(true);//子控件不满全屏时，设置ScrollView 全屏
        setWillNotDraw(false);//取消重绘的Tag
        initView(context);
        mSelectLinePaint = new Paint();
    }


    private void initView(Context context) {
        mLinearLayout = new LinearLayout(context);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
        mLinearLayout.setGravity(Gravity.CENTER_VERTICAL);
        addView(mLinearLayout);
    }

    /**
     * Indicator 添加ViewPager 关联
     * @param pager
     */
    public void setViewPager(ViewPager pager){
        mPager = pager;
        if(mPager!= null){
            mPager.addOnPageChangeListener(this);
        }
        notifyDataSetChange();
    }

    private void notifyDataSetChange() {
        mLinearLayout.removeAllViews();
        PagerAdapter adapter = mPager.getAdapter();
        mTabCount = adapter.getCount();
        for(int i = 0; i < mTabCount; i++) {
            CharSequence pageTitle = adapter.getPageTitle(i);
            addTab(pageTitle,i);
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else{
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                mCurrentPosition = mPager.getCurrentItem();
                mOffset = 0;
                scrollToChild();
            }
        });
    }


    private void scrollToChild() {
        int measuredWidth = this.getMeasuredWidth();
        View tabview = mLinearLayout.getChildAt(mCurrentPosition);
        int width = tabview.getWidth();
        float offset = width * mOffset;
        int left = tabview.getLeft();
        int scrollWidth = (int) (left - measuredWidth / 2 + offset);
        smoothScrollTo(scrollWidth,0);
    }


    private void addTab(CharSequence pageTitle, final int position) {
        TextView tabView = new TextView(getContext());
        if(position == mSelectPosition){
            tabView.setTextColor(mSelectTextColor);
        }else{
            tabView.setTextColor(mTextColor);
        }
        tabView.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTextSize);
        tabView.setText(pageTitle);
        tabView.setPadding(mTabPaddingLeft,0,mTabPaddingRight,0);

        mLinearLayout.addView(tabView,position);
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(position);
                updateTextSelectState(position);

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mTabCount == 0){
            return;
        }
        mSelectLinePaint.setAntiAlias(true);
        mSelectLinePaint.setColor(mLineColor);
        View currentTab = mLinearLayout.getChildAt(mCurrentPosition);
        float lineLeft=currentTab.getLeft();
        float lineRight=currentTab.getRight();
        if (mOffset>0f && mCurrentPosition<mTabCount-1){
            View nextTab = mLinearLayout.getChildAt(mCurrentPosition + 1);
            //            lineLeft=nextTab.getLeft()*mOffset-(1f-mOffset)*currentTab.getLeft();
            //            lintRight=nextTab.getRight()*mOffset-(1f-mOffset)*currentTab.getRight();

            final float nextTabLeft = nextTab.getLeft();
            final float nextTabRight = nextTab.getRight();

            lineLeft = (mOffset * nextTabLeft + (1f - mOffset) * lineLeft);
            lineRight = (mOffset * nextTabRight + (1f - mOffset) * lineRight);
        }
        canvas.drawRect(lineLeft,getMeasuredHeight()-mLineHeight,lineRight,getMeasuredHeight(),mSelectLinePaint);
    }

    private void updateTextSelectState(int position) {
        if (mTabCount==0){
            return;
        }
        TextView lastSelectView = (TextView) mLinearLayout.getChildAt(mSelectPosition);
        lastSelectView.setTextColor(mTextColor);
        TextView currentSelectView = (TextView) mLinearLayout.getChildAt(position);
        currentSelectView.setTextColor(mSelectTextColor);
        mSelectPosition=position;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(mListener!= null){
            mListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
        }
        mCurrentPosition = position;
        mOffset = positionOffset;
        scrollToChild();
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        if (mListener!=null){
            mListener.onPageSelected(position);
        }

        updateTextSelectState(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mListener!=null){
            mListener.onPageScrollStateChanged(state);
        }
    }

    public void setTextSize(int textSize){
        this.mTextSize=textSize;
    }

    public void setTabPadding(int tabPadding){
        this.mTabPaddingLeft=tabPadding;
        this.mTabPaddingRight=tabPadding;
    }
}
