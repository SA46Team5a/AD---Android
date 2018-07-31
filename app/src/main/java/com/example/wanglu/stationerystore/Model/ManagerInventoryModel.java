package com.example.wanglu.stationerystore.Model;

import android.util.Log;

import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerInventoryModel extends HashMap<String,String> {
    public ManagerInventoryModel(String categoryId, String categoryName){
        put("categoryId",categoryId);
        put("categoryName",categoryName);
    }
    public static HashMap<String,ArrayList<String>> getCategories() {
        HashMap<String,ArrayList<String>> categoryMap = new HashMap<>();
        ArrayList<String> categoryIDList=new ArrayList<>();
        ArrayList<String> categoryNameList=new ArrayList<>();
        JSONArray a= JSONParser.getJSONArrayFromUrl(Constant.BASE_URL+"/classification/categories");
        try {
            for(int i=0;i<a.length();i++){
                JSONObject b=a.getJSONObject(i);
                categoryIDList.add(b.get("CategoryID").toString());
                categoryNameList.add(b.get("CategoryName").toString());
            }
            categoryMap.put("CategoryID",categoryIDList);
            categoryMap.put("CategoryName",categoryNameList);
        }
        catch (Exception e){
            Log.e("getCategories()","JSONArray error");
        }
        return categoryMap;
    }
}
