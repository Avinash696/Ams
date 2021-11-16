package com.ams.amsvistara.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PvAssetRes {

    @Expose
    @SerializedName("ResponseMessage")
    public String responseMessage;
    @Expose
    @SerializedName("Response")
    public String response;
    @Expose
    @SerializedName("PVDetailID")
    public int PVDetailID;
    @Expose
    @SerializedName("PVID")
    public int PVID;
}

