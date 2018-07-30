package com.example.wanglu.stationerystore.Model;

import android.util.Log;

import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
//Author:Luo Chao
public class ApproveRequestModel extends HashMap<String,String> {



    public static HashMap<String, ArrayList<String>> getApproveform(String deptID) {

        HashMap<String, ArrayList<String>> approve = new HashMap<>();

        ArrayList<String> RequisitionID = new ArrayList<>();
        ArrayList<String> RequesterName = new ArrayList<>();
        ArrayList<String> RequestDate = new ArrayList<>();

        ArrayList<String> RequisitionDetailID = new ArrayList<>();
        ArrayList<String> ItemName = new ArrayList<>();
        ArrayList<String> UnitOfMeasure = new ArrayList<>();
        ArrayList<String> Quantity = new ArrayList<>();

        JSONArray a = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL + "/requisitions/pending/" +deptID);

        try {
            for(int i=0;i<a.length();i++){
                JSONObject obj=a.getJSONObject(i);
                RequisitionID.add(obj.get("RequisitionID").toString());
                RequesterName.add(obj.get("RequesterName").toString());
                RequestDate.add(obj.get("RequestDate").toString().split("T")[0]);

                JSONArray detail =new JSONArray(obj.getString("RequisitionDetails"));
                for (int j = 0; j <detail.length();j++){
                    JSONObject object = detail.getJSONObject(j);
                    RequisitionDetailID.add(object.get("RequisitionDetailID").toString());
                    ItemName.add(object.get("ItemName").toString());
                    UnitOfMeasure.add(object.get("UnitOfMeasure").toString());
                    Quantity.add(object.get("Quantity").toString());
                }
            }
            approve.put("RequisitionID", RequisitionID);
            approve.put("RequesterName", RequesterName);
            approve.put("RequestDate", RequestDate);
            approve.put("RequisitionDetailID", RequisitionDetailID);
            approve.put("ItemName", ItemName);
            approve.put("UnitOfMeasure",UnitOfMeasure);
            approve.put("Quantity",Quantity);

        } catch (Exception e) {
            Log.e("getApproveform()", "JSONArray error");
        }
        return approve;
    }

    public static void approveRequest(String empId, String reqId) {

        InputStream is = null;
        try {
            URL u = new URL(Constant.BASE_URL + "/requisition/approve/"+empId+"/"+reqId);
            Log.i("@@@@@@@@@@@@@@@@@@@@@@@@approveRequest",u.toString());
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            is = conn.getInputStream();
        } catch (UnsupportedEncodingException e) {
            Log.e("getStream Exception",  e.toString());
        } catch (Exception e) {
            Log.e("getStream Exception",  e.toString());
        }

    }

    public static void rejectRequest(String empId, String reqId) {

        InputStream is = null;
        try {
            URL u = new URL(Constant.BASE_URL + "/requisition/reject/"+empId+"/"+reqId);
            Log.i("@@@@@@@@@@@@@@@@@@@@@@@@rejectRequest",u.toString());
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            is = conn.getInputStream();
        } catch (UnsupportedEncodingException e) {
            Log.e("getStream Exception",  e.toString());
        } catch (Exception e) {
            Log.e("getStream Exception",  e.toString());
        }

    }


}


