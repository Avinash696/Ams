package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.AssetCategoryMasters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class AssetCategoryConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromAssetCategoryMastersList(List<AssetCategoryMasters> assetCategoryMasters) {
        if (assetCategoryMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetCategoryMasters>>() {
        }.getType();
        String json = gson.toJson(assetCategoryMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<AssetCategoryMasters> toAssetCategoryMastersList(String assetCategoryMastersString) {
        if (assetCategoryMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetCategoryMasters>>() {
        }.getType();
        List<AssetCategoryMasters> assetCategoryMasters = gson.fromJson(assetCategoryMastersString, type);
        return assetCategoryMasters;
    }

}