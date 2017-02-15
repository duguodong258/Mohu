package com.xianrou.mohu.fragment.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseFragment;

/**
 * @author 咸鱼
 * @date 2017/1/19
 * @des 手机号注册
 */

public class PhoneRegisterFragment extends BaseFragment {

    private LinearLayout mLl_Root;
    private RelativeLayout mRl_Top;
    private RelativeLayout mRl_Close;
    private TextView mTv_TabRight;
    private SimpleDraweeView mUserAvatar;
    private EditText mEt_InputAccount;
    private Button mBtn_GetCode;

    private static PhoneRegisterFragment instance;
    private View mView;

    public static synchronized PhoneRegisterFragment getInstance() {
        if(instance==null)
            instance = new PhoneRegisterFragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_phone_register, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mLl_Root = (LinearLayout) mView.findViewById(R.id.ll_root);
        mRl_Top = (RelativeLayout) mView.findViewById(R.id.rl_top);
        mRl_Close = (RelativeLayout) mView.findViewById(R.id.rl_close);
        mTv_TabRight = (TextView) mView.findViewById(R.id.tv_tab_right);
        mUserAvatar = (SimpleDraweeView) mView.findViewById(R.id.user_avatar);
        mEt_InputAccount = (EditText) mView.findViewById(R.id.et_input_account);
        mBtn_GetCode = (Button) mView.findViewById(R.id.btn_getCode);
        Uri uri = Uri.parse("http://image102.360doc.com/DownloadImg/2016/12/2900/88036894_1.jpg");
        mUserAvatar.setImageURI(uri);
    }
}
