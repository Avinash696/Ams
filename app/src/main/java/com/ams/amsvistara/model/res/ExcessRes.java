package com.ams.amsvistara.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExcessRes {
    @Expose
    @SerializedName("ResponseMessage")
    public String responsemessage;
    @Expose
    @SerializedName("ResponseStatus")
    public String responsestatus;
    @Expose
    @SerializedName("PVExcessID")
    public int pvexcessid;
    @Expose
    @SerializedName("PVID")
    public int pvid;
    @Expose
    @SerializedName("AssetNumber")
    public String assetnumber;
}
