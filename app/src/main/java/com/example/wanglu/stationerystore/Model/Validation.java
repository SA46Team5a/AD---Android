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
