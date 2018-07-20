package com.example.wanglu.stationerystore.Model;

import java.util.HashMap;

public class approveRequestModel extends HashMap<String,String>{
    public approveRequestModel(String reqId, String reqDate, String empName)
    {
        put ("requisitionId", reqId);
        put ("requisitionDate", reqDate);
        put("employeeName",empName);

    }
}
