package com.example.wanglu.stationerystore.Orders.restockInventory;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.RestockInventoryAdapter;
import com.example.wanglu.stationerystore.R;
import com.example.wanglu.stationerystore.StoreRequisition.ConfirmDisbursementList.DisbursementListDeptActivity;

import java.util.ArrayList;
//Author:Luo Chao
public class RestockInventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activety_restock_inventory);
        ListView inventoryItemList=findViewById(R.id.inventoryItemList);
        RestockInventoryAdapter inventoryAdapter=new RestockInventoryAdapter(this);
        inventoryItemList.setAdapter(inventoryAdapter);

        //load two dropdownlist
        Spinner orderidDropdownlist=findViewById(R.id.orderidDropdownlist);
        Spinner supplierDropdownlist=findViewById(R.id.supplierDropdownlist);
        //load orderIDdropdown
        ArrayList<String> orderIDList=new ArrayList<String>(){{add("5");}{add("4");}{add("3");}{add("2");}{add("1");}};
        final ArrayAdapter<String> orderIDdropdownlistAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, orderIDList);
        orderIDdropdownlistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderidDropdownlist.setAdapter(orderIDdropdownlistAdapter);
        orderidDropdownlist.setOnItemSelectedListener(dropdownlistener);
        //load supplierdropdownlist
        ArrayList<String> supplierList=new ArrayList<String>(){{add("supplier1");add("supplier2");add("supplier3");add("supplier4");add("supplier5");}};
        final ArrayAdapter<String> supplierDropdownistAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, supplierList);
        supplierDropdownistAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplierDropdownlist.setAdapter(supplierDropdownistAdapter);
        supplierDropdownlist.setOnItemSelectedListener(dropdownlistener);

    }

    //event handler for dropdownlist
    private AdapterView.OnItemSelectedListener dropdownlistener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}
