package com.example.wanglu.stationerystore.Model;

import android.util.Log;

import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
//Author:Wang Lu
public class AppointRepModel extends HashMap<String,String> {
    public AppointRepModel(String empId, String empName)
    {
        put ("employeeId", empId);
        put("employeeName",empName);
    }
    public static HashMap<String,ArrayList<String>> getEmloyee() {
        HashMap<String,ArrayList<String>> empMap = new HashMap<>();
        ArrayList<String> empIDList=new ArrayList<>();
        ArrayList<String> empNameList=new ArrayList<>();
        JSONArray a= JSONParser.getJSONArrayFromUrl(Constant.BASE_URL+"/department/employees/STOR");
        try {
            for(int i=0;i<a.length();i++){
                JSONObject b=a.getJSONObject(i);
                empIDList.add(b.get("EmployeeID").toString());
                empNameList.add(b.get("EmployeeName").toString());
            }
            empMap.put("EmployeeID",empIDList);
            empMap.put("EmployeeName",empNameList);
        }
        catch (Exception e){
            Log.e("getEmployee()","JSONArray error");
        }
        return empMap;
    }

}
