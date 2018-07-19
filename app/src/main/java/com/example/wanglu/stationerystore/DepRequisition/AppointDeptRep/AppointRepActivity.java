package com.example.wanglu.stationerystore.DepRequisition.AppointDeptRep;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;

public class AppointRepActivity extends AppCompatActivity {
    private ConstraintLayout employeedropdownlist = null;
    Spinner spinner;
   // SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_dropdown_item_1line);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate_form2);
        employeedropdownlist = findViewById(R.id.employeeItems);


        spinner = findViewById(R.id.representativeSpinner);
       // spinner.setAdapter( );
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String [] rep = getResources().getStringArray(R.array.department);
               // Toast.makeText(this,"You select "+ )
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(),"You must select one representative",Toast.LENGTH_LONG).show();
            }
        });

        ArrayList<String> list=new ArrayList<String>()
        {
            {
                add("pointA");add("pointB");add("pointC");add("pointD");
            }
        };


    }



}
