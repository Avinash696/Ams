package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.AssetTagTypeMasters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class AssetTagTypeConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromAssetTagTypeMastersList(List<AssetTagTypeMasters> assetTagTypeMasters) {
        if (assetTagTypeMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetTagTypeMasters>>() {
        }.getType();
        String json = gson.toJson(assetTagTypeMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<AssetTagTypeMasters> toAssetTagTypeMastersList(String assetTagTypeMastersString) {
        if (assetTagTypeMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetTagTypeMasters>>() {
        }.getType();
        List<AssetTagTypeMasters> assetTagTypeMasters = gson.fromJson(assetTagTypeMastersString, type);
        return assetTagTypeMasters;
    }

}