package com.example.wanglu.stationerystore.StoreRequisition.stationeryRetrieval;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.RetrievalFormAdapter;
import com.example.wanglu.stationerystore.MainActivity;
import com.example.wanglu.stationerystore.Navigation.NavigationForClerk;
import com.example.wanglu.stationerystore.R;

import java.util.List;

public class StationeryRetrievalFormActivity extends AppCompatActivity implements AdapterView.OnClickListener {

    Button submitbutton;
    Button adjustmentbutton;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrieval_form);

        ListView listView;
        // GridLayout gridLayout = null;
        listView = findViewById(R.id.listview);
        //gridLayout = findViewById(R.id.gridlayout);
        RetrievalFormAdapter adapter = new RetrievalFormAdapter(this);
        submitbutton = findViewById(R.id.submitButton);
        //adjustmentbutton = findViewById(R.id.submitadjustmentButton);
//        checkBox = findViewById(R.id.retrivalformCheckbox);
        listView.setAdapter(adapter);
        submitbutton.setOnClickListener((View.OnClickListener) this);
        submitbutton.setEnabled(false);
    }
    public void setSubmitButtonState(boolean bool)
    {
        submitbutton.setEnabled(bool);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent (StationeryRetrievalFormActivity.this,NavigationForClerk.class);
        startActivity(intent);
        Toast.makeText(this, "Submit Successful!", Toast.LENGTH_SHORT).show();


    }
}
