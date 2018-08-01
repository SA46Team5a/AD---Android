package com.example.wanglu.stationerystore.DepRequisition.AppointDeptRep;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Model.AppointRepModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForHead;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;

//Author: Wang Lu
public class AppointRepActivity extends AppCompatActivity {
    ArrayList<String> empIDList=new ArrayList<>();
    ArrayList<String> empNameList=new ArrayList<>();
    SharedPreferences pref;
    private ConstraintLayout employeedropdownlist = null;
    Spinner spinner;
    Button btn;
    String selectedRepName;
    String selectedIDFromSpinner;
    String deptRepID;
    String currentRepName;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate_form2);

        pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        employeedropdownlist = findViewById(R.id.employeeItems);

        btn = findViewById(R.id.confirmButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makeAlertDialog();
            }

        });

//Start AsyncTask
        new AsyncTask<Void, Void, HashMap<String,ArrayList<String>>>() {
            @Override
            protected HashMap<String,ArrayList<String>> doInBackground(Void... params) {
                HashMap<String,ArrayList<String>> empMap = new HashMap<>();
                empMap= AppointRepModel.getEmployee(pref.getString("deptID","no name"));
                return empMap;
            }
            @Override
            protected void onPostExecute(HashMap<String,ArrayList<String>> result) {
                empIDList = result.get("EmployeeID");
                empNameList = result.get("EmployeeName");

                spinner = findViewById(R.id.representativeSpinner);
                final ArrayAdapter<String> adapter=new ArrayAdapter<String>(AppointRepActivity.this,android.R.layout.simple_spinner_item, empNameList);
                adapter.setDropDownViewResource(R.layout.spinner_item);
                spinner.setAdapter(adapter );
                spinner.setSelection(empNameList.indexOf(currentRepName));

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        selectedRepName =adapterView.getItemAtPosition(i).toString();

                        selectedIDFromSpinner = empIDList.get(empNameList.indexOf(selectedRepName));
                        Toast.makeText(getApplicationContext(),selectedIDFromSpinner,Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Toast.makeText(getApplicationContext(),"You must select one representative",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }.execute();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                HashMap<String,String> deprepIdMap = new HashMap<>();

                deprepIdMap= AppointRepModel.getDeprepID(pref.getString("deptID","no name"));
                deptRepID=  deprepIdMap.get("DeptRepID");
                currentRepName=deprepIdMap.get("EmployeeName");
                return null;

            }
            @Override
            protected void onPostExecute(Void result) {

            }
        }.execute();



    }
    void makeAlertDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Appoint representative")
                .setMessage("Appoint representative will be submitted. Would you like to continue?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do post?
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... params) {
                                AppointRepModel.submitAppointRep(deptRepID, selectedIDFromSpinner);
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
                        Toast.makeText(AppointRepActivity.this, getString(android.R.string.no), Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }
}
