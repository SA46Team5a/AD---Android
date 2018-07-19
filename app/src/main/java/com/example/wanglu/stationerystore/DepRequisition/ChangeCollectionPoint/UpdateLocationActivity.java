package com.example.wanglu.stationerystore.DepRequisition.ChangeCollectionPoint;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.wanglu.stationerystore.R;

public class UpdateLocationActivity extends AppCompatActivity {

    private ConstraintLayout collectionPoints=null;

    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);//set content view

        collectionPoints =  findViewById(R.id.collectionPoints);//initiate include(include ID is collectionPoints)
        //initiate buttons


    }

}
