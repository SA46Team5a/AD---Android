 package com.example.wanglu.stationerystore.DepRequisition.ChangeCollectionPoint;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.DepRequisition.AppointDeptRep.AppointRepActivity;
import com.example.wanglu.stationerystore.Navigation.NavigationForHead;
import com.example.wanglu.stationerystore.Model.ChangeCollectionPointModel;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;

//Author:Luo Chao
public class UpdateLocationActivity extends AppCompatActivity {
    ArrayList<String> collectionPntIDList=new ArrayList<>();
    ArrayList<String> collectionDetailsList=new ArrayList<>();
    SharedPreferences pref;
    private ConstraintLayout collectionPoints=null;
    //declare variables and buttons
    Button confirmButton;
    RadioGroup radioGroup;
    String selectedLocationID;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);//set content view

        pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        collectionPoints =  findViewById(R.id.collectionPoints);//initiate include(include ID is collectionPoints)

        radioGroup=findViewById(R.id.radioGroupOfCollectionPoints);
        //start AsyncTask to load locationDetail and ID

        new AsyncTask<Void, Void, HashMap<String,ArrayList<String>>>() {
            @Override
            protected HashMap<String,ArrayList<String>> doInBackground(Void... params) {
                HashMap<String,ArrayList<String>> collectPnt=new HashMap<>();
                collectPnt=ChangeCollectionPointModel.getCollectPntList();
                return collectPnt;
            }
            @Override
            protected void onPostExecute(HashMap<String,ArrayList<String>> collectionPntList) {
                int[] ids={R.id.collectPoint1RadioButton,R.id.collectPoint2RadioButton,R.id.collectPoint3RadioButton,R.id.collectPoint4RadioButton,R.id.collectPoint5RadioButton,R.id.collectPoint6RadioButton};
                for(int i=0;i<collectionPntList.get("detail").size();i++)
                {
                    //load text to radioButton
                    String text=collectionPntList.get("detail").get(i);
                    collectionDetailsList.add(text);
                    collectionPntIDList.add(collectionPntList.get("ID").get(i));
                    RadioButton radioButton=(RadioButton)findViewById(ids[i]);
                    radioButton.setText(text);
                }
            }
        }.execute();
        //asyncTask to load passcode
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String pass=ChangeCollectionPointModel.getPasscode(pref.getString("deptID","no name"));
                return pass;
            }
            @Override
            protected void onPostExecute(String result) {
                TextView t=findViewById(R.id.passcodeView);
                t.setText(result);
            }
        }.execute();

        //load default collectionPoint to radioGroup
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String defaultLocation=ChangeCollectionPointModel.getCollectionPointOfDept(pref.getString("deptID","no name"));
                return defaultLocation;
            }
            @Override
            protected void onPostExecute(String result) {
                radioGroup.check(findCollectionPointRadiobuttonIDByName(result));
            }
        }.execute();

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
                selectedLocationID=collectionPntIDList.get(collectionDetailsList.indexOf(selectedLocation));
                makeAlertDialog();

            }
        });
    }
    void makeAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Change collection point")
                .setMessage("Changed collection point successful!")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... params) {
                                ChangeCollectionPointModel.updateCollectionPoint(pref.getString("deptID","no name"),selectedLocationID);
                                return null;

                            }
                            @Override
                            protected void onPostExecute(Void result) {

                            }
                        }.execute();
                        startActivity(new Intent(getApplicationContext(), NavigationForHead.class));
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UpdateLocationActivity.this, getString(android.R.string.no), Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    //method used in this activity
    private int findCollectionPointRadiobuttonIDByName(String collectionPointName)
    {
        int buttonID=0;
        int[] ids={R.id.collectPoint1RadioButton,R.id.collectPoint2RadioButton,R.id.collectPoint3RadioButton,R.id.collectPoint4RadioButton,R.id.collectPoint5RadioButton,R.id.collectPoint6RadioButton};
        for(int i=0;i<ids.length;i++)
        {

            RadioButton r=(RadioButton)findViewById(ids[i]);
            if(r.getText().toString().equals(collectionPointName))
            {
                buttonID=ids[i];
            }
        }
        return buttonID;
    }

}
