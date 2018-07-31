package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
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
    private List<EditText> quantitiesCollected = new ArrayList<>();
    private List<EditText> reasons = new ArrayList<>();

    public void setData(List<DisbursementDetailModel> data){
        this.data = data;
        fillLists();
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
            if (quantitiesCollected.get(i).getText().toString().equals(""))
                return false;
            else if (Integer.valueOf(quantitiesCollected.get(i).getText().toString()) != data.get(i).getDisbursedQuantity()
                    && reasons.get(i).getText().toString().equals(""))
                return false;
        }
        return true;
    }

    private void fillLists() {
        quantitiesCollected.clear();
        reasons.clear();

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

    public class ViewHolder {
        public TextView titleLabel;
        public TextView quantityLabel;
        public TextView quantityView;
        public TextView quantitycollectedLabel;
        public TextView reasonLabel;
        public EditText quantitycollectedView;
        public EditText reasonView;

        public ViewHolder(View view, int position) {
            initializeViews(view);
            setValues(position);
        }

        private void initializeViews(View view) {
            titleLabel = (TextView) view.findViewById(R.id.titleLabel);
            quantityLabel =view.findViewById(R.id.quantityLabel);
            quantityView=view.findViewById(R.id.quantityView);
            quantitycollectedLabel=view.findViewById(R.id.quantitycollectedLabel);
            reasonLabel=view.findViewById(R.id.reasonLabel);
            quantitycollectedView=view.findViewById(R.id.quantitycollectedView);
            reasonView=view.findViewById(R.id.reasonView);
        }

        private void setValues(int position)     {
            DisbursementDetailModel detail = data.get(position);
            titleLabel.setText(detail.getItemName());
            quantityLabel.setText("Quantity:");
            quantityView.setText(detail.getQtyAndUom());
            quantitycollectedLabel.setText("Quantity Collected:");
            quantitycollectedView.setText(String.valueOf(detail.getDisbursedQuantity()));
            quantitiesCollected.set(position, quantitycollectedView);
            reasons.set(position, reasonView);
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.content_disbursement_list, null);
            holder = new ViewHolder(view, position);
            view.setTag(holder);
        }
        return view;
    }
}
