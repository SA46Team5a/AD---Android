package com.example.wanglu.stationerystore.Model;

import android.util.JsonReader;
import android.util.Log;

import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

//Author: Wang Lu
public class RestockInventoryModel extends HashMap<String,String>{
    public RestockInventoryModel (String orderId){
        put ("orderId",orderId);
    }

    public  static HashMap<String,ArrayList<String>> getOrderId(){
        HashMap<String,ArrayList<String>> orderMap = new HashMap<>();
        ArrayList<String> orderIDList = new ArrayList<>();

        JSONArray a = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL+"/orders/orderids");
                try {
                    for (int i = 0;i <a.length();i++){
                        JSONObject b = a.getJSONObject(i);
                        orderIDList.add(b.get("OrderID").toString());
                    }
                    orderMap.put("OrderID",orderIDList);
                }
                catch (Exception e){
                    Log.e ("getOrderId()","JSONArray error");
                }
                return orderMap;
    }
    public  static HashMap<String,ArrayList<String>> getSupplier(){
        HashMap<String,ArrayList<String>> supplierMap = new HashMap<>();
        ArrayList<String> supplierIDList = new ArrayList<>();
        ArrayList<String> supplierNameList =new ArrayList<>();

        JSONArray a = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL+"/orders/suppliers");
        try {
            for (int i = 0;i <a.length();i++){
                JSONObject b = a.getJSONObject(i);
                supplierIDList.add(b.get("SupplierID").toString());
                supplierNameList.add(b.get("SupplierName").toString());
            }
            supplierMap.put("SupplierID",supplierIDList);
            supplierMap.put("SupplierName",supplierNameList);
        }
        catch (Exception e){
            Log.e ("getSupplier()","JSONArray error");
        }
        return supplierMap ;
    }


}
