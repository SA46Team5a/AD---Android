 package com.example.wanglu.stationerystore.DepRequisition.ChangeCollectionPoint;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Model.ChangeCollectionPointModel;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;

//Author:Luo Chao
public class UpdateLocationActivity extends AppCompatActivity {


    private ConstraintLayout collectionPoints=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);//set content view


        collectionPoints =  findViewById(R.id.collectionPoints);//initiate include(include ID is collectionPoints)

        //declare variables and buttons
        Button confirmButton;
        //start AsyncTask
        new AsyncTask<Void, Void, ArrayList<String>>() {
            @Override
            protected ArrayList<String> doInBackground(Void... params) {
                      ArrayList<String> collectionPntList=new ArrayList<>();
                      collectionPntList=ChangeCollectionPointModel.getCollectPntList();
                return collectionPntList;
            }
            @Override
            protected void onPostExecute(ArrayList<String> collectionPntList) {
                int[] ids={R.id.collectPoint1RadioButton,R.id.collectPoint2RadioButton,R.id.collectPoint3RadioButton,R.id.collectPoint4RadioButton,R.id.collectPoint5RadioButton};
                for(int i=0;i<collectionPntList.size();i++)
                {
                    //load text to radioButton
                    String text=collectionPntList.get(i);
                    RadioButton r=(RadioButton)findViewById(ids[i]);
                    r.setText(text);

                }
            }
        }.execute();

        //need default selection array

        ArrayList<String> passcodeList=new ArrayList<String>(){{add("1234");}};

        //load passcode
        TextView pwdView=findViewById(R.id.passcodeView);
        pwdView.setText(passcodeList.get(0));

        //confirm button click event
        confirmButton=findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldleak")
            @Override
            public void onClick(View view) {
                RadioGroup collectionPntGroup=findViewById(R.id.radioGroupOfCollectionPoints);
                int selectedID=collectionPntGroup.getCheckedRadioButtonId();
                RadioButton r=(RadioButton)findViewById(selectedID);
                String selectedLocation=r.getText().toString();
                Toast.makeText(UpdateLocationActivity.this,selectedLocation,Toast.LENGTH_SHORT).show();
            }
        });

    }

}
