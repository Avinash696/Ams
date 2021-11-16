package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationMasters {
    @Expose
    @SerializedName("LocationName")
    public String LocationName;
    @Expose
    @SerializedName("LocationCode")
    public String LocationCode;
    @Expose
    @SerializedName("LocationID")
    public int LocationID;

    public LocationMasters() {
    }


}
