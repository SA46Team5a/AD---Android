package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.MainActivity;
import com.example.wanglu.stationerystore.R;
import com.example.wanglu.stationerystore.StoreRequisition.stationeryRetrieval.StationeryRetrievalFormActivity;
import com.example.wanglu.stationerystore.StoreRequisition.stationeryRetrieval.SubmitAdjustmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class RetrievalFormAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private PopupWindow mpopup;

    private static final String TAG = "ContentAdapter";
    private List<String> mContentList;
    private StationeryRetrievalFormActivity activity;


    private List<Map<String, Object>> data = getData();
    //  Read hashmap inside list
    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map;
        for (int i = 0; i < 13; i++) {
            map = new HashMap<>();
            map.put("itemDescription", "1.pencil");
            map.put("quantityRequired", "12 Dozen");
            map.put("actualStock", "12 Dozen");
            map.put("totalRetrieved", " 9 Dozen");

            list.add(map);
        }
        return list;
    }
    public RetrievalFormAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.activity = (StationeryRetrievalFormActivity) context;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount");
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        Log.i(TAG, "getItem");
        return null;
    }

    @Override
    public long getItemId(int i) {
        Log.i(TAG, "getItemId");
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        Log.i(TAG, "getView");

        ViewHolder holder;
        if (view ==null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.controls,null);
            holder.itemName = view.findViewById(R.id.itemnameLabel);
            holder.quantityRequired = (TextView) view.findViewById(R.id.quantityLabel);
            holder.actualstockNumber = view.findViewById(R.id.actualstockView);
            holder.quantityNumber = view.findViewById(R.id.quantitynumbervView);
            holder.stockLabel = view.findViewById(R.id.actualstockView);
            holder.totalRetrived = view.findViewById(R.id.totalLabel);
            holder.retrievalNumber = view.findViewById(R.id.totalretrivedView);
            holder.submitadjustmentBtn = view.findViewById(R.id.submitadjustmentButton);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.itemName.setText((String) data.get(position).get("itemDescription"));
        holder.quantityNumber.setText((String) data.get (position).get("quantityRequired"));
        holder.actualstockNumber.setText((String) data.get(position).get("actualStock"));
        holder.retrievalNumber.setText((String) data.get(position).get("totalRetrieved"));

        holder.submitadjustmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAdjustmentDialogBuilder alertBuilder = new submitAdjustmentDialogBuilder(activity, position);
                alertBuilder.show();
            }
        });
        holder.submitadjustmentBtn.setTag(position);

        return view;
    }

    public static class ViewHolder{
         public TextView itemName;
         public TextView quantityRequired;
         public TextView quantityNumber;
         public TextView stockLabel;
         public TextView totalRetrived;
         public TextView actualstockNumber;
         public EditText retrievalNumber;
         public Button submitadjustmentBtn;

        public TextView itemdesp;
    }


    private class submitAdjustmentDialogBuilder extends AlertDialog.Builder {

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = mInflater.inflate(R.layout.activity_retrieval_popupwindow, null);

        private submitAdjustmentDialogBuilder(@NonNull final Context context, int position) {
            super(context);
            Map<String, Object> dataItem = data.get(position);
            //TextView itemdesp = (TextView) v.findViewById(R.id.itemdespLabel);
            TextView itemview = (TextView) v.findViewById(R.id.itemdespView);
           // TextView unites = (TextView) v.findViewById(R.id.unitLabel);
            TextView unitview = v.findViewById(R.id.unitsView);
           // TextView balance = v.findViewById(R.id.stockrecordLabel);
            TextView balanceview = v.findViewById(R.id.stockrecordView);
          //  TextView actualstock = v.findViewById(R.id.actualLabel);
            // TextView reason = v.findViewById(R.id.reasonLabel);
            EditText actualstockview = v.findViewById(R.id.actualstockView);
            EditText reasonview = v.findViewById(R.id.reasonView);

            itemview.setText((String)dataItem.get("itemDescription"));
            balanceview.setText((String)dataItem.get("quantityRequired"));
            actualstockview.setText((String)dataItem.get("actualStock"));


//            TextView input2 = new TextView(context);
//            input2.setText("ohlhsag");
//            input2.setLayoutParams(params);
//            container.addView(input2);
//            EditText input3 = new EditText(context);
//            input3.setText("6");
//            input3.setLayoutParams(params);
//            container.addView(input3);

//            itemdesp.setText("ktisme");
//            itemdesp.setLayoutParams(params);
//            container.addView(itemdesp);
//            EditText input3 = new EditText(context);

//            itemview.setText("hahaha");

//            itemview.setLayoutParams(params);

//            container.addView(itemview);

            
//            setTitle("Stock Adjustment");
            setCancelable(true);
            setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, "cancel is pressed", Toast.LENGTH_SHORT).show();
                }
            });
            setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, "confirm is pressed", Toast.LENGTH_SHORT).show();
                }
            });
            setView(v);
        }
    }
}

