package com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.wanglu.stationerystore.Model.ManagerInventoryModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForClerk;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;

//Author: Benedict, Jack
public class ManageInventoryActivity extends AppCompatActivity  {
    ListView categoryListView;
    Button homeBtn;
    ArrayList<String> categoryIDList=new ArrayList<>();
    ArrayList<String> categoryNameList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_inventory);
        initializeViews();
        setEventHandlers();
        new getCategories().execute();
    }

    private void initializeViews() {
        homeBtn= (Button) findViewById(R.id.homeBtn);
        categoryListView = (ListView) findViewById(R.id.categoryListView);
    }

    private void setEventHandlers() {
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageInventoryActivity.this, NavigationForClerk.class);
                startActivity(i);
            }
        });

        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ManageInventoryActivity.this, ManageInventoryDetailsActivity.class);
                i.putExtra("categoryName", categoryNameList.get(position));
                i.putExtra("categoryId", categoryIDList.get(position));
                startActivityForResult(i, -1);
            }
        });
    }

    private class getCategories extends AsyncTask<Void, Void, HashMap<String,ArrayList<String>>> {
        @Override
        protected HashMap<String,ArrayList<String>> doInBackground(Void... params) {
            HashMap<String,ArrayList<String>> category = new HashMap<>();
            category= ManagerInventoryModel.getCategories();
            return category;
        }
        @Override
        protected void onPostExecute(HashMap<String,ArrayList<String>> result) {
            categoryIDList = result.get ("CategoryID");
            categoryNameList = result.get ("CategoryName");

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManageInventoryActivity.this, android.R.layout.simple_list_item_1, categoryNameList);
            categoryListView.setAdapter(adapter);
        }
    }
}
