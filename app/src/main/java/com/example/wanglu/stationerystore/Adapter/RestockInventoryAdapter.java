package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Model.RestockInventoryModel;
import com.example.wanglu.stationerystore.Orders.restockInventory.RestockInventoryActivity;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
//Author:Luo Chao
public class RestockInventoryAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private RestockInventoryActivity stockActivity;
    private HashMap<String,ArrayList<String>> restock;
    SharedPreferences pref;

    ViewHolder holder;

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
        this.restock = stockActivity.ItemListMap;
        this.pref=stockActivity.pref;
    }

    @Override
    public int getCount() {
        return restock.get("ItemID").size();
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

        holder.categoryView.setText((String ) restock.get("ItemID").get(position));
        holder.descriptionView.setText((String )restock.get("ItemName").get(position));
        holder.stockView.setText((String ) restock.get("OrderedQty").get(position)+" "+ restock.get("UnitOfMeasure").get(position));
        holder.AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            addStockQtyDialogBuilder alertBuilder=new addStockQtyDialogBuilder(stockActivity,position,view);
                alertBuilder.show();
            }
        });


        return view;
    }
    private class addStockQtyDialogBuilder extends AlertDialog.Builder {

        LayoutInflater inflater = (LayoutInflater) stockActivity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = mInflater.inflate(R.layout.activity_restock_popupwindows, null);

        private addStockQtyDialogBuilder (@NonNull final Context context, final int position, final View addBtn) {
            super(context);
            final EditText quantityView = v.findViewById(R.id.quantityView);
            quantityView.setText(restock.get("OrderedQty").get(position));
            quantityView.setHint("e.g. 5");
            quantityView.setFilters(new InputFilter[]{ new InputFilterMinMax("0", restock.get("OrderedQty").get(position))});

            setCancelable(true);
            setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   // Toast.makeText(context, "cancel is pr", Toast.LENGTH_SHORT).show();
                }
            });
            setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (quantityView.getText().toString().isEmpty())
                    {

                        Toast.makeText(context, "You didn't add quantity.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //holder.AddBtn.setEnabled(true);
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... params) {
                                RestockInventoryModel.addStockQty(
                                        pref.getString("empID","Empty"),
                                        restock.get("OrderSupplierDetailId").get(position),
                                        quantityView.getText().toString());
                                return null;
                            }
                            @Override
                            protected void onPostExecute(Void result) {
                            }

                        }.execute();

                        Toast.makeText(context, "Added successful!", Toast.LENGTH_SHORT).show();
                        addBtn.setEnabled(false);
                        addBtn.setBackgroundResource(R.drawable.disable_button);
                    }

                }
            });
            setView(v);
        }
    }

    public class InputFilterMinMax implements InputFilter {

        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
