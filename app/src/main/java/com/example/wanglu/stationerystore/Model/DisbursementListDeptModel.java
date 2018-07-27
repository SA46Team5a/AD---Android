package com.example.wanglu.stationerystore.Model;

import android.util.Log;

import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

//Author: Wang Lu
public class DisbursementListDeptModel extends HashMap<String , String> {

    public static HashMap<String, ArrayList<String>> getDepartment() {
        HashMap<String, ArrayList<String>> deptMap = new HashMap<>();
        ArrayList<String> deptIDList = new ArrayList<>();
        ArrayList<String> deptNameList = new ArrayList<>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL + "/store/disbursement/departments");
        try {
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                deptIDList.add(b.get("DepartmentID").toString());
                deptNameList.add(b.get("DepartmentName").toString());
            }
            deptMap.put("DepartmentID", deptIDList);
            deptMap.put("DepartmentName", deptNameList);
        } catch (Exception e) {
            Log.e("getDepartment()", "JSONArray error");
        }
        return deptMap;
    }
}

