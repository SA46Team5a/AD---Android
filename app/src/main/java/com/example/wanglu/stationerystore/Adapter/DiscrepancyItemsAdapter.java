package com.example.wanglu.stationerystore.Adapter;


import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wanglu.stationerystore.Model.DiscrepancyItemsModel;
import com.example.wanglu.stationerystore.Model.ManageInventoryDetailsModel;
import com.example.wanglu.stationerystore.R;

import java.util.List;

//Author: Benedict
public class DiscrepancyItemsAdapter extends ArrayAdapter<DiscrepancyItemsModel>{
    private List<DiscrepancyItemsModel> items;
    int resource;

    public DiscrepancyItemsAdapter(Context context, int resource, List<DiscrepancyItemsModel> items){
        super(context, resource, items);
        this.resource = resource;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, null);
        DiscrepancyItemsModel discrepancyItem = items.get(position);
        if(discrepancyItem!=null){
            int[] ids = {R.id.itemdescriptionView, R.id.quantityView, R.id.amountView};
            String[] keys = {"description", "quantityAdjustment", "amount"};
            for(int i=0; i<keys.length; i++){
                TextView tv = (TextView) v.findViewById(ids[i]);
                tv.setText(discrepancyItem.get(keys[i]));

            }
            EditText e = (EditText) v.findViewById(R.id.reasonEditText);
            e.setText(discrepancyItem.get("reason"));

        }
        return v;
    }

}
