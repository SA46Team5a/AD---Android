 package com.example.wanglu.stationerystore.DepRequisition.ChangeCollectionPoint;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

    private ConstraintLayout collectionPoints=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);//set content view
//        final ArrayList<String> IDlist=new ArrayList<String>()
//        {
//            {
//                add("1");add("2");add("3");add("4");add("5");add("6");
//            }
//        };

        collectionPoints =  findViewById(R.id.collectionPoints);//initiate include(include ID is collectionPoints)

        collectionPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //declare variables and buttons
        Button confirmButton;
        //start AsyncTask

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
                String ID=collectionPntIDList.get(collectionDetailsList.indexOf(selectedLocation));

                Toast.makeText(UpdateLocationActivity.this,ID,Toast.LENGTH_SHORT).show();

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
                        //do post?
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

}
