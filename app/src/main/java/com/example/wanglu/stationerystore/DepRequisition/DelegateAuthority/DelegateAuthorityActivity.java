package com.example.wanglu.stationerystore.DepRequisition.DelegateAuthority;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Fragments.datePickerFragment;
import com.example.wanglu.stationerystore.R;

import java.text.DateFormat;
import java.util.Calendar;

public class DelegateAuthorityActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ConstraintLayout delegateLayout=null;

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        EditText d=findViewById(R.id.enddate);
        d.setText(currentDateString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate_form);

        delegateLayout=findViewById(R.id.delegateInclude);
        EditText d=findViewById(R.id.enddate);

//        d.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                DialogFragment datePicker=new DialogFragment();
//                datePicker.show(getFragmentManager(),"date picker");
//                Toast.makeText(DelegateAuthorityActivity.this,"touch",Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
        Button b=findViewById(R.id.confirmButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment datePicker=new datePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");


            }
        });





    }
}
