package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.SubLocationMasters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class SubLocationConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromSubLocationMastersList(List<SubLocationMasters> subLocationMasters) {
        if (subLocationMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SubLocationMasters>>() {
        }.getType();
        String json = gson.toJson(subLocationMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<SubLocationMasters> toSubLocationMastersList(String subLocationMastersString) {
        if (subLocationMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SubLocationMasters>>() {
        }.getType();
        List<SubLocationMasters> subLocationMasters = gson.fromJson(subLocationMastersString, type);
        return subLocationMasters;
    }

}