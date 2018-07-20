package com.example.wanglu.stationerystore.Model;

import java.util.HashMap;

public class AppointRepModel extends HashMap<String,String> {
    public AppointRepModel(String empId, String empName)
    {
        put ("employeeId", empId);
        put("employeeName",empName);

    }
}
