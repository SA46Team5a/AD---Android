package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wanglu.stationerystore.Model.DiscrepancyItemsModel;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.List;

//Author: Benedict, Jack
public class ManageInventoryDetailsAdapter extends BaseAdapter {
    private List<DiscrepancyItemsModel> data;
    private List<EditText> actualCounts = new ArrayList<EditText>();
    private List<EditText> reasons = new ArrayList<EditText>();
    private LayoutInflater inflater;

    public ManageInventoryDetailsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<DiscrepancyItemsModel> data) {
        this.data = data;
        fillLists();
    }

    public List<DiscrepancyItemsModel> getData() {
        updateData();
        return data;
    }

    private void updateData() {
        DiscrepancyItemsModel datum;
        for (int i = 0; i < getCount(); i++) {
            datum = data.get(i);
            datum.setActualQty(Integer.valueOf(actualCounts.get(i).getText().toString()));
            datum.setReason(reasons.get(i).getText().toString());
        }
    }

    public boolean validateData() {
        updateData();
        for (DiscrepancyItemsModel datum : data) {
            if (datum.getActualQty() != datum.getOriginalQty() && datum.getReason().trim().equals(""))
                return false;
        }
        return true;
    }

    public void fillLists() {
        actualCounts.clear();
        reasons.clear();
        for (DiscrepancyItemsModel datum : data) {
            actualCounts.add(null);
            reasons.add(null);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView itemCode, itemDescription, qtyInStock;
        EditText actualCount, reason;

        public ViewHolder(View view, int position) {
            initializeViews(view);
            setValues(position);
        }

        public void initializeViews(View view) {
            itemCode = view.findViewById(R.id.itemcodeView);
            itemDescription = view.findViewById(R.id.itemdescriptionView);
            qtyInStock = view.findViewById(R.id.stockrecordView);
            actualCount = view.findViewById(R.id.actualstockEditText);
            reason = view.findViewById(R.id.reasonEditText);
        }

        public void setValues(int position) {
            DiscrepancyItemsModel d = data.get(position);
            itemCode.setText(d.getItemId());
            itemDescription.setText(d.getItemName());
            qtyInStock.setText(d.getQtyAndUom());

            actualCounts.set(position, actualCount);
            reasons.set(position, reason);
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.content_inventory_detail, null);
            holder = new ViewHolder(view, position);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.setValues(position);
        return view;
    }
}
