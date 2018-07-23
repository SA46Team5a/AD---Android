package com.example.wanglu.stationerystore.Model;


import java.util.HashMap;

//Author: Benedict
public class DiscrepancyItemsModel extends HashMap<String, String> {
    public DiscrepancyItemsModel(String itemCode, String description, String quantityAdjustment,
                                 String unitOfMeasure, String amount, String reason){
        put("itemCode", itemCode);
        put("description", description);
        put("quantityAdjustment", quantityAdjustment);
        put("unitOfMeasure", unitOfMeasure);
        put("amount", amount);
        put("reason", reason);
    }

}
