package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import android.widget.TextView;
import com.example.wanglu.stationerystore.R;
import com.example.wanglu.stationerystore.StoreRequisition.stationeryRetrieval.StationeryRetrievalFormActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetrievalFormAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private List<Map<String, Object>> data = getData();
    //  Read hashmap inside list
    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map;
        for (int i = 0; i < 13; i++) {
            map = new HashMap<>();
            map.put("itemDescription", "1.pencil");
            map.put("quantityRequired", "12 Dozen");
            map.put("actualStock", "12 Dozen");
            map.put("totalRetrieved", " 9 Dozen");

            list.add(map);
        }
        return list;
    }
    public RetrievalFormAdapter(Context context) {
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

        ViewHolder holder;
        if (view ==null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.controls,null);
            holder.itemName = view.findViewById(R.id.itemnameLabel);
            holder.quantityRequired = (TextView) view.findViewById(R.id.quantityLabel);
            holder.actualstockNumber = view.findViewById(R.id.actualstockView);
            holder.quantityNumber = view.findViewById(R.id.quantitynumbervView);
            holder.stockLabel = view.findViewById(R.id.actualstockLabel);
            holder.totalRetrived = view.findViewById(R.id.totalLabel);
            holder.retrievalNumber = view.findViewById(R.id.totalretrivedView);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        //holder.quantityRequired.setText("Quantity Required");
        holder.itemName.setText((String) data.get(position).get("itemDescription"));
        holder.quantityNumber.setText((String) data.get (position).get("quantityRequired"));
       // holder.stockLabel.setText("Actual Stock");
        holder.actualstockNumber.setText((String) data.get(position).get("actualStock"));
        //holder.totalRetrived.setText("Total Recrieved");
        holder.retrievalNumber.setText((String) data.get(position).get("totalRetrieved"));

        return view;
    }

    public static class ViewHolder{
         public TextView itemName;
         public TextView quantityRequired;
         public TextView quantityNumber;
         public TextView stockLabel;
         public TextView totalRetrived;
         public TextView actualstockNumber;
         public EditText retrievalNumber;
    }
}

