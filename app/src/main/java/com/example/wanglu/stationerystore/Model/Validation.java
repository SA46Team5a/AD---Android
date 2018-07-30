package com.example.wanglu.stationerystore.Model;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;


public class Validation {

//public static Integer max_input;

    public static InputFilter getLimitFilter(Integer MAX_INPUT) {
       // max_input = MAX_INPUT;
        final Integer input=MAX_INPUT;
        final InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Log.d("------>", "source is " + source.toString() +  // 本次输入字符
                        "\nstart is " + start +    // start和end一般为0和1，即sorce长度
                        "\nend is " + end +
                        "\ndest is " + dest +      // 上一次输入内容
                        "\ndstart is " + dstart +  // 光标开始位置
                        "\ndend is " + dend);    // 光标结束位置

                Integer inputNum = Integer.parseInt(dest.toString()+source);
                if (inputNum > input){
                    return "";
                }
                return null;
            }
        };
        return filter;
    }
}
