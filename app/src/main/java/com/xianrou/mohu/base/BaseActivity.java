package com.xianrou.mohu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.xianrou.mohu.R;

/**
 * @author 咸鱼
 * @date 2017/1/13
 * @des ${TODO}
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        View titleView = inflater.inflate(R.layout.layout_title,null);
//        titleView.findViewById(R.id.ll_backLayout);
    }


    public void onClick(View view){
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Log.i("TAG", "onKeyDown   执行啦");
            overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
        }
        return super.onKeyDown(keyCode, event);
    }
}
