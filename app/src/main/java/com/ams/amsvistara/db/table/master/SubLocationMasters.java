package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubLocationMasters {
    @Expose
    @SerializedName("SubLocationName")
    public String SubLocationName;
    @Expose
    @SerializedName("SubLocationCode")
    public String SubLocationCode;
    @Expose
    @SerializedName("SubLocationID")
    public int SubLocationID;

    public SubLocationMasters() {
    }

}
