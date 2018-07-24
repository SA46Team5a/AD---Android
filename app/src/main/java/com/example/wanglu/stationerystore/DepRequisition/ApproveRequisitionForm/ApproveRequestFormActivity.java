package com.example.wanglu.stationerystore.DepRequisition.ApproveRequisitionForm;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wanglu.stationerystore.Adapter.ApproveRequestAdapter;
import com.example.wanglu.stationerystore.R;

import java.util.List;
import java.util.Map;
//Author: Wang Lu
public class ApproveRequestFormActivity extends AppCompatActivity {
    public static List<Map<String, Object>> data = null;
    ConstraintLayout approveform = null;
    ListView itemlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_form);
        //approveform = findViewById(R.id.requestItemInclude);
        itemlistview = findViewById(R.id.Listview);
        ApproveRequestAdapter adapter = new ApproveRequestAdapter(this);
        itemlistview.setAdapter(adapter);
    }
// initialize ViewHolder for adapter.
    public static class ViewHolder{
        public TextView date;
        public TextView empName;
        public Button approve;
        public Button reject;
        public ListView itemlistview;
        public TextView itemDetail;
        public LinearLayout listitems ;
        public TextView descreption;
        public TextView quantity;
    }
}
