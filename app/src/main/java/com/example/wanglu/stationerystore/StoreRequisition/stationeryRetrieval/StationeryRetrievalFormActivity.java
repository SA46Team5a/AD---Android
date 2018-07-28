package com.example.wanglu.stationerystore.StoreRequisition.stationeryRetrieval;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.RetrievalFormAdapter;
import com.example.wanglu.stationerystore.DepRequisition.DelegateAuthority.DelegateAuthorityActivity;
import com.example.wanglu.stationerystore.MainActivity;
import com.example.wanglu.stationerystore.Model.DelegateAuthorityModel;
import com.example.wanglu.stationerystore.Model.StationeryRetrievalFormModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForClerk;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//Wang Lu
public class StationeryRetrievalFormActivity extends AppCompatActivity implements AdapterView.OnClickListener {

    Button submitbutton;
    Button adjustmentbutton;
    CheckBox checkBox;
    public HashMap<String,ArrayList<String>> retrievalMap=new HashMap<>();
    public ArrayList<String> quantityList=new ArrayList<>();
    RetrievalFormAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval_form);
        ListView listView = findViewById(R.id.listview);
        submitbutton = findViewById(R.id.submitButton);
        submitbutton.setOnClickListener((View.OnClickListener) this);
        submitbutton.setEnabled(false);


        new AsyncTask<Void, Void, HashMap<String,ArrayList<String>>>() {
            @Override
            protected HashMap<String,ArrayList<String>> doInBackground(Void... params) {
                HashMap<String,ArrayList<String>> retrievalMap= StationeryRetrievalFormModel.getStationeryRetrievalFormList();
                return retrievalMap;
            }
            @Override
            protected void onPostExecute(HashMap<String,ArrayList<String>> result) {
                retrievalMap=result;
                Log.i("Size", String.valueOf(result.size()));
                ListView listView = findViewById(R.id.listview);
                adapter= new RetrievalFormAdapter(StationeryRetrievalFormActivity.this, result);
                listView.setAdapter(adapter);
               // quantityList=adapter.getAllTotalRetrieved();


            }

        }.execute();

    }
    public void setSubmitButtonState(boolean bool)
    {
        submitbutton.setEnabled(bool);
    }
    @Override
    public void onClick(View v) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                quantityList=adapter.getAllTotalRetrieved();
               StationeryRetrievalFormModel.updateStationeryRetrievalform(retrievalMap.get("ItemID"),quantityList,retrievalMap.get("disDutyId"));
               return null;
            }
            @Override
            protected void onPostExecute(Void result) {

            }

        }.execute();
        Intent intent = new Intent (StationeryRetrievalFormActivity.this,NavigationForClerk.class);
        startActivity(intent);
        Toast.makeText(this, "Submit Successful!", Toast.LENGTH_SHORT).show();


    }
}
