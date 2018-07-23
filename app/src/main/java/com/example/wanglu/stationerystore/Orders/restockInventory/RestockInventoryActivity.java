package com.example.wanglu.stationerystore.Orders.restockInventory;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.wanglu.stationerystore.Adapter.RestockInventoryAdapter;
import com.example.wanglu.stationerystore.R;

public class RestockInventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activety_restock_inventory);
        ListView inventoryItemList=findViewById(R.id.inventoryItemList);
        RestockInventoryAdapter inventoryAdapter=new RestockInventoryAdapter(this);
        inventoryItemList.setAdapter(inventoryAdapter);

    }
}
