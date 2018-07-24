package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Orders.restockInventory.RestockInventoryActivity;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
//Author:Luo Chao
public class RestockInventoryAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private RestockInventoryActivity stockActivity;
    //create data to list
    ArrayList<String> categoryList=new ArrayList<String>(){{add("Pen");add("Paper");add("Ruler");add("Tape");add("Ink");}};
    ArrayList<String> descriptionList=new ArrayList<String>(){{add("pen1");add("pen2");add("pen3");add("pen4");add("pen5");}};
    ArrayList<String> stockList=new ArrayList<String>(){{add("5");}{add("4");}{add("3");}{add("2");}{add("1");}};

    public static class ViewHolder{
        public TextView categoryLable;
        public TextView categoryView;
        public TextView descriptionLabel;
        public TextView descriptionView;
        public TextView stockLabel;
        public TextView stockView;
        public Button AddBtn;


    }
    public RestockInventoryAdapter  (Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.stockActivity=(RestockInventoryActivity) context;
    }

    @Override
    public int getCount() {
        return categoryList.size();
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
    public View getView(final int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.content_restock_inventory_itemlist, null);
            holder.categoryView=view.findViewById(R.id.categoryView);
            holder.descriptionView=view.findViewById(R.id.descriptionView);
            holder.stockView=view.findViewById(R.id.stockView);
            holder.AddBtn=view.findViewById(R.id.addBtn);

            view.setTag(holder);


        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.categoryView.setText(categoryList.get(position));
        holder.descriptionView.setText(descriptionList.get(position));
        holder.stockView.setText(stockList.get(position));
        holder.AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addStockQtyDialogBuilder alertBuilder=new addStockQtyDialogBuilder(stockActivity,position);
                alertBuilder.show();
            }
        });


        return view;
    }
    private class addStockQtyDialogBuilder extends AlertDialog.Builder {

        LayoutInflater inflater = (LayoutInflater) stockActivity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = mInflater.inflate(R.layout.activity_restock_popupwindows, null);

        private addStockQtyDialogBuilder (@NonNull final Context context, int position) {
            super(context);
            final EditText quantityView=v.findViewById(R.id.quantityView);





            setCancelable(true);
            setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, "cancel is pressed", Toast.LENGTH_SHORT).show();
                }
            });
            setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, quantityView.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            setView(v);
        }
    }
}
