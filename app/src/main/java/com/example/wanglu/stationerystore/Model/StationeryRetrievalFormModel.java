package com.example.wanglu.stationerystore.Model;

import android.util.Log;
import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


//Author: Luo Chao
public class StationeryRetrievalFormModel extends HashMap<String, String> {
    static String clerkID="E015";

    public static HashMap<String,ArrayList<String>> getStationeryRetrievalFormList(){
        //List<StationeryRetrievalFormModel> stationeryRetrievalFormList = new ArrayList<StationeryRetrievalFormModel>();
        HashMap<String,ArrayList<String>> retrieval=new HashMap<>();
        ArrayList<String> ItemId=new ArrayList<>();
        ArrayList<String> ItemName=new ArrayList<>();
        ArrayList<String> UnitOfMeasure=new ArrayList<>();
        ArrayList<String> QtyInStock=new ArrayList<>();
        ArrayList<String> QtyToRetrieve=new ArrayList<>();
        ArrayList<String> disDutyId=new ArrayList<>();


        JSONObject a = JSONParser.getJSONFromUrl(Constant.BASE_URL+"/store/retrieval/"+clerkID);
        JSONArray b;

        try{
            b=new JSONArray(a.getString("retrievalItemPayloads"));
            disDutyId.add(a.getString("disDutyId"));
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
            retrieval.put("disDutyId",disDutyId);
      }catch(Exception e){
            Log.e("getStationeryRetrievalFormList()", "JSONArray error");
        }
        return retrieval;
    }
    public static void updateStationeryRetrievalform(ArrayList<String> itemID,ArrayList<String> Quantity,ArrayList<String> disDutyId)
    {

        JSONArray jArr=new JSONArray();
        try {
            for (int i = 0; i < itemID.size(); i++) {
                JSONObject jObj = new JSONObject();
                jObj.put("ItemId", itemID.get(i));
                jObj.put("Quantity",Integer.valueOf(Quantity.get(i)));
                jArr.put(jObj);
                Log.i("JSON obj", jArr.toString(4));
            }
                Log.i("@@@@@@@@@@@@@@22", jArr.toString()+"  "+disDutyId.get(0));
                String result =JSONParser.postStream(Constant.BASE_URL+"/store/retrieval/"+disDutyId.get(0),jArr.toString());

        }catch (Exception e){
            Log.e("updateStationeryRetrievalForm()", "JSONArray error");
        }
    }
}
