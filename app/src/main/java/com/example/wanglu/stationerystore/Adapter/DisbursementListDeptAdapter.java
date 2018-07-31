package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Model.DisbursementDetailModel;
import com.example.wanglu.stationerystore.R;

import java.util.List;

//Author:Luo Chao
public class DisbursementListDeptAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<DisbursementDetailModel> data;
    private Context context;

    public DisbursementListDeptAdapter  (Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<DisbursementDetailModel> data){
        this.data = data;
    }

    public List<DisbursementDetailModel> getData() {
        updateData();
        return data;
    }

    public void updateData() {
        DisbursementDetailModel datum;
    }

    public boolean validateData() {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getCollectedQuantity() == 0)
                return false;
            else if (data.get(i).getCollectedQuantity() != data.get(i).getDisbursedQuantity()
                    && data.get(i).equals(""))
                return false;
        }
        return true;
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
            setEventHandlers(position);
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
        }

        private void setEventHandlers(final int position) {
            quantitycollectedView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    int qty;
                    if (!quantitycollectedView.getText().toString().equals("")) {
                        qty = Integer.valueOf(quantitycollectedView.getText().toString());
                        data.get(position).setCollectedQuantity(qty);
                    } else {
                        Toast.makeText(context, "Quantity collected must not be blank", Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.setValues(position);
        return view;
    }
}
