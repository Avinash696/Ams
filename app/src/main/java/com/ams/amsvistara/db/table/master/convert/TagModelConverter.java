package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.TagModels;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class TagModelConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromTagItemsMastersList(List<TagModels> assetMasters) {
        if (assetMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TagModels>>() {
        }.getType();
        String json = gson.toJson(assetMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<TagModels> toAssetMastersList(String assetMastersString) {
        if (assetMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TagModels>>() {
        }.getType();
        List<TagModels> assetMasters = gson.fromJson(assetMastersString, type);
        return assetMasters;
    }

}