package com.xianrou.mohu.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author 咸鱼
 * @date 2017/1/18 0018
 * @des ${TODO}
 */

public class EditTextUtil {

    private IEditTextChangeListener mListener;

    public void setListener(IEditTextChangeListener listener) {
        mListener = listener;
    }

    //获取输入内容的长度 回调给界面显示
    public void change(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mListener.getContentLength(charSequence.length(),editText);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public interface IEditTextChangeListener{
        void getContentLength(int length,EditText editText);
    }
}
