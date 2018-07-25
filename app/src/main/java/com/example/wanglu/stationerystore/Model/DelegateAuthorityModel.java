package com.example.wanglu.stationerystore.Model;

import java.util.HashMap;
//Author:Luo Chao
public class DelegateAuthorityModel extends HashMap<String,String> {
    public DelegateAuthorityModel(String authorityID, String empID, String empName, String startDate, String endDate) {
        put("authorityID", authorityID);
        put("empID", empID);
        put("empName", empName);
        put("startDate", startDate);
        put("endDate", endDate);


    }
}

