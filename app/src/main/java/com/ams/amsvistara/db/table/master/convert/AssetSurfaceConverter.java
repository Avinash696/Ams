package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.AssetSurfaceMasters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class AssetSurfaceConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromAssetSurfaceMastersList(List<AssetSurfaceMasters> assetSurfaceMasters) {
        if (assetSurfaceMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetSurfaceMasters>>() {
        }.getType();
        String json = gson.toJson(assetSurfaceMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<AssetSurfaceMasters> toAssetSurfaceMastersList(String assetSurfaceMastersString) {
        if (assetSurfaceMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AssetSurfaceMasters>>() {
        }.getType();
        List<AssetSurfaceMasters> assetSurfaceMasters = gson.fromJson(assetSurfaceMastersString, type);
        return assetSurfaceMasters;
    }

}