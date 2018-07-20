package com.example.wanglu.stationerystore.Model;

import java.util.HashMap;

public class delegateAuthorityModel extends HashMap<String,String> {
    public delegateAuthorityModel(String authorityID, String empID, String empName, String startDate, String endDate) {
        put("authorityID", authorityID);
        put("empID", empID);
        put("empName", empName);
        put("startDate", startDate);
        put("endDate", endDate);


    }
}

