package com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Adapter.DiscrepancyItemsAdapter;
import com.example.wanglu.stationerystore.Model.DiscrepancyItemsModel;
import com.example.wanglu.stationerystore.Navigation.NavigationForManager;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;

public class DiscrepancyItemsActivity extends AppCompatActivity {
    Button submitbtn;

    ArrayList<DiscrepancyItemsModel> discrepancyItemList;
    private void getData(){
        discrepancyItemList = new ArrayList<DiscrepancyItemsModel>();
        discrepancyItemList.add(new DiscrepancyItemsModel("C100", "Pen 2B with eraser", "50",
                "Dozen", "280", "Damaged"));
        discrepancyItemList.add(new DiscrepancyItemsModel("C101", "Ring file", "-20",
                "Dozen", "120", "Missing"));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_discrepency);

        getData();
        DiscrepancyItemsAdapter adapter = new DiscrepancyItemsAdapter
                (this, R.layout.content_discrepency_detail, discrepancyItemList);
        ListView listView = findViewById(R.id.discrepencyItemListView);
        listView.setAdapter(adapter);

        submitbtn = (Button) findViewById(R.id.submitButton);
        submitbtn.setEnabled(false);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DiscrepancyItemsActivity.this,"Submit Successful!",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(DiscrepancyItemsActivity.this, NavigationForManager.class);
                startActivity(intent);
            }
        });

    }
//    public void setSubmitButtonState(boolean bool) {
//
//        submitbtn.setEnabled(bool);
//    }

    protected void onClickSubmit(View v){
        makeAlertDialog();
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
}
