package com.example.wanglu.stationerystore.StoreRequisition.ConfirmDisbursementList;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.wanglu.stationerystore.Adapter.DisbursementListDeptAdapter;
import com.example.wanglu.stationerystore.R;

public class DisbursementListDeptActivity extends AppCompatActivity {
    private ConstraintLayout disbursementInclude=null;
    ListView itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbersment_confirm);
        disbursementInclude=findViewById(R.id.disbursementInclude);
        itemsListView=findViewById(R.id.itemsListView);
        DisbursementListDeptAdapter adapter=new DisbursementListDeptAdapter(this);
        itemsListView.setAdapter(adapter);

    }
}
