package com.ams.amsvistara.db.table.master.convert;

import androidx.room.TypeConverter;

import com.ams.amsvistara.db.table.master.FinancialYears;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class FinancialYearConverter implements Serializable {

    @TypeConverter // note this annotation
    public String fromFinancialYearList(List<FinancialYears> financialYear) {
        if (financialYear == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FinancialYears>>() {
        }.getType();
        String json = gson.toJson(financialYear, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<FinancialYears> toAssetMastersList(String financialYear) {
        if (financialYear == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FinancialYears>>() {
        }.getType();
        List<FinancialYears> financialYears = gson.fromJson(financialYear, type);
        return financialYears;
    }

}