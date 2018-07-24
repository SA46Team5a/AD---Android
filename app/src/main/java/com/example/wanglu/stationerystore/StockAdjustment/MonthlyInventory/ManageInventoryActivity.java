package com.example.wanglu.stationerystore.StockAdjustment.MonthlyInventory;

import android.content.Intent;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wanglu.stationerystore.Model.JSON.JSONParser;
import com.example.wanglu.stationerystore.Model.ManageInventoryDetailsModel;
import com.example.wanglu.stationerystore.R;
import com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency.ManageInventoryDetailsActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//Author: Benedict
public class ManageInventoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //private ConstraintLayout contentManagerInventory = null;
    ArrayList<String> categoryList;
    private void getData(){
        categoryList = new ArrayList<String>();
        JSONArray a = JSONParser.getJSONArrayFromUrl("url");
        try {
            for (int i = 0; i < a.length(); i++) {
                //JSONObject b = a.getJSONObject(i)
                categoryList.add(a.getString(i));
            }
        } catch (Exception e){
                Log.e("ManageInventoryActivity", "JSONArray error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_inventory);
        //contentManagerInventory = findViewById(R.id.contentManagerInventory);

        String[] categoryItems = {"Clip", "Envelop", "Eraser", "Exercise", "File", "Puncher"};
        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        //getData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, categoryItems);
        ListView listViewOfCategoryItems = (ListView) findViewById(R.id.categoryListView);
        listViewOfCategoryItems.setAdapter(adapter);
        listViewOfCategoryItems.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> av, View v, int position, long id){
        String item = (String) av.getAdapter().getItem(position);
        Intent i = new Intent(this, ManageInventoryDetailsActivity.class);
        i.putExtra("categoryItem", item);
        startActivity(i);
    }
}
