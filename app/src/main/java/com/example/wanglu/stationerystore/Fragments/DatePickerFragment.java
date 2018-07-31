package com.example.wanglu.stationerystore.Fragments;

import android.app.Dialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
//Author: Luo Chao and Jack
public class DatePickerFragment extends DialogFragment {
    private long minDate;

    public void setMinDate(long minDate) {
        this.minDate = minDate;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(),year,month,day);

        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMinDate(minDate);
        return dialog;
    }
}
