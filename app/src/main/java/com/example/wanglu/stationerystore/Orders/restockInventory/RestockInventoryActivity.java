package com.example.wanglu.stationerystore.Orders.restockInventory;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.wanglu.stationerystore.Adapter.RestockInventoryAdapter;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;

public class RestockInventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activety_restock_inventory);
        ListView inventoryItemList=findViewById(R.id.inventoryItemList);
        RestockInventoryAdapter inventoryAdapter=new RestockInventoryAdapter(this);
        inventoryItemList.setAdapter(inventoryAdapter);

        ArrayList<String> categoryList=new ArrayList<String>(){{add("Pen");add("Paper");add("Ruler");add("Tape");add("Ink");}};
        ArrayList<String> descriptionList=new ArrayList<String>(){{add("pen1");add("pen2");add("pen3");add("pen4");add("pen5");}};
        ArrayList<String> stockList=new ArrayList<String>(){{add("5");}{add("4");}{add("3");}{add("2");}{add("1");}};

    }
}
