package com.example.wanglu.stationerystore.Orders.restockInventory;


import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.RestockInventoryAdapter;
import com.example.wanglu.stationerystore.DepRequisition.AppointDeptRep.AppointRepActivity;
import com.example.wanglu.stationerystore.Model.AppointRepModel;
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
    String selected;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activety_restock_inventory);
        ListView inventoryItemList=findViewById(R.id.inventoryItemList);
        RestockInventoryAdapter inventoryAdapter=new RestockInventoryAdapter(this);
        inventoryItemList.setAdapter(inventoryAdapter);

        //load two dropdownlist


        //load orderIDdropdown

        //load supplierdropdownlist
//        ArrayList<String> supplierList=new ArrayList<String>(){{add("supplier1");add("supplier2");add("supplier3");add("supplier4");add("supplier5");}};


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
                HashMap<String,ArrayList<String>> orderIDMap = new HashMap<>();
             //   HashMap<String,ArrayList<String>> supplierMap = new HashMap<>();
                orderIDMap= RestockInventoryModel.getOrderId();
             //   supplierMap = RestockInventoryModel.getSupplier();
                return orderIDMap;
            }
            @Override
            protected void onPostExecute(HashMap<String,ArrayList<String>> result) {

                orderIDList = result.get ("OrderID");
                Spinner orderidDropdownlist=findViewById(R.id.orderidDropdownlist);
//                ArrayList<String> orderIDList=new ArrayList<String>(){{add("5");}{add("4");}{add("3");}{add("2");}{add("1");}};
                final ArrayAdapter<String> orderIDdropdownlistAdapter=new ArrayAdapter<String>(RestockInventoryActivity.this,android.R.layout.simple_spinner_item, orderIDList);
                orderIDdropdownlistAdapter.setDropDownViewResource(R.layout.spinner_item);
                orderidDropdownlist.setAdapter(orderIDdropdownlistAdapter);
                orderidDropdownlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selected= parent.getItemAtPosition(position).toString();
                        //String  orderid = supplierIDList.get(supplierNameList.indexOf(selected));
                        Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_SHORT).show();
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
//                ArrayList<String> orderIDList=new ArrayList<String>(){{add("5");}{add("4");}{add("3");}{add("2");}{add("1");}};
                final ArrayAdapter<String> supplierDropdownistAdapter=new ArrayAdapter<String>(RestockInventoryActivity.this,android.R.layout.simple_spinner_item, supplierNameList);
                supplierDropdownistAdapter.setDropDownViewResource(R.layout.spinner_item);
                supplierDropdownlist.setAdapter(supplierDropdownistAdapter);
                supplierDropdownlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selected= parent.getItemAtPosition(position).toString();
                        String  supplier = supplierIDList.get(supplierNameList.indexOf(selected));
                        Toast.makeText(getApplicationContext(),supplier,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

        }.execute();


    }

    //event handler for dropdownlist
//    private AdapterView.OnItemSelectedListener dropdownlistener=new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//        }
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }
//    };
}
