package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.LocationMasters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class LocationConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromLocationMastersList(List<LocationMasters> locationMasters) {
        if (locationMasters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<LocationMasters>>() {
        }.getType();
        String json = gson.toJson(locationMasters, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<LocationMasters> toLocationMastersList(String locationMastersString) {
        if (locationMastersString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<LocationMasters>>() {
        }.getType();
        List<LocationMasters> locationMasters = gson.fromJson(locationMastersString, type);
        return locationMasters;
    }

}