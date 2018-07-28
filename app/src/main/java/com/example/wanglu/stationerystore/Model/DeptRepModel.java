package com.example.wanglu.stationerystore.Model;

import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DeptRepModel extends HashMap<String, Object> {
    private static String deptRepId = "DeptRepID";
    private static String empName = "EmployeeName";

    public int getDeptRepInt() { return (int) get(deptRepId); }
    private void setDeptRepId(int id) { put(deptRepId, id); }

    public String getEmpName() { return (String) get(empName); }
    private void setEmpName(String name) { put(empName, name); }

    public DeptRepModel(JSONObject obj) throws JSONException {
        setDeptRepId(obj.getInt(deptRepId));
        setEmpName(obj.getString(empName));
    }

    public static DeptRepModel getDeptRepOfDept(String depId) {
        JSONObject obj = JSONParser.getJSONFromUrl(Constant.BASE_URL + "/deprep/" + depId);
        try {
            return new DeptRepModel(obj);
        } catch (JSONException e){
            return null;
        }
    }
}
