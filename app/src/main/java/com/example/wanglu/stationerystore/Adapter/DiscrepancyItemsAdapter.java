package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private List<Boolean> checkBoxes = new ArrayList<>();

    public DiscrepancyItemsAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<DiscrepancyItemsModel> data) {
        this.data = data;
        fillLists();
    }

    public List<DiscrepancyItemsModel> getData() {
        List<DiscrepancyItemsModel> returnedData = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            if (checkBoxes.get(i)) {
                returnedData.add(data.get(i));
            }
        }
        return returnedData;
    }

    public boolean validateData() {
        for (int i = 0; i < getCount(); i++) {
            if (checkBoxes.get(i) && data.get(i).getReason().equals(""))
                return false;
        }
        return true;
    }

    public void fillLists() {
        checkBoxes.clear();

        for (DiscrepancyItemsModel item : data) {
            checkBoxes.add(false);
        }
    }

    @Override
    public int getCount() {
        if (data != null)
            return data.size();
        else
            return 0;
    }

    @Override
    public DiscrepancyItemsModel getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private class ViewHolder {
        TextView itemDescriptionView, quantityView, amountView;
        EditText reasonEditText;
        CheckBox checkBox;

        public ViewHolder(View view, int position) {
            initializeView(view);
            setEventHandlers(position);
            setValues(position);
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
            amountView.setText(datum.getTotalAmount());
        }

        private void setEventHandlers(final int position) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    checkBoxes.set(position, b);
                }
            });

            reasonEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    data.get(position).setReason(reasonEditText.getText().toString());
                }
            });
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.content_discrepency_detail, null);
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
