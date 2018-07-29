package com.example.wanglu.stationerystore.Model;

import android.util.Log;
import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Author: Luo Chao
public class StationeryRetrievalFormModel extends HashMap<String, String> {
    static String clerkID="E001";

    public static HashMap<String,ArrayList<String>> getStationeryRetrievalFormList(){
        //List<StationeryRetrievalFormModel> stationeryRetrievalFormList = new ArrayList<StationeryRetrievalFormModel>();
        HashMap<String,ArrayList<String>> retrieval=new HashMap<>();
        Integer disDutyId;
        ArrayList<String> ItemId=new ArrayList<>();
        ArrayList<String> ItemName=new ArrayList<>();
        ArrayList<String> UnitOfMeasure=new ArrayList<>();
        ArrayList<String> QtyInStock=new ArrayList<>();
        ArrayList<String> QtyToRetrieve=new ArrayList<>();


        JSONObject a = JSONParser.getJSONFromUrl(Constant.BASE_URL+"/store/retrieval/"+clerkID);
        JSONArray b;

        try{
            b=new JSONArray(a.getString("retrievalItemPayloads"));
            disDutyId=Integer.valueOf(a.getString("disDutyId"));
            for(int i=0;i<b.length();i++)
            {
                JSONObject obj=b.getJSONObject(i);
                ItemId.add(obj.getString("ItemID"));
                ItemName.add(obj.getString("ItemName"));
                UnitOfMeasure.add(obj.getString("UnitOfMeasure"));
                QtyToRetrieve.add(obj.getString("QtyToRetrieve"));
                QtyInStock.add(obj.getString("QtyInStock"));

            }
            retrieval.put("ItemID",ItemId);
            retrieval.put("ItemName",ItemName);
            retrieval.put("UnitOfMeasure",UnitOfMeasure);
            retrieval.put("QtyInStock",QtyInStock);
            retrieval.put("QtyToRetrieve",QtyToRetrieve);
      }catch(Exception e){
            Log.e("getStationeryRetrievalFormList", "JSONArray error");
        }
        return retrieval;
    }
}
