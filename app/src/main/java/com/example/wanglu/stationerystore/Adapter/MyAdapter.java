package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wanglu.stationerystore.DepRequisition.ApproveRequisitionForm.ApproveRequestFormActivity;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter{

    private LayoutInflater mInflater;

    private List<String> items;
    int resource;

    private List<Map<String, Object>> data = getData();
    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map;
        for(int i=0;i<10;i++)
        {
            map = new HashMap<>();
            map.put("date", "20/07/2018");
            map.put("empName", "JacWong");

            list.add(map);
        }
        for (int j = 0; j<10;j++)
        {
            map = new HashMap<>();
            map.put("itemDetail","2B Pencil");
            list.add(map);
        }
        return list;
    }

    public MyAdapter(Context context) {
            this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ApproveRequestFormActivity.ViewHolder holder ;

        if (view == null)
        {
            holder = new ApproveRequestFormActivity.ViewHolder();
            view = mInflater.inflate(R.layout.activity_approve_form,null);
            holder.date=(TextView) view.findViewById(R.id.dateLabel);
            holder.empName = (TextView) view.findViewById(R.id.employeenameLabel);
            holder.itemlistview = (ListView) view.findViewById(R.id.requisitionListview);
            holder.approve = (Button) view.findViewById(R.id.approveButton);
            holder.reject = (Button) view.findViewById(R.id.rejectButton);
            holder.itemDetail=(TextView) view.findViewById(R.id.itemdetailView);
            view.setTag(holder);
        }
        else
        {
            holder =(ApproveRequestFormActivity.ViewHolder)view.getTag();
        }
//        Map<String, Object> title= data.get(i);
//        Map<String, Object> empName= data.get(i);
        holder.date.setText((String) data.get(position).get("date"));
        holder.empName.setText((String) data.get(position).get("empName"));
        holder.itemDetail.setText((String)data.get(position).get("itemDetail") );
        return view;

    }


}
