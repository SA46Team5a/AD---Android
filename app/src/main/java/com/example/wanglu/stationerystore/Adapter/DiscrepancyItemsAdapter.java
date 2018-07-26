package com.example.wanglu.stationerystore.Adapter;


import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wanglu.stationerystore.Model.DiscrepancyItemsModel;
import com.example.wanglu.stationerystore.Model.ManageInventoryDetailsModel;
import com.example.wanglu.stationerystore.R;
import com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency.DiscrepancyItemsActivity;

import java.util.ArrayList;
import java.util.List;

//Author: Benedict
public class DiscrepancyItemsAdapter extends ArrayAdapter<DiscrepancyItemsModel>{
    private List<DiscrepancyItemsModel> items;
    int resource;
    CheckBox select;
    public List<Boolean> checkedItem=new ArrayList<>();
    private DiscrepancyItemsActivity activity;

    public DiscrepancyItemsAdapter(Context context, int resource, List<DiscrepancyItemsModel> items){
        super(context, resource, items);
        this.resource = resource;
        this.items = items;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resource, null);
        DiscrepancyItemsModel discrepancyItem = items.get(position);
        if(discrepancyItem!=null){
            int[] ids = {R.id.itemdescriptionView, R.id.quantityView, R.id.amountView};
            String[] keys = {"description", "quantityAdjustment", "amount"};
            for(int i=0; i<keys.length; i++){
                TextView tv = (TextView) v.findViewById(ids[i]);
                tv.setText(discrepancyItem.get(keys[i]));
                //checkedItem.add(Integer.parseInt(discrepancyItem.get(keys[i])),false);

            }
//            select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    checkedItem.set(position, isChecked);
//                    activity.setSubmitButtonState(isAllChecked());
//                }
//            });
//            EditText e = (EditText) v.findViewById(R.id.reasonEditText);
//            e.setText(discrepancyItem.get("reason"));
        }
        return v;
    }
//    public boolean isAllChecked()
//    {
//        return !checkedItem.contains(false);
//    }

}
