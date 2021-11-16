package com.ams.amsvistara.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class AuditExcessRes {


    @Expose
    @SerializedName("ResponseMessage")
    public String responsemessage;
    @Expose
    @SerializedName("ResponseStatus")
    public String responsestatus;
    @Expose
    @SerializedName("AssetNumber")
    public String assetnumber;
    @Expose
    @SerializedName("ExcessID")
    public int excessid;
    @Expose
    @SerializedName("AuditID")
    public int auditid;
}
