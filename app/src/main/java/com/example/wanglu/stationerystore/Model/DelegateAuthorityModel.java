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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
//Author:Luo Chao
public class DelegateAuthorityModel extends HashMap<String,String> {


    public static HashMap<String,ArrayList<String>> getEmps(String deptID) {
        ArrayList<String> empsList=new ArrayList<>();
        ArrayList<String> IDList=new ArrayList<>();
        HashMap<String,ArrayList<String>> empMap=new HashMap<>();
        JSONArray a= JSONParser.getJSONArrayFromUrl(Constant.BASE_URL+"/authority/employees/"+deptID);
        Log.i("@@@@@@@@@@@@@@@@@@@@@@@JSONARRAY",a.toString());
        try {
            for(int i=0;i<a.length();i++){
                JSONObject b=a.getJSONObject(i);
                empsList.add(b.get("EmployeeName").toString());
                IDList.add(b.get("EmployeeID").toString());
            }
            empMap.put("name",empsList);
            empMap.put("ID",IDList);
        }
        catch (Exception e){
            Log.e("CollectionPointDetails()","JSONArray error");
        }
        return empMap;
    }

    public static HashMap<String,String> getCurrentAuthority(String deptID) {

        HashMap<String,String> authorityMap=new HashMap<>();

        JSONObject a= JSONParser.getJSONFromUrl(Constant.BASE_URL+"/authority/"+deptID);
        if(a==null)
        {
            return null;
        }
        else {

            try {
                authorityMap.put("EmployeeName", a.getString("EmployeeName"));
                authorityMap.put("StartDate", a.getString("StartDate"));
                authorityMap.put("EndDate", a.getString("EndDate"));
            } catch (Exception e) {
                Log.e("getCurrentAuthority()", "JSONArray error");
            }
            return authorityMap;
        }
    }
    //unfinished method
    public static void submitNewAuthority(String empID,Date startDate,Date endDate)
    {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

        try {
            JSONObject jObj = new JSONObject();
            jObj.put("EmployeeID", empID);
            jObj.put("StartDate",df.format(startDate)+"T00:00:00");
            jObj.put("EndDate", df.format(endDate)+"T00:00:00");

            Log.i("@@@@@@@@submitNewAuthorityJSON obj", jObj.toString(4));
            String result =JSONParser.postStream(Constant.BASE_URL+"/authority/new",jObj.toString());

        }catch (Exception e){
            Log.e("submitNewAuthority()", "JSONArray error");
        }
    }

    //unfinished method
    public static void updateAuthority(String empID,Date startDate,Date endDate)
    {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

        try {
            JSONObject jObj = new JSONObject();
            jObj.put("EmployeeID", empID);
            jObj.put("StartDate",df.format(startDate)+"T00:00:00");
            jObj.put("EndDate", df.format(endDate)+"T00:00:00");

            Log.i("$$$$$$$$$$$$$$$$$$$$$$$$updateAuthorityJSON obj", jObj.toString(4));
            String result =JSONParser.postStream(Constant.BASE_URL+"/authority/update",jObj.toString());

        }catch (Exception e){
            Log.e("updateAuthority()", "JSONArray error");
        }
    }

    public static void rescind(String empId)
    {
        InputStream is = null;
        try {
            URL u = new URL(Constant.BASE_URL + "/authority/rescind/"+empId);
            Log.i("@@@@@@@@@@@@@@@@@@@@@@@@rescind",u.toString());
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

