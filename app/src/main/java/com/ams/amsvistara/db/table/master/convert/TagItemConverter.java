package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.TagItems;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class TagItemConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromTagItemsMastersList(List<TagItems> assetMasters) {
        if (assetMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TagItems>>() {
        }.getType();
        String json = gson.toJson(assetMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<TagItems> toAssetMastersList(String assetMastersString) {
        if (assetMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TagItems>>() {
        }.getType();
        List<TagItems> assetMasters = gson.fromJson(assetMastersString, type);
        return assetMasters;
    }

}