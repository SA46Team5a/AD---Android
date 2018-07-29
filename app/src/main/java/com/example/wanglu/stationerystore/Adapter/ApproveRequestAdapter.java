package com.example.wanglu.stationerystore.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanglu.stationerystore.DepRequisition.ApproveRequisitionForm.ApproveRequestFormActivity;
import com.example.wanglu.stationerystore.Model.ApproveRequestModel;
import com.example.wanglu.stationerystore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.security.AccessController.getContext;
//Author: Wang Lu
public class ApproveRequestAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ApproveRequestFormActivity activity;
    private HashMap<String,ArrayList<String>> approve;

    public ApproveRequestAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.activity = (ApproveRequestFormActivity) context;
        approve = activity.approvaMap;
    }

    @Override
    public int getCount() {
        return approve.get("RequisitionID").size();
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

        ApproveRequestFormActivity.ViewHolder holder;
// initialize controls
        if (view == null) {
            holder = new ApproveRequestFormActivity.ViewHolder();
            view = mInflater.inflate(R.layout.content_approve_form, null);
            holder.date = (TextView) view.findViewById(R.id.dateLabel);
            holder.empName = (TextView) view.findViewById(R.id.employeenameLabel);
            holder.approve = (Button) view.findViewById(R.id.approveButton);
            holder.reject = (Button) view.findViewById(R.id.rejectButton);
            holder.listitems = (LinearLayout) view.findViewById(R.id.LinearLayoutforlist);
            view.setTag(holder);

        }
        else {
            holder = (ApproveRequestFormActivity.ViewHolder) view.getTag();
        }

        //ButtonClick event
        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        //"put into below method when jack add empId for endpoint method" approve.get("empID").get(position)
                        ApproveRequestModel.approveRequest("E026",approve.get("RequisitionID").get(position));
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result) {

                    }

                }.execute();
                //Toast.makeText(,"approved successfully",Toast.LENGTH_SHORT)
            }
        });

// set texts
        holder.date.setText((String) approve.get("RequestDate").get(0));
        holder.empName.setText((String) approve.get("RequesterName").get(0));
//set lists texts
        holder.listitems.removeAllViews();
        for(int i=0;i<approve.get("RequisitionDetailID").size();i++)
        {
            View v = mInflater.inflate(R.layout.content_itemlist, null);
            TextView descriptionView = v.findViewById(R.id.descriptionView);
            TextView quantityView = v.findViewById(R.id.quantityView);

            descriptionView.setText((String) approve.get("ItemName").get(i));
            quantityView.setText((String) approve.get("Quantity").get(i)+" "+ approve.get("UnitOfMeasure").get(i));
            holder.listitems.addView(v);
        }

        return view;
    }

}
