package com.example.wanglu.stationerystore.StoreRequisition.stationeryRetrieval;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;

import com.example.wanglu.stationerystore.Adapter.RetrievalFormAdapter;
import com.example.wanglu.stationerystore.R;

import java.util.List;

public class StationeryRetrievalFormActivity extends AppCompatActivity {

    Button  submitbutton;
    GridLayout cardview ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrieval_form);
//
          ListView listView ;
          GridLayout gridLayout = null;
//        submitbutton = findViewById(R.id.submitButton);
          listView = findViewById(R.id.listview);
          gridLayout = findViewById(R.id.gridlayout);
          RetrievalFormAdapter adapter = new RetrievalFormAdapter(this);
          listView.setAdapter(adapter);


    }

}
