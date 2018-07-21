package com.example.wanglu.stationerystore.DepRequisition.ApproveRequisitionForm;

import android.app.ListActivity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wanglu.stationerystore.Adapter.MyAdapter;
import com.example.wanglu.stationerystore.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApproveRequestFormActivity extends AppCompatActivity {
    public static List<Map<String, Object>> data = null;
    ConstraintLayout approveform = null;
    ListView itemlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_request_itemlist);
        approveform = findViewById(R.id.requestItemInclude);
        itemlistview = findViewById(R.id.requisitionListview);
        MyAdapter adapter = new MyAdapter(this);
        itemlistview.setAdapter(adapter);
    }

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
