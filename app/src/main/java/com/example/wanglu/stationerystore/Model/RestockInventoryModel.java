package com.example.wanglu.stationerystore.Model;

import android.util.JsonReader;
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

//Author: Wang Lu
public class RestockInventoryModel extends HashMap<String,String> {

    static String orderId = "1";
    static String supplierId = "ALPA";

    public static HashMap<String, ArrayList<String>> getOrderId() {
        HashMap<String, ArrayList<String>> orderMap = new HashMap<>();
        ArrayList<String> orderIDList = new ArrayList<>();

        JSONArray a = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL + "/orders/orderids");
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                orderIDList.add(b.get("OrderID").toString());
            }
            orderMap.put("OrderID", orderIDList);
        } catch (Exception e) {
            Log.e("getOrderId()", "JSONArray error");
        }
        return orderMap;
    }

    public static HashMap<String, ArrayList<String>> getSupplier() {
        HashMap<String, ArrayList<String>> supplierMap = new HashMap<>();
        ArrayList<String> supplierIDList = new ArrayList<>();
        ArrayList<String> supplierNameList = new ArrayList<>();

        JSONArray a = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL + "/orders/suppliers");
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                supplierIDList.add(b.get("SupplierID").toString());
                supplierNameList.add(b.get("SupplierName").toString());
            }
            supplierMap.put("SupplierID", supplierIDList);
            supplierMap.put("SupplierName", supplierNameList);
        } catch (Exception e) {
            Log.e("getSupplier()", "JSONArray error");
        }
        return supplierMap;
    }

    public static HashMap<String, ArrayList<String>> getInventoryDetail(String orderId, String supplierId) {

        HashMap<String, ArrayList<String>> restock = new HashMap<>();

        ArrayList<String> CategoryName = new ArrayList<>();
        ArrayList<String> SupplierID = new ArrayList<>();
        ArrayList<String> ItemID = new ArrayList<>();
        ArrayList<String> ItemName = new ArrayList<>();
        ArrayList<String> UnitOfMeasure = new ArrayList<>();
        ArrayList<String> QtyInStock = new ArrayList<>();


        JSONArray a = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL + "/orders/orderdetails/" + orderId + "/" + supplierId);

        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject obj = a.getJSONObject(i);
                ItemID.add(obj.getString("ItemID"));
                ItemName.add(obj.getString("ItemName"));
                UnitOfMeasure.add(obj.getString("UnitOfMeasure"));
                QtyInStock.add(obj.getString("QtyInStock"));
                CategoryName.add(obj.getString("CategoryName"));
                SupplierID.add(obj.getString("OrderSupplierDetailId"));

            }
            restock.put("ItemID", ItemID);
            restock.put("ItemName", ItemName);
            restock.put("UnitOfMeasure", UnitOfMeasure);
            restock.put("QtyInStock", QtyInStock);
            restock.put("CategoryName", CategoryName);
            restock.put("OrderSupplierDetailId", SupplierID);

        } catch (Exception e) {
            Log.e("getInventoryDetail()", "JSONArray error");
        }
        return restock;
    }

    public static void addStockQty(String empID, String orderSupplierDtailID, String qty) {
            InputStream is = null;
            try {
                URL u = new URL(Constant.BASE_URL + "/orders/addstock/" + empID + "/" + orderSupplierDtailID + "/" + qty);
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
