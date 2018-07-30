package com.example.wanglu.stationerystore.Model;
import android.provider.SyncStateContract;
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
//author: Luo Chao
public class ChangeCollectionPointModel extends HashMap<String, String> {
     static String deptID="STOR";
   public ChangeCollectionPointModel(String collectionPointID, String CollectionPointDetails)
    {
        put ("CollectionPointID", collectionPointID);
        put ("CollectionPointDetails", CollectionPointDetails);
    }
    public static HashMap<String,ArrayList<String>> getCollectPntList() {
        ArrayList<String> detailList=new ArrayList<>();
        ArrayList<String> IDList=new ArrayList<>();
        HashMap<String,ArrayList<String>> collectPnt=new HashMap<>();
        JSONArray a= JSONParser.getJSONArrayFromUrl(Constant.BASE_URL+"/classification/collectionpoints");
        try {
            for(int i=0;i<a.length();i++){
                JSONObject b=a.getJSONObject(i);
                detailList.add(b.get("CollectionPointDetails").toString());
                IDList.add(b.get("CollectionPointID").toString());
                }
                collectPnt.put("detail",detailList);
                collectPnt.put("ID",IDList);
        }
        catch (Exception e){
            Log.e("CollectionPointDetails()","JSONArray error");
        }
        return collectPnt;
    }

    public static String getCollectionPointOfDept(String deptID){
        JSONObject obj = JSONParser.getJSONFromUrl(Constant.BASE_URL + "/deprep/collectionpoint/" + deptID);
        String collectionPointDetial=null;
        try {
            collectionPointDetial= obj.getString("CollectionPointDetails");
        } catch (Exception e){
            Log.e("getCollectionPointOfDept()","JSONArray error");
        }
        return collectionPointDetial;
    }

    public static String getPasscode(String deptID){
        JSONObject a= JSONParser.getJSONFromUrl(Constant.BASE_URL+"/deprep/passcode/"+deptID);
        String passcode=null;
        try {
            passcode=a.getString("passcode").toString();
        }
        catch (Exception e){
            Log.e("getPasscode()","JSONArray error");
        }
        return passcode;
    }

    public static void updateCollectionPoint(String depId,String collectionPointId) {
        InputStream is = null;
        try {
            URL u = new URL(Constant.BASE_URL + "/deprep/collectionpoint/"+depId+"/"+collectionPointId);
            Log.i("@@@@@@@@@@@@@@@@URL:",u.toString());
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
