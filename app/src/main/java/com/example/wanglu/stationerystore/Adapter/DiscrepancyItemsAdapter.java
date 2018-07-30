package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wanglu.stationerystore.Model.DiscrepancyItemsModel;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.List;

//Author: Benedict, Jack
public class DiscrepancyItemsAdapter extends BaseAdapter{
    private List<DiscrepancyItemsModel> data;
    private LayoutInflater inflater ;
    private List<EditText> reasons = new ArrayList<EditText>();
    private List<CheckBox> checkBoxes = new ArrayList<CheckBox>();

    public DiscrepancyItemsAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<DiscrepancyItemsModel> data) {
        this.data = data;
        fillLists();
    }

    public List<DiscrepancyItemsModel> getData() {
        updateData();
        List<DiscrepancyItemsModel> returnedData = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            if (checkBoxes.get(i).isChecked()) {
                returnedData.add(data.get(i));
            }
        }
        return returnedData;
    }

    public void updateData() {
        for (int i = 0; i < getCount(); i++) {
            DiscrepancyItemsModel d = getItem(i);
            d.setReason(reasons.get(i).getText().toString());
        }
    }

    public boolean validateData() {
        for (int i = 0; i < getCount(); i++) {
            if (reasons.get(i).getText().toString().trim().equals(""))
                return false;
        }
        return true;
    }

    public void fillLists() {
        reasons.clear();
        checkBoxes.clear();

        for (DiscrepancyItemsModel item : data) {
            reasons.add(null);
            checkBoxes.add(null);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public DiscrepancyItemsModel getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView itemDescriptionView, quantityView, amountView;
        EditText reasonEditText;
        CheckBox checkBox;

        public ViewHolder(View view) {
            initializeView(view);
        }
        private void initializeView(View view) {
            reasonEditText = (EditText) view.findViewById(R.id.reasonEditText);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            itemDescriptionView = (TextView) view.findViewById(R.id.itemdescriptionView);
            quantityView = (TextView) view.findViewById(R.id.quantityView);
            amountView = (TextView) view.findViewById(R.id.amountView);
        }

        private void setValues(int position) {
            DiscrepancyItemsModel datum = getItem(position);
            reasonEditText.setText(datum.getReason());
            itemDescriptionView.setText(datum.getItemName());
            quantityView.setText(String.valueOf(datum.getAdjustment()) + " " + datum.getUnitOfMeasure());
            amountView.setText(String.valueOf(datum.getAdjustment()) + " " + datum.getUnitOfMeasure());
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.content_discrepency_detail, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else
            holder = (ViewHolder) view.getTag();

        holder.setValues(position);
        return view;
    }
}
