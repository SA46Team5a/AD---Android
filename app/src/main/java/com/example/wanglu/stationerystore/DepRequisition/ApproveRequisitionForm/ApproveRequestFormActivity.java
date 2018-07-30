package com.example.wanglu.stationerystore.DepRequisition.ApproveRequisitionForm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wanglu.stationerystore.Adapter.ApproveRequestAdapter;
import com.example.wanglu.stationerystore.Model.ApproveRequestModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForHead;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;

//Author: Wang Lu
public class ApproveRequestFormActivity extends AppCompatActivity {
    public SharedPreferences pref;
    ConstraintLayout approveform = null;
    public HashMap<String,ArrayList<String>> approvaMap =new HashMap<>();

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_form);

        pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Button homebtn = (Button) findViewById(R.id.homebtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ApproveRequestFormActivity.this, NavigationForHead.class);
                startActivity(i);
            }
        });

        new AsyncTask<Void, Void, HashMap<String,ArrayList<String>>>() {
            @Override
            protected HashMap<String,ArrayList<String>> doInBackground(Void... params) {

                HashMap<String,ArrayList<String>> approveMap= ApproveRequestModel.getApproveform(pref.getString("deptID","no name"));
                return approveMap;
            }
            @Override
            protected void onPostExecute(HashMap<String,ArrayList<String>> result) {
                approvaMap =result;
                Log.i("Size", String.valueOf(result.size()));
                ApproveRequestAdapter adapter = new ApproveRequestAdapter(ApproveRequestFormActivity.this);
                ListView itemlistview = findViewById(R.id.Listview) ;
                itemlistview.setAdapter(adapter);
            }

        }.execute();
    }
// initialize ViewHolder for adapter.
    public static class ViewHolder{
        public TextView date;
        public TextView empName;
        public Button approve;
        public Button reject;
        public LinearLayout listitems ;
    }
}
