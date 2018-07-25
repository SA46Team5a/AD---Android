package com.example.wanglu.stationerystore.Model;
import android.provider.SyncStateContract;
import android.util.Log;
import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
//author: Luo Chao
public class ChangeCollectionPointModel extends HashMap<String, String> {
   public ChangeCollectionPointModel(String collectionPointID, String CollectionPointDetails)
    {
        put ("CollectionPointID", collectionPointID);
        put ("CollectionPointDetails", CollectionPointDetails);
    }
    public static ArrayList<String> getCollectPntList() {
        ArrayList<String> detailList=new ArrayList<>();
        JSONArray a= JSONParser.getJSONArrayFromUrl(Constant.BASE_URL+"/classification/collectionpoints");
        try {
            for(int i=0;i<a.length();i++){
                JSONObject b=a.getJSONObject(i);
                detailList.add(b.get("CollectionPointDetails").toString());
                }
        }
        catch (Exception e){
            Log.e("CollectionPointDetails()","JSONArray error");
        }
        return detailList;
    }



}
