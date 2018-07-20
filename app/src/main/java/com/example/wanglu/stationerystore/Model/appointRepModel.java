package com.example.wanglu.stationerystore.Model;

import java.util.HashMap;

public class appointRepModel extends HashMap<String,String> {
    public appointRepModel(String empId,  String empName)
    {
        put ("employeeId", empId);
        put("employeeName",empName);

    }
}
