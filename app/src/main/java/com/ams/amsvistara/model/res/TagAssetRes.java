package com.ams.amsvistara.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagAssetRes {


    @Expose
    @SerializedName("ResponseMessage")
    public String responseMessage;
    @Expose
    @SerializedName("Response")
    public String response;
    @Expose
    @SerializedName("ResponseStatus")
    public String responseStatus;
    @Expose
    @SerializedName("TAGDetailID")
    public int TAGDetailID;
    @Expose
    @SerializedName("TAGID")
    public int TAGID;
}
