package com.example.wanglu.stationerystore.Model;

import android.util.Log;

import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
//Author:Luo Chao
public class DelegateAuthorityModel extends HashMap<String,String> {
    static String deptID="CHEM";
    public DelegateAuthorityModel(String authorityID, String empName, String startDate, String endDate) {
        put("authorityID", authorityID);
        //put("empID", empID);
        put("empName", empName);
        put("startDate", startDate);
        if(endDate==null)
            put("endDate","");
        else
        put("endDate", endDate);
    }
    public static DelegateAuthorityModel getDefaltAuthorisedPerson() {
        //DelegateAuthorityModel model;
        JSONObject a = JSONParser.getJSONFromUrl(Constant.BASE_URL + "/authority/" + deptID);
        try {
            return new DelegateAuthorityModel(a.getString("AuthorityID"), a.getString("EmployeeName"), a.getString("StartDate"), a.getString("EndDate"));

        } catch (Exception e) {
            Log.e("getDefaltAuthorisedPerson()", "JSONArray error");
        }
        return null;
    }
    public static HashMap<String,ArrayList<String>> getEmps() {
        ArrayList<String> empsList=new ArrayList<>();
        ArrayList<String> IDList=new ArrayList<>();
        HashMap<String,ArrayList<String>> empMap=new HashMap<>();
        JSONArray a= JSONParser.getJSONArrayFromUrl(Constant.BASE_URL+"/department/employees/"+deptID);
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


}

