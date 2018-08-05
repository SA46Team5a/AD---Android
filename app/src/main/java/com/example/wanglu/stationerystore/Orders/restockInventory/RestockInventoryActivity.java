package com.example.wanglu.stationerystore.Orders.restockInventory;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.wanglu.stationerystore.Adapter.RestockInventoryAdapter;
import com.example.wanglu.stationerystore.Model.RestockInventoryModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForClerk;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;

//Author:Luo Chao
public class RestockInventoryActivity extends AppCompatActivity {

    ArrayList<String> orderIDList=new ArrayList<>();
    ArrayList<String> supplierIDList=new ArrayList<>();
    ArrayList<String> supplierNameList = new ArrayList<>();
    String selectedOrderId;
    String SelectedSupplierId;
    public HashMap<String,ArrayList<String>> ItemListMap=new HashMap<>();
    public SharedPreferences pref;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activety_restock_inventory);

        pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Button homebtn = (Button) findViewById( R.id.homeBtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestockInventoryActivity.this, NavigationForClerk.class);
                startActivity(intent);
            }
        });
//Start AsyncTask
        new AsyncTask<Void, Void, HashMap<String,ArrayList<String>>>() {
            @Override
            protected HashMap<String,ArrayList<String>> doInBackground(Void... params) {
                HashMap<String,ArrayList<String>> orderIDMap;
                orderIDMap= RestockInventoryModel.getOrderId();
                return orderIDMap;
            }
            @Override
            protected void onPostExecute(HashMap<String,ArrayList<String>> result) {

                orderIDList = result.get ("OrderID");
                Spinner orderidDropdownlist=findViewById(R.id.orderidDropdownlist);

                final ArrayAdapter<String> orderIDdropdownlistAdapter=new ArrayAdapter<String>(RestockInventoryActivity.this,android.R.layout.simple_spinner_item, orderIDList);
                orderIDdropdownlistAdapter.setDropDownViewResource(R.layout.spinner_item);
                orderidDropdownlist.setAdapter(orderIDdropdownlistAdapter);
                orderidDropdownlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedOrderId= parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        }.execute();

        new AsyncTask<Void, Void, HashMap<String,ArrayList<String>>>() {
            @Override
            protected HashMap<String,ArrayList<String>> doInBackground(Void... params) {

                HashMap<String,ArrayList<String>> supplierMap = new HashMap<>();
                supplierMap = RestockInventoryModel.getSupplier();
                return supplierMap;
            }
            @Override
            protected void onPostExecute(HashMap<String,ArrayList<String>> result) {

                supplierIDList = result.get ("SupplierID");
                supplierNameList = result.get("SupplierName");
                Spinner supplierDropdownlist=findViewById(R.id.supplierDropdownlist);

                final ArrayAdapter<String> supplierDropdownistAdapter=new ArrayAdapter<String>(RestockInventoryActivity.this,android.R.layout.simple_spinner_item, supplierNameList);
                supplierDropdownistAdapter.setDropDownViewResource(R.layout.spinner_item);
                supplierDropdownlist.setAdapter(supplierDropdownistAdapter);
                supplierDropdownlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SelectedSupplierId= supplierIDList.get(position).toString();
                        }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        }.execute();

        Button searchbtn = (Button) findViewById(R.id.searchButton);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, HashMap<String,ArrayList<String>>>() {
                    @Override
                    protected HashMap<String, ArrayList<String>> doInBackground(Void... params) {
                        HashMap<String, ArrayList<String>> ItemListMap = new HashMap<>();

                        ItemListMap = RestockInventoryModel.getInventoryDetail(selectedOrderId,SelectedSupplierId);
//                       ItemListMap = RestockInventoryModel.getInventoryDetail("117","ALPA");// Hardcoded value will replace this with above one after real data comming
                        return ItemListMap;
                    }

                    @Override
                    protected void onPostExecute(HashMap<String, ArrayList<String>> result) {
                        ItemListMap = result;
                        Log.i("Size", String.valueOf(result.size()));
                        ListView inventoryItemList=findViewById(R.id.inventoryItemList);
                        RestockInventoryAdapter inventoryAdapter=new RestockInventoryAdapter(RestockInventoryActivity.this);
                        inventoryItemList.setAdapter(inventoryAdapter);
                    }
                }.execute();
            }
        });

    }

}
