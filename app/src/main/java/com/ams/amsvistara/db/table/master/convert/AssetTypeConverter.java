package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.AssetTypeMasters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class AssetTypeConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromAssetTypeMastersList(List<AssetTypeMasters> assetTypeMasters) {
        if (assetTypeMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetTypeMasters>>() {
        }.getType();
        String json = gson.toJson(assetTypeMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<AssetTypeMasters> toAssetTypeMastersList(String assetTypeMastersString) {
        if (assetTypeMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetTypeMasters>>() {
        }.getType();
        List<AssetTypeMasters> assetTypeMasters = gson.fromJson(assetTypeMastersString, type);
        return assetTypeMasters;
    }

}