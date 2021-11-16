package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.AssetSubCategoryMasters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class AssetSubCategoryConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromAssetSubCategoryMastersList(List<AssetSubCategoryMasters> assetSubCategoryMasters) {
        if (assetSubCategoryMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetSubCategoryMasters>>() {
        }.getType();
        String json = gson.toJson(assetSubCategoryMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<AssetSubCategoryMasters> toAssetSubCategoryMastersList(String assetMastersString) {
        if (assetMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetSubCategoryMasters>>() {
        }.getType();
        List<AssetSubCategoryMasters> assetSubCategoryMasters = gson.fromJson(assetMastersString, type);
        return assetSubCategoryMasters;
    }

}