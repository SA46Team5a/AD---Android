package com.example.wanglu.stationerystore.Model;

import java.util.HashMap;

//author: Wang Lu
public class ChangeCollectionPointModel extends HashMap<String, String> {
    public ChangeCollectionPointModel(String collectionPointID, String collectionPointName)
    {
        put ("collectionPointID", collectionPointID);
        put ("collectionPointName", collectionPointName);

    }

}
