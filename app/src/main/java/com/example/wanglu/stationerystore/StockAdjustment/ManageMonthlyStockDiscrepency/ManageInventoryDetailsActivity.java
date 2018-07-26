package com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.ManageInventoryDetailsAdapter;
import com.example.wanglu.stationerystore.Model.ManageInventoryDetailsModel;
import com.example.wanglu.stationerystore.R;
import com.example.wanglu.stationerystore.StockAdjustment.MonthlyInventory.ManageInventoryActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

//Author: Benedict
public class ManageInventoryDetailsActivity extends AppCompatActivity {

    ArrayList<ManageInventoryDetailsModel> inventoryItemList;
    private void getData(){
        inventoryItemList = new ArrayList<ManageInventoryDetailsModel>();
        inventoryItemList.add(new ManageInventoryDetailsModel("P010", "Pen Ballpoint Black", "Dozen",
                "500", "500", "none"));
        inventoryItemList.add(new ManageInventoryDetailsModel("P011", "Ring file", "Dozen",
                "200", "200", "none"));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_inventory_detail);

        TextView categoryView = findViewById(R.id.categoryView);
        categoryView.setText(getIntent().getStringExtra("categoryItem"));
        //TextView dateView = findViewById(R.id.dateView);
        //dateView.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));

        getData();
        ManageInventoryDetailsAdapter adapter = new ManageInventoryDetailsAdapter
                (this, R.layout.content_inventory_detail, inventoryItemList);
        ListView listView = findViewById(R.id.manageInventoryListView);
        listView.setAdapter(adapter);
        Button submitbtn = (Button) findViewById(R.id.submitButton);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAlertDialog();
            }
        });

    }

//    protected void onClickSubmit(View v){
//
//    }

    void makeAlertDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Inventory submission")
                .setMessage("Updated stock number will be submitted. Would you like to continue?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do post?
                        startActivity(new Intent(getApplicationContext(), ManageInventoryActivity.class));
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ManageInventoryDetailsActivity.this, getString(android.R.string.no), Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
