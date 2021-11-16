package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinancialYears {
    @Expose
    @SerializedName("FYEndDate")
    public String FYEndDate;
    @Expose
    @SerializedName("FYStartDate")
    public String FYStartDate;
    @Expose
    @SerializedName("FinancialYear")
    public String FinancialYear;
    @Expose
    @SerializedName("FinancialYearID")
    public int FinancialYearID;

    public FinancialYears() {
    }

}
