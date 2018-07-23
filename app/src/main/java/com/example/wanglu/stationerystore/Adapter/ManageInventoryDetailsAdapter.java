package com.example.wanglu.stationerystore.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wanglu.stationerystore.Model.ManageInventoryDetailsModel;
import com.example.wanglu.stationerystore.R;

import java.util.List;

//Author: Benedict
public class ManageInventoryDetailsAdapter extends ArrayAdapter<ManageInventoryDetailsModel> {
    private List<ManageInventoryDetailsModel> items;
    int resource;

    public ManageInventoryDetailsAdapter(Context context, int resource, List<ManageInventoryDetailsModel> items){
        super(context, resource, items);
        this.resource = resource;
        this.items = items;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, null);
        ManageInventoryDetailsModel inventoryItem = items.get(position);
        if(inventoryItem!=null){
            int[] ids = {R.id.itemnamelabel, R.id.itemdespview, R.id.unitsview, R.id.stockrecordview};
            String[] keys = {"itemCode", "description", "unitOfMeasure", "stockBalance"};
            for(int i=0; i<keys.length; i++){
                TextView tv = (TextView) v.findViewById(ids[i]);
                tv.setText(inventoryItem.get(keys[i]));

            }
            EditText e1 = (EditText) v.findViewById(R.id.actualstockLabel);
            e1.setText(inventoryItem.get("stockBalance"));
            //EditText e2 = (EditText) v.findViewById(R.id.reasonEditText);
            //e2.setText(inventoryItem.get(""));

        }
        return v;
    }
}
