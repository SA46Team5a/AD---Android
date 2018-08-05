package com.example.wanglu.stationerystore.Model;


import android.util.Log;

import com.example.wanglu.stationerystore.Model.ConstantAndMethod.Constant;
import com.example.wanglu.stationerystore.Model.JSON.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Author: Benedict, Jack
public class DiscrepancyItemsModel extends HashMap<String, Object> {
    private static String itemId = "ItemID";
    private static String itemName = "ItemName";
    private static String unitOfMeasure = "UnitOfMeasure";
    private static String unitCost = "UnitCost";
    private static String originalQty = "QtyInStock";
    private static String discrepancyId = "DiscrepancyID";
    private static String actualQty = "ActualCount";
    private static String voucherRaiserId = "VoucherRaiserID";
    private static String voucherApproverId = "VoucherApproverID";
    private static String reason = "Reason";

    public String getItemId() { return (String) get(itemId);}
    private void setItemId(String id) { put(itemId, id);}

    public String getItemName() { return (String) get(itemName);}
    private void setItemName(String name) { put(itemName, name);}

    public String getUnitOfMeasure() { return (String) get(unitOfMeasure);}
    private void setUnitOfMeasure(String uom) { put(unitOfMeasure, uom);}

    public double getUnitCost() { return (double) get(unitCost); }
    private void setUnitCost(double cost) { put(unitCost, cost); }

    public int getOriginalQty() { return (int) get(originalQty);}
    private void setOriginalQty(int qty) { put(originalQty, qty);}

    public int getActualQty() { return (int) get(actualQty);}
    public void setActualQty(int qty) { put(actualQty, qty);}

    public int getDiscrepancyId() { return (int) get(discrepancyId);}
    private void setDiscrepancyId(int id) { put(discrepancyId, id);}

    public String getVoucherRaiserId() { return (String) get(voucherRaiserId);}
    public void setVoucherRaiserId(String raiserId) { put(voucherRaiserId, raiserId);}

    public String getVoucherApproverId() { return (String) get(voucherApproverId);}
    private void setVoucherApproverId(String approverId) { put(voucherApproverId, approverId);}

    public String getReason() { return (String) get(reason);}
    public void setReason(String r) { put(reason, r);}

    public String getQtyAndUom() { return getOriginalQty() + " " + getUnitOfMeasure(); }

    public int getAdjustment() { return getActualQty() - getOriginalQty(); }

    public String getTotalAmount() {
        return String.format("$%.2f", getAdjustment() * getUnitCost());
    }

    public DiscrepancyItemsModel() {}

    public DiscrepancyItemsModel(JSONObject o) throws JSONException{
        setItemId(o.getString(itemId));
        setItemName(o.getString(itemName));
        setUnitOfMeasure(o.getString(unitOfMeasure));
        setUnitCost(o.getDouble(unitCost));
        setOriginalQty(o.getInt(originalQty));
        setActualQty(o.getInt(actualQty));
        setDiscrepancyId(o.getInt(discrepancyId));
        setVoucherRaiserId(o.getString(voucherRaiserId));
        setReason(o.getString(reason));
    }

    public static DiscrepancyItemsModel fromItemPayload(JSONObject o) throws JSONException {
        DiscrepancyItemsModel d = new DiscrepancyItemsModel();
        d.setItemId(o.getString(itemId));
        d.setItemName(o.getString(itemName));
        d.setUnitOfMeasure(o.getString(unitOfMeasure));
        d.setUnitCost(o.getDouble(unitCost));
        d.setOriginalQty(o.getInt(originalQty));
        d.setActualQty(o.getInt(originalQty));
        return d;
    }

    public JSONObject toJSONObject() throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(itemId, getItemId());
        jsonObject.put(itemName, getItemName());
        jsonObject.put(unitOfMeasure, getUnitOfMeasure());
        jsonObject.put(unitCost, getUnitCost());
        jsonObject.put(originalQty, getOriginalQty());
        jsonObject.put(actualQty, getActualQty());
        try {
            jsonObject.put(discrepancyId, getDiscrepancyId());
        } catch (Exception e){}
        jsonObject.put(voucherRaiserId, getVoucherRaiserId());
        jsonObject.put(voucherApproverId, getVoucherApproverId());
        jsonObject.put(reason, getReason());
        return jsonObject;
    }

    public static List<DiscrepancyItemsModel> getStockCountsOfCategory(String catId) {
        List<DiscrepancyItemsModel> details = new ArrayList<DiscrepancyItemsModel>();

        JSONArray array = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL + "/store/stockcount/" + catId);
        DiscrepancyItemsModel detail;
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                detail = fromItemPayload(jsonObject);
                details.add(detail);
            }
        } catch (Exception e) {
            Log.e("getStockCounts()", "JSONArray error");
        }
        return details ;
    }

    public static boolean submitStockCountResults(List<DiscrepancyItemsModel> data) {
        try {
            JSONArray array = new JSONArray();
            for (DiscrepancyItemsModel datum : data){
                array.put(datum.toJSONObject());
            }

            String empId = "E001"; // TODO: get from SharedPreferences
            String url = Constant.BASE_URL + "/store/stockcount/submit/" + empId;
            boolean result = JSONParser.postStream(url, array.toString()).equals("true\n");
            return result;
        } catch (JSONException e) {
            Log.e("submitCountResults()", "JSONArray error");
            return false;
        }
    }

    public static List<DiscrepancyItemsModel> getStockVouchers() {
        List<DiscrepancyItemsModel> details = new ArrayList<DiscrepancyItemsModel>();
        try {
            boolean isManager = true; // TODO: get from SharedPreferences
            JSONArray array = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL + "/store/vouchers/retrieve/" + isManager);
            DiscrepancyItemsModel detail;
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                detail = new DiscrepancyItemsModel(jsonObject);
                details.add(detail);
            }
        } catch (JSONException e) {
            Log.e("getStockVouchers()", "JSONArray error");
        }
        return details;
    }

    public static boolean submitStockVouchers(List<DiscrepancyItemsModel> data, String empId) {
        try {
            JSONArray array = new JSONArray();
            for (DiscrepancyItemsModel datum : data){
                array.put(datum.toJSONObject());
            }

            String url = Constant.BASE_URL + "/store/vouchers/submit/" + empId;
            String result = JSONParser.postStream(url, array.toString());
            return result.equals("true\n");
        } catch (JSONException e) {
            Log.e("submitStockVouchers()", "JSONArray error");
            return false;
        }
    }
}
