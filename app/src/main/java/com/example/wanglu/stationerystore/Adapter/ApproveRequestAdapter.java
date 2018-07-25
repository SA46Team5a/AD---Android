package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wanglu.stationerystore.DepRequisition.ApproveRequisitionForm.ApproveRequestFormActivity;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.security.AccessController.getContext;
//Author: Wang Lu
public class ApproveRequestAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private List<Map<String, Object>> data = getData();
//  Read hashmap inside list
    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map;
        for (int i = 0; i < 10; i++) {
            map = new HashMap<>();
            map.put("date", "20/07/2018");
            map.put("empName", "JacWong");
            List<Map<String, String>> innerList = new ArrayList<>();
            Map<String, String> innerMap;
            for (int j = 0; j < 5; j++) {
                innerMap = new HashMap<>();

                innerMap.put("itemDetail", "2B Pencil");
                innerMap.put("quantity", "50 Dozen");
                innerList.add(innerMap);
            }
            map.put("items", innerList);
            list.add(map);
        }

        return list;
    }

    public ApproveRequestAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
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

        ApproveRequestFormActivity.ViewHolder holder;
// initialize controls
        if (view == null) {
            holder = new ApproveRequestFormActivity.ViewHolder();
            view = mInflater.inflate(R.layout.content_approve_form, null);
            holder.date = (TextView) view.findViewById(R.id.dateLabel);
            holder.empName = (TextView) view.findViewById(R.id.employeenameLabel);
            //holder.itemlistview = (ListView) view.findViewById(R.id.Listview);
            holder.approve = (Button) view.findViewById(R.id.approveButton);
            holder.reject = (Button) view.findViewById(R.id.rejectButton);
            holder.listitems = (LinearLayout) view.findViewById(R.id.LinearLayoutforlist);

            view.setTag(holder);

        }
        else {
            holder = (ApproveRequestFormActivity.ViewHolder) view.getTag();
        }
// set texts
        holder.date.setText((String) data.get(position).get("date"));
        holder.empName.setText((String) data.get(position).get("empName"));
//set lists texts
        List<Map<String, String>> itemList = (List) data.get(position).get("items");
        holder.listitems.removeAllViews();
        for (Map<String, String> item : itemList) {
            View v = mInflater.inflate(R.layout.content_itemlist, null);
            TextView descriptionView = v.findViewById(R.id.descriptionView);
            TextView quantityView = v.findViewById(R.id.quantityView);
            descriptionView.setText(item.get("itemDetail"));
            quantityView.setText(item.get("quantity"));
            holder.listitems.addView(v);
        }
        return view;
    }

}
