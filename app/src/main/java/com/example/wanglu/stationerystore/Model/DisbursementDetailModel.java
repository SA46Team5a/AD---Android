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

public class DisbursementDetailModel extends HashMap<String, Object> {

    private static String itemId = "ItemId";
    public String getItemId() { return (String) get(itemId); }
    private void setItemId(String id) { put(itemId, id); }

    private static String itemName = "ItemName";
    public String getItemName() { return (String) get(itemName); }
    private void setItemName(String name) { put(itemName, name); }

    private static String unitOfMeasure= "UnitOfMeasure";
    public String getUnitOfMeasure() { return (String) get(unitOfMeasure); }
    private void setUnitOfMeasure(String uom) { put(unitOfMeasure, uom); }

    public String getQtyAndUom() { return getDisbursedQuantity() + " " + getUnitOfMeasure(); }

    private static String reason = "Reason";
    public String getReason() { return (String) get(reason); }
    public void setReason(String reason) { put(this.reason, reason); }

    private static String disDutyId = "DisbursementDutyIds";
    public List<Integer> getDisbursementDutyId() { return (List<Integer>) get(disDutyId); }
    private void setDisbursementDutyId(List<Integer> ids) { put(disDutyId, ids); }

    private static String disbursedQty = "DisbursedQuantity";
    public int getDisbursedQuantity() { return (int) get(disbursedQty); }
    private void setDisbursedQuantity(int qty) { put(disbursedQty, qty); }

    private static String collectedQty = "CollectedQuantity";
    public int getCollectedQuantity() { return (int) get(collectedQty); }
    public void setCollectedQuantity(int qty) { put(collectedQty, qty); }

    public JSONObject toJSONObject() throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(itemId, getItemId());
        jsonObject.put(itemName, getItemName());
        jsonObject.put(unitOfMeasure, getUnitOfMeasure());
        jsonObject.put(reason, getReason());
        jsonObject.put(disDutyId, getDisbursementDutyId());
        jsonObject.put(collectedQty, getCollectedQuantity());
        jsonObject.put(disbursedQty, getDisbursedQuantity());
        return jsonObject;
    }

    private DisbursementDetailModel(JSONObject jsonObject) throws JSONException{
        setItemId(jsonObject.getString(itemId));
        setUnitOfMeasure(jsonObject.getString(unitOfMeasure));
        setDisbursedQuantity(jsonObject.getInt(disbursedQty));
        setCollectedQuantity(getDisbursedQuantity());
    }

    public static List<DisbursementDetailModel> getDisbursementDetailsOfDepartment(String depId) {
        List<DisbursementDetailModel> details = new ArrayList<DisbursementDetailModel>();
        JSONArray array = JSONParser.getJSONArrayFromUrl(Constant.BASE_URL + "/store/disbursement/" + depId);
        DisbursementDetailModel detail;
        if (array == null)
            return null;
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                detail = new DisbursementDetailModel(jsonObject);

                JSONArray jsonDutyIds = jsonObject.getJSONArray(disDutyId);
                List<Integer> disDutyIds = new ArrayList<Integer>();
                for (int j = 0; j < jsonDutyIds.length(); j++) {
                    disDutyIds.add(jsonDutyIds.getInt(j));
                }
                detail.setDisbursementDutyId(disDutyIds);
            }
        } catch (Exception e) {
            Log.e("getDisbursementDetailsOfDepartment()", "JSONArray error");
        }
        return null;
    }

    public static boolean submitDisbursementDetails(List<DisbursementDetailModel> data, String depId, String empId, String passcode) {
        try {
            JSONArray array = new JSONArray();
            for (DisbursementDetailModel datum : data){
                array.put(datum.toJSONObject());
            }

            StringBuilder url = new StringBuilder();
            url.append(Constant.BASE_URL + "/store/disbursement/");
            url.append(depId + "/");
            url.append(empId + "/");
            url.append(passcode);
            return JSONParser.postStream(url.toString(), array.toString()).equals("true");
        } catch (JSONException e) {
            return false;
        }
   }
}
