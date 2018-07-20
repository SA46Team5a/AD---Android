package com.example.wanglu.stationerystore.Model;

import java.util.HashMap;

//author: Wang Lu
public class changeCollectionPointModel extends HashMap<String, String> {
    public changeCollectionPointModel(String collectionPointID, String collectionPointName)
    {
        put ("collectionPointID", collectionPointID);
        put ("collectionPointName", collectionPointName);

    }

}
