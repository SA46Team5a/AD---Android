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
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.DiscrepancyItemsAdapter;
import com.example.wanglu.stationerystore.Model.DiscrepancyItemsModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForManager;
import com.example.wanglu.stationerystore.R;

import java.util.List;

//Author: Benedict, Jack
public class DiscrepancyItemsActivity extends AppCompatActivity {
    Button submitBtn;
    ListView listView;
    DiscrepancyItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_discrepency);
        initializeViews();
        setEventHandlers();

        new getStockVouchers().execute(true); // TODO: get role from SharedPreferences. true if is Store Manager
   }

    private void initializeViews() {
        listView = findViewById(R.id.discrepencyItemListView);
        submitBtn = (Button) findViewById(R.id.submitButton);
    }

    private void setEventHandlers() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new submitStockVouchers().execute();
            }
        });
    }

    void makeAlertDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Submission of Adjustment Voucher")
                .setMessage("Updated stock level and reason for discrepancies will be submitted. Would you like to continue?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do post?
                        Toast.makeText(DiscrepancyItemsActivity.this, getString(android.R.string.yes), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DiscrepancyItemsActivity.this, getString(android.R.string.no), Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private class getStockVouchers extends AsyncTask<Boolean, Void, List<DiscrepancyItemsModel>> {
        @Override
        protected List<DiscrepancyItemsModel> doInBackground(Boolean... booleans) {
            return DiscrepancyItemsModel.getStockVouchers();
        }

        @Override
        protected void onPostExecute(List<DiscrepancyItemsModel> data) {
            adapter = new DiscrepancyItemsAdapter(DiscrepancyItemsActivity.this);
            adapter.setData(data);
            listView.setAdapter(adapter);
        }
    }

    private class submitStockVouchers extends AsyncTask<Void, Void, Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {
            if (adapter.validateData()) {
                return DiscrepancyItemsModel.submitStockVouchers(adapter.getData());
            }
            else
                return false;
        }

        protected void onPostExecute(Boolean submitted) {
            if (submitted) {
                 makeAlertDialog();
                 Intent intent= new Intent(DiscrepancyItemsActivity.this, NavigationForManager.class);
                 startActivity(intent);

            } else {
                 Toast.makeText(DiscrepancyItemsActivity.this, "Submission failed", Toast.LENGTH_SHORT).show();
            }
       }
    }
}
