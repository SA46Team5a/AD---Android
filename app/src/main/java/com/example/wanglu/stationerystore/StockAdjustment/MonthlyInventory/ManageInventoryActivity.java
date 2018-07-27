package com.example.wanglu.stationerystore.StockAdjustment.MonthlyInventory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Model.JSON.JSONParser;
import com.example.wanglu.stationerystore.Model.ManageInventoryDetailsModel;
import com.example.wanglu.stationerystore.Model.ManagerInventoryModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForClerk;
import com.example.wanglu.stationerystore.R;
import com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency.DiscrepancyItemsActivity;
import com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency.ManageInventoryDetailsActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

//Author: Benedict
public class ManageInventoryActivity extends AppCompatActivity  {

    ArrayList<String> categoryIDList=new ArrayList<>();
    ArrayList<String> categoryNameList=new ArrayList<>();
    private ConstraintLayout contentManagerInventory = null;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_inventory);
        Button homebtn = (Button) findViewById(R.id.homeBtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageInventoryActivity.this, NavigationForClerk.class);
                startActivity(i);
            }
        });

//Start AsyncTask
        new AsyncTask<Void, Void, HashMap<String,ArrayList<String>>>() {
            @Override
            protected HashMap<String,ArrayList<String>> doInBackground(Void... params) {
                HashMap<String,ArrayList<String>> category=new HashMap<>();
                category= ManagerInventoryModel.getCategories();
                return category;
            }
            @Override
            protected void onPostExecute(HashMap<String,ArrayList<String>> result) {
                categoryIDList = result.get ("CategoryID");
                categoryNameList = result.get ("CategoryName");
                ListView listViewOfCategoryItems = (ListView) findViewById(R.id.categoryListView);

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (ManageInventoryActivity.this, android.R.layout.simple_list_item_1, categoryNameList);
                listViewOfCategoryItems.setAdapter(adapter);
                listViewOfCategoryItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = (String) parent.getAdapter().getItem(position).toString();
                        Intent i = new Intent(ManageInventoryActivity.this, ManageInventoryDetailsActivity.class);
                        i.putExtra("categoryItem", item);
                        Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                });

            }
        }.execute();

    }
}
