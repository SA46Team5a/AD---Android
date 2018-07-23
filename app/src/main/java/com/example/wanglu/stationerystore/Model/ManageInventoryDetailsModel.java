package com.example.wanglu.stationerystore.Model;

import android.util.Log;

import com.example.wanglu.stationerystore.Model.JSON.JSONParser;
import com.example.wanglu.stationerystore.StockAdjustment.ManageMonthlyStockDiscrepency.ManageInventoryDetailsActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Author: Benedict
public class ManageInventoryDetailsModel extends HashMap<String, String> {
    public ManageInventoryDetailsModel(String itemCode, String description,
                                       String unitOfMeasure, String stockBalance, String actualStock, String reason){
        put("itemCode", itemCode);
        put("description", description);
        put("unitOfMeasure", unitOfMeasure);
        put("stockBalance", stockBalance);
        put("actualStock", actualStock);
        put("reason", reason);

    }

    public static List<ManageInventoryDetailsModel> getInventoryItemList(){
        List<ManageInventoryDetailsModel> inventoryItemList = new ArrayList<ManageInventoryDetailsModel>();
        JSONArray a = JSONParser.getJSONArrayFromUrl("url");
        try{
            for(int i=0; i<a.length(); i++){
                JSONObject b = a.getJSONObject(i);
                inventoryItemList.add(new ManageInventoryDetailsModel
                        (b.getString("itemCode"), b.getString("description"),
                                b.getString("unitOfMeasure"), b.getString("stockBalance"),
                                "500", "none")
                );
            }
        }catch(Exception e){
            Log.e("ManageInventoryDetailsModel", "JSONArray error");
        }
        return inventoryItemList;
    }
}
