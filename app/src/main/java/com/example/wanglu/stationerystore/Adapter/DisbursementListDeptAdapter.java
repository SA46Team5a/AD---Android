package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wanglu.stationerystore.DepRequisition.ApproveRequisitionForm.ApproveRequestFormActivity;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisbursementListDeptAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    //create data to list
    ArrayList<String> itemsIDList=new ArrayList<String>(){{add("1");add("2");add("3");add("4");add("5");}};
    ArrayList<String> descriptionList=new ArrayList<String>(){{add("pen1");add("pen2");add("pen3");add("pen4");add("pen5");}};
    ArrayList<String> quantityList=new ArrayList<String>(){{add("5");}{add("4");}{add("3");}{add("2");}{add("1");}};
    public static class ViewHolder{
        public TextView titleLabel;
        public TextView quantityLable;
        public TextView quantityView;
        public TextView quantitycollectedLabel;
        public TextView reasonLabel;
        public EditText quantitycollectedView;
        public EditText reasonView;

    }
    public DisbursementListDeptAdapter  (Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemsIDList.size();
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

        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.content_disbursement_list, null);
            holder.titleLabel = (TextView) view.findViewById(R.id.titleLabel);
            holder.quantityLable=view.findViewById(R.id.quantityLabel);
            holder.quantityView=view.findViewById(R.id.quantityView);
            holder.quantitycollectedLabel=view.findViewById(R.id.quantitycollectedLabel);
            holder.reasonLabel=view.findViewById(R.id.reasonLabel);
            holder.quantitycollectedView=view.findViewById(R.id.quantitycollectedView);
            holder.reasonView=view.findViewById(R.id.reasonView);


            view.setTag(holder);

        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.titleLabel.setText(descriptionList.get(position));
        holder.quantityLable.setText("Quantity:");
        holder.quantityView.setText(quantityList.get(position));
        holder.quantitycollectedLabel.setText("Quantity Collected:");
        holder.reasonLabel.setText("Shortage Reason");

//        holder.date.setText((String) data.get(position).get("date"));
//        holder.empName.setText((String) data.get(position).get("empName"));
//
//        List<Map<String, String>> itemList = (List) data.get(position).get("items");
//        holder.listitems.removeAllViews();
//        for (Map<String, String> item : itemList) {
//            View v = mInflater.inflate(R.layout.content_itemlist, null);
//            TextView descriptionView = v.findViewById(R.id.descriptionView);
//            TextView quantityView = v.findViewById(R.id.quantityView);
//            descriptionView.setText(item.get("itemDetail"));
//            quantityView.setText(item.get("quantity"));
//            holder.listitems.addView(v);
//        }
        return view;
    }
}
