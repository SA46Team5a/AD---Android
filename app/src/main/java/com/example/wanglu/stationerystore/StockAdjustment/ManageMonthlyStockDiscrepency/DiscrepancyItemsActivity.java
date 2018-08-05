package com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.DiscrepancyItemsAdapter;
import com.example.wanglu.stationerystore.Model.DiscrepancyItemsModel;
import com.example.wanglu.stationerystore.R;

import java.util.List;

//Author: Benedict, Jack
public class DiscrepancyItemsActivity extends AppCompatActivity {
    Button submitBtn;
    ListView listView;
    DiscrepancyItemsAdapter adapter;
    public SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_discrepency);
        initializeViews();
        setEventHandlers();

        new getStockVouchers().execute();
   }

    private void initializeViews() {
        listView = findViewById(R.id.discrepencyItemListView);
        submitBtn = (Button) findViewById(R.id.submitButton);
        pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    private void setEventHandlers() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getData().size() > 0)
                    makeAlertDialog();
                else
                    Toast.makeText(DiscrepancyItemsActivity.this, "No vouchers to submit", Toast.LENGTH_SHORT).show();
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
                     new submitStockVouchers().execute();
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

    private class getStockVouchers extends AsyncTask<Void, Void, List<DiscrepancyItemsModel>> {
        @Override
        protected List<DiscrepancyItemsModel> doInBackground(Void... voids) {
            boolean isManager;
            if(pref.getString("empID","no name").equals("E011"))
                isManager=true;
            else
                isManager=false;

            return DiscrepancyItemsModel.getStockVouchers(isManager);
        }

        @Override
        protected void onPostExecute(List<DiscrepancyItemsModel> data) {
            adapter = new DiscrepancyItemsAdapter(DiscrepancyItemsActivity.this);
            if (data.size() > 0) {
                adapter.setData(data);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(DiscrepancyItemsActivity.this, "No outstanding stock vouchers", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class submitStockVouchers extends AsyncTask<Void, Void, Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {
            if (adapter.validateData()) {
                SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                return DiscrepancyItemsModel.submitStockVouchers(adapter.getData(), pref.getString("empID", "E011"));
            }
            else
                return false;
        }

        protected void onPostExecute(Boolean submitted) {
            if (!submitted) {
                 Toast.makeText(DiscrepancyItemsActivity.this, "Submission failed", Toast.LENGTH_SHORT).show();
            } else {
                recreate();
            }
       }
    }
}
