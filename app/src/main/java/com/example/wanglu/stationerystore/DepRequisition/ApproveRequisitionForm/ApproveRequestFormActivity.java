package com.example.wanglu.stationerystore.DepRequisition.ApproveRequisitionForm;

import android.app.ListActivity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    //ConstraintLayout itemlistform = null;
    ListView itemlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.content_approve_form);
        setContentView(R.layout.content_request_itemlist);
        approveform = findViewById(R.id.requestItemInclude);
        //itemlistform = findViewById(R.layout.content_request_itemlist);
        itemlistview = findViewById(R.id.requisitionListview);

//        List <String> items = list;
        //data = getData();
        MyAdapter adapter = new MyAdapter(this);
        itemlistview.setAdapter(adapter);
    }

//    public List<Map<String, Object>> getData() {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//        Map<String, Object> map;
//        for(int i=0;i<10;i++)
//        {
//            map = new HashMap<String, Object>();
//            map.put("date", "19/07/2018");
//            map.put("empName", "Jacky Wng");
//            list.add(map);
//        }
//        return list;
//    }

    public static class ViewHolder{
        public TextView date;
        public TextView empName;
        public Button approve;
        public Button reject;
        public ListView itemlistview;
        public TextView itemDetail;






    }
}
