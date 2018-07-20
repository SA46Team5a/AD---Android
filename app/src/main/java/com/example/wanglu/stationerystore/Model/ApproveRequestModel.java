package com.example.wanglu.stationerystore.Model;

import java.util.HashMap;

public class ApproveRequestModel extends HashMap<String,String>{
    public ApproveRequestModel(String reqId, String reqDate, String empName)
    {
        put ("requisitionId", reqId);
        put ("requisitionDate", reqDate);
        put("employeeName",empName);

    }
}
