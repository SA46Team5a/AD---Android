package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wanglu.stationerystore.Model.DisbursementDetailModel;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.List;

//Author:Luo Chao
public class DisbursementListDeptAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<DisbursementDetailModel> data;
    private List<EditText> quantitiesCollected;
    private List<EditText> reasons;

    public class ViewHolder{
        public TextView titleLabel;
        public TextView quantityLabel;
        public TextView quantityView;
        public TextView quantitycollectedLabel;
        public TextView reasonLabel;
        public EditText quantitycollectedView;
        public EditText reasonView;
    }

    public void setData(List<DisbursementDetailModel> data){
        this.data = data;
        fillEditTextLists();
    }

    public List<DisbursementDetailModel> getData() {
        updateData();
        return data;
    }

    public void updateData() {
        DisbursementDetailModel datum;
        for (int i = 0; i < data.size(); i++) {
            datum = data.get(i);
            datum.setCollectedQuantity(Integer.valueOf(quantitiesCollected.get(i).getText().toString()));
            datum.setReason(reasons.get(i).getText().toString());
        }
    }

    public boolean validateData() {
        for (int i = 0; i < data.size(); i++) {
            if (quantitiesCollected.get(i).getText().toString().matches(""))
                return false;
            else if (Integer.valueOf(quantitiesCollected.get(i).getText().toString()) != data.get(i).getDisbursedQuantity()
                    && reasons.get(i).getText().toString().matches(""))
                return false;
        }
        return true;
    }

    private void fillEditTextLists() {
        for (DisbursementDetailModel datum : data) {
            quantitiesCollected.add(null);
            reasons.add(null);
        }
    }

    public DisbursementListDeptAdapter  (Context context) {
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

        if (view == null) {
            holder = new ViewHolder();
            initializeViews(holder, view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        setValues(holder, position);
        return view;
    }

    protected void initializeViews(ViewHolder holder, View view) {
        view = mInflater.inflate(R.layout.content_disbursement_list, null);
        holder.titleLabel = (TextView) view.findViewById(R.id.titleLabel);
        holder.quantityLabel =view.findViewById(R.id.quantityLabel);
        holder.quantityView=view.findViewById(R.id.quantityView);
        holder.quantitycollectedLabel=view.findViewById(R.id.quantitycollectedLabel);
        holder.reasonLabel=view.findViewById(R.id.reasonLabel);
        holder.quantitycollectedView=view.findViewById(R.id.quantitycollectedView);
        holder.reasonView=view.findViewById(R.id.reasonView);
    }

    protected void setValues(ViewHolder holder, int position)     {
        DisbursementDetailModel detail = data.get(position);
        holder.titleLabel.setText(detail.getItemName());
        holder.quantityLabel.setText("Quantity:");
        holder.quantityView.setText(detail.getQtyAndUom());
        holder.quantitycollectedLabel.setText("Quantity Collected:");
        quantitiesCollected.set(position, holder.quantitycollectedView);
        reasons.set(position, holder.reasonView);
    }


}
