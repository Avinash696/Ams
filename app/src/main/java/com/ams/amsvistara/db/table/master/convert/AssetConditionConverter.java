package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.AssetConditionMasters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class AssetConditionConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromAssetConditionMastersList(List<AssetConditionMasters> assetConditionMasters) {
        if (assetConditionMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetConditionMasters>>() {
        }.getType();
        String json = gson.toJson(assetConditionMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<AssetConditionMasters> toAssetConditionMastersList(String assetConditionMastersString) {
        if (assetConditionMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetConditionMasters>>() {
        }.getType();
        List<AssetConditionMasters> assetConditionMasters = gson.fromJson(assetConditionMastersString, type);
        return assetConditionMasters;
    }

}