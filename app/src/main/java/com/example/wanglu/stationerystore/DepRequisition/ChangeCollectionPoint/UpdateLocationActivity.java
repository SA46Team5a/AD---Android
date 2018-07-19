package com.example.wanglu.stationerystore.DepRequisition.ChangeCollectionPoint;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;

public class UpdateLocationActivity extends AppCompatActivity {
    ArrayList<String> nameList=new ArrayList<String>(){{add("pointA");add("pointB");add("pointC");add("pointD");add("pointD");}};
    ArrayList<String> numberList=new ArrayList<String>(){{add("1");add("2");add("3");add("4");add("5");}};

    private ConstraintLayout collectionPoints=null;

    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);//set content view

        collectionPoints =  findViewById(R.id.collectionPoints);//initiate include(include ID is collectionPoints)
        //initiate buttons

        confirmButton= findViewById(R.id.confirmbutton);
        //load text to radioButton
        int[] ids={R.id.collectPoint1RadioButton,R.id.collectPoint2RadioButton,R.id.collectPoint3RadioButton,R.id.collectPoint4RadioButton,R.id.collectPoint5RadioButton};
        for(int i=0;i<nameList.size();i++)
        {
            String text=nameList.get(i);
            RadioButton r=(RadioButton)findViewById(ids[i]);
            r.setText(text);
        }




    }

}
