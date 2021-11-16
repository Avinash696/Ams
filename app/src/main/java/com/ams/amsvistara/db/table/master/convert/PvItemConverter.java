package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.PvItems;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class PvItemConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromPvItemsList(List<PvItems> pvItems) {
        if (pvItems == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PvItems>>() {
        }.getType();
        String json = gson.toJson(pvItems, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<PvItems> toPvItemsList(String pvItemsString) {
        if (pvItemsString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PvItems>>() {
        }.getType();
        List<PvItems> assetMasters = gson.fromJson(pvItemsString, type);
        return assetMasters;
    }

}