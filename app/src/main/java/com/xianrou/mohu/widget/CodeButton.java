package com.xianrou.mohu.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.xianrou.mohu.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lxl on 2015/12/28.
 * 验证码的时间统计
 */
public class CodeButton extends TextView implements OnClickListener {
	private long lenght = 60 * 1000;// 倒计时长度,这里给了默认60秒
	private String textafter = "重新发送(";
	private String textAfterString=")";
	private String textbefore = "获取验证码";
	private final String TIME = "time";
	private final String CTIME = "ctime";
	private OnClickListener mOnclickListener;
	private Timer t;
	private TimerTask tt;
	private long time;
	public static Map<String, Long> map=new HashMap<>();
	private EditText mEditText;//传进来EditText 如果有手机号正确再开始倒计时
	private Context mContext;

	public void setEditText(EditText editText) {
		mEditText = editText;
	}

	public CodeButton(Context context) {
		super(context);
		mContext = context;
		setOnClickListener(this);

	}

	public CodeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setOnClickListener(this);
	}

	@SuppressLint("HandlerLeak")
	Handler han = new Handler() {
		public void handleMessage(android.os.Message msg) {
			CodeButton.this.setText(textafter+time / 1000+textAfterString );
			time -= 1000;
			if (time < 0) {
				CodeButton.this.setEnabled(true);
				CodeButton.this.setText(textbefore);
				clearTimer();
			}else{
				CodeButton.this.setEnabled(false);
			}
		};
	};

	private void initTimer() {
		time = lenght;
		t = new Timer();
		tt = new TimerTask() {
			@Override
			public void run() {
				han.sendEmptyMessage(0x01);
			}
		};
	}

	private void clearTimer() {
		if (tt != null) {
			tt.cancel();
			tt = null;
		}
		if (t != null)
			t.cancel();
		t = null;
	}

	@Override
	public void setOnClickListener(OnClickListener l) {
		if (l instanceof CodeButton) {
			super.setOnClickListener(l);
		} else
			this.mOnclickListener = l;
	}

	@Override
	public void onClick(View v) {
		if (mOnclickListener != null)
			mOnclickListener.onClick(v);
		initTimer();
		checkPhone();
	}

	//检查手机号正确再发送
	private void checkPhone() {
		String phoneNumber = mEditText.getText().toString();
		if(isMobleNO(phoneNumber)){
			this.setText(textafter+time / 1000+textAfterString);
			this.setEnabled(false);
			t.schedule(tt, 0, 1000);
		}else{
			ToastUtil.showToast(mContext,"手机号格式不正确");
		}
	}

	//验证手机号码格式
	public boolean isMobleNO(String phoneNumber){
		/*
		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
		String telRegex = "[1][358]\\d{9}";
		if(TextUtils.isEmpty(phoneNumber)){
			ToastUtil.showToast(mContext,"请先输入手机号");
			return false;
		}else{
			return phoneNumber.matches(telRegex);
		}
	}

	/**
	 * 和activity的onDestroy()方法同步
	 */
	public void onDestroy() {
		if (map == null)
		map = new HashMap<>();
		map.put(TIME, time);
		map.put(CTIME, System.currentTimeMillis());
		clearTimer();

	}

	/**
	 * 和activity的onCreate()方法同步
	 */
	public void onCreate(Bundle bundle) {
		if (map == null)
			return;
		if (map.size() <= 0)// 这里表示没有上次未完成的计时
			return;
		long time = System.currentTimeMillis() - map.get(CTIME)
				- map.get(TIME);
		map.clear();
		if (time > 0)
			return;
		else {
			initTimer();
			this.time = Math.abs(time);
			t.schedule(tt, 0, 1000);
			this.setText(textafter+time+textAfterString);
			this.setEnabled(false);
		}
	}

	/** * 设置计时时候显示的文本 */
	public CodeButton setTextAfter(String text1) {
		this.textafter = text1;
		return this;
	}

	/** * 设置点击之前的文本 */
	public CodeButton setTextBefore(String text0) {
		this.textbefore = text0;
		this.setText(textbefore);
		return this;
	}

	/**
	 * 设置到计时长度
	 *
	 * @param lenght
	 *            时间 默认毫秒
	 * @return
	 */
	public CodeButton setLenght(long lenght) {
		this.lenght = lenght;
		return this;
	}

	public long getButtonTime(){
		return time;
	}

	public void restart(){
		time=0;
	}
}