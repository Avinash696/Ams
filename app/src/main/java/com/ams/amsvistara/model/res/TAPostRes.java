package com.ams.amsvistara.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TAPostRes {

    @Expose
    @SerializedName("ResponseMessage")
    private String responsemessage;
    @Expose
    @SerializedName("ResponseStatus")
    private String responsestatus;
    @Expose
    @SerializedName("TRDetailID")
    private int trdetailid;
    @Expose
    @SerializedName("TRID")
    private int trid;
}

