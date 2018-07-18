package com.example.wanglu.stationerystore.Model;

import java.util.HashMap;

//author: Wang Lu
public class changeCollectionPoint extends HashMap<String, String> {
    public changeCollectionPoint(String collectionPointID, String collectionPointName)
    {
        put ("collectionPointID", collectionPointID);
        put ("collectionPointName", collectionPointName);

    }

}
