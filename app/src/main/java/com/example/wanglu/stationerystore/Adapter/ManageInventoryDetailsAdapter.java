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

import com.example.wanglu.stationerystore.Model.DiscrepancyItemsModel;
import com.example.wanglu.stationerystore.R;

import java.util.List;

//Author: Benedict, Jack
public class ManageInventoryDetailsAdapter extends BaseAdapter {
    private List<DiscrepancyItemsModel> data;
    private LayoutInflater inflater;
    private Context context;

    public ManageInventoryDetailsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setData(List<DiscrepancyItemsModel> data) {
        this.data = data;
    }

    public List<DiscrepancyItemsModel> getData() {
        return data;
    }

    public boolean validateData() {
        for (DiscrepancyItemsModel datum : data) {
            if (datum.getActualQty() != datum.getOriginalQty() && datum.getReason().trim().equals(""))
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
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return data.size();
    }

    private class ViewHolder {
        TextView itemCode, itemDescription, qtyInStock;
        EditText actualCount, reason;

        public ViewHolder(View view, int position) {
            initializeViews(view);
            setEventHandlers(position);
            setValues(position);
        }

        private void initializeViews(View view) {
            itemCode = view.findViewById(R.id.itemcodeView);
            itemDescription = view.findViewById(R.id.itemdescriptionView);
            qtyInStock = view.findViewById(R.id.stockrecordView);
            actualCount = view.findViewById(R.id.actualstockEditText);
            reason = view.findViewById(R.id.reasonEditText);
            reason.setHint("Enter Reason");
        }

        private void setValues(int position) {
            DiscrepancyItemsModel d = data.get(position);
            itemCode.setText(d.getItemId());
            itemDescription.setText(d.getItemName());
            qtyInStock.setText(d.getQtyAndUom());
            try {
                actualCount.setText(String.valueOf(d.getActualQty()));
            } catch (Exception e) {
                actualCount.setText(String.valueOf(d.getOriginalQty()));
            }
        }

        private void setEventHandlers(final int position) {
            actualCount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String text = actualCount.getText().toString().trim();
                    if (text.equals("")) {
                        int qty = Integer.valueOf(text);
                        data.get(position).setActualQty(qty);
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
