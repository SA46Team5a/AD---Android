package com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.ManageInventoryDetailsAdapter;
import com.example.wanglu.stationerystore.Model.DiscrepancyItemsModel;
import com.example.wanglu.stationerystore.Model.ManageInventoryDetailsModel;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.List;

//Author: Benedict, Jack
public class ManageInventoryDetailsActivity extends AppCompatActivity {

    ArrayList<ManageInventoryDetailsModel> inventoryItemList;
    ListView listView;
    TextView categoryView;
    Button submitBtn;
    ManageInventoryDetailsAdapter adapter;

    String categoryId, categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_inventory_detail);
        getValuesFromIntent();
        initializeViews();
        setEventHandlers();

        new getStockItemsOfCategory().execute();
    }

    private void getValuesFromIntent() {
        Intent intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        categoryName = intent.getStringExtra("categoryName");
    }

    private void initializeViews() {
        categoryView = (TextView) findViewById(R.id.categoryView);
        categoryView.setText(categoryName);
        listView = (ListView) findViewById(R.id.manageInventoryListView);
        submitBtn = (Button) findViewById(R.id.submitButton);
    }

    private void setEventHandlers() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.validateData()){
                    makeAlertDialog();
                } else {
                    Toast.makeText(ManageInventoryDetailsActivity.this, "Reasons must be provided if there are discrepancies.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void makeAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Inventory submission")
                .setMessage("Updated stock number will be submitted. Would you like to continue?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new submitStockItems().execute();
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

    private class getStockItemsOfCategory extends AsyncTask<Void, Void, List<DiscrepancyItemsModel>> {
        @Override
        protected List<DiscrepancyItemsModel> doInBackground(Void... voids) {
            return DiscrepancyItemsModel.getStockCountsOfCategory(categoryId);
        }

        @Override
        protected void onPostExecute(List<DiscrepancyItemsModel> data) {
            adapter = new ManageInventoryDetailsAdapter(ManageInventoryDetailsActivity.this);
            adapter.setData(data);
            listView.setAdapter(adapter);
        }
    }

    private class submitStockItems extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            return DiscrepancyItemsModel.submitStockCountResults(adapter.getData());
        }

        @Override
        protected void onPostExecute(Boolean submitted){
            if (submitted)
                finish();
            else
                Toast.makeText(ManageInventoryDetailsActivity.this, "An error has occured and submission has failed. Please try again", Toast.LENGTH_SHORT).show();
        }
    }
}
