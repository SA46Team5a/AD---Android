package com.example.wanglu.stationerystore.Model;

import android.util.Log;

import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
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

   // static  String deprepid = "chem";
    public static HashMap<String, String> getDeprepID(String departmentId)  {
        HashMap<String, String> deprepIdMap = new HashMap<>();
        String DeptRepID;
        String EmployeeName;
        JSONObject A = JSONParser.getJSONFromUrl(Constant.BASE_URL+"/deprep/"+ departmentId );
        try {
            DeptRepID  =A.get("DeptRepID").toString();
            EmployeeName =A.get("EmployeeName").toString();
            deprepIdMap.put("DeptRepID",DeptRepID);
            deprepIdMap.put("EmployeeName",EmployeeName);
        }
        catch (Exception e){
            Log.e("getDeprepID()","JSONArray error");
        }
        return deprepIdMap;
    }



}
