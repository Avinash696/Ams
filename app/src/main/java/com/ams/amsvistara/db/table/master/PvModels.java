package com.ams.amsvistara.db.table.master;

import androidx.room.TypeConverters;

import com.ams.amsvistara.db.table.master.convert.PvItemConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PvModels {
    @Expose
    @SerializedName("PVRemarks")
    public String PVRemarks;
    @Expose
    @SerializedName("PVStatus")
    public String PVStatus;
    @Expose
    @SerializedName("PVUser")
    public String PVUser;
    @Expose
    @SerializedName("PVUserID")
    public int PVUserID;
    @Expose
    @SerializedName("PVEndDate")
    public String PVEndDate;
    @Expose
    @SerializedName("PVStartDate")
    public String PVStartDate;
    @Expose
    @SerializedName("PVCode")
    public String PVCode;
    @Expose
    @SerializedName("PVLocation")
    public String PVLocation;
    @Expose
    @SerializedName("PVLocationID")
    public int PVLocationID;
    @Expose
    @SerializedName("PVFinancialYear")
    public String PVFinancialYear;
    @Expose
    @SerializedName("PVFinancialYearID")
    public int PVFinancialYearID;
    @Expose
    @SerializedName("PhysicalVerificationID")
    public int PhysicalVerificationID;
    @Expose
    @SerializedName("PVItems")
    @TypeConverters(PvItemConverter.class)
    public List<PvItems> PVItems=new ArrayList<>();

    public PvModels() {
    }

}
