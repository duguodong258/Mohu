package com.xianrou.mohu.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/**
 * @author 咸鱼
 * @date 2017/1/18 0018
 * @des ${TODO}
 */

public class EditTextUtil implements TextWatcher {

    private IEditTextChangeListener mListener;

    public void setListener(IEditTextChangeListener listener) {
        mListener = listener;
    }

    public EditTextUtil(EditText...editTexts) {
        for (EditText editText : editTexts) {
            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.i("TAG", "charSequence:"+charSequence);
        mListener.getContentLength(charSequence.length());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    public interface IEditTextChangeListener{
        void getContentLength(int length);
    }
}
