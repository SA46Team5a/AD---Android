package com.example.wanglu.stationerystore.DepRequisition.ChangeCollectionPoint;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.wanglu.stationerystore.R;

public class UpdateLocationActivity extends AppCompatActivity {

    private ConstraintLayout collectionPoints=null;
    RadioButton p1;
    RadioButton p2;
    RadioButton p3;
    RadioButton p4;
    RadioButton p5;
    RadioButton p6;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);//set content view

        collectionPoints =  findViewById(R.id.collectionPoints);//initiate include(include ID is collectionPoints)
        //initiate buttons
        confirmButton= findViewById(R.id.confirmbutton);
        p1= findViewById(R.id.p1radioButton);
        p2= findViewById(R.id.p2radioButton);
        p3= findViewById(R.id.p3radioButton);
        p4= findViewById(R.id.p4radioButton);
        p5= findViewById(R.id.p5radioButton);
        p6= findViewById(R.id.p6radioButton);

    }
}
