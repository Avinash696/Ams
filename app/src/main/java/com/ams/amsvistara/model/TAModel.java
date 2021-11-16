package com.ams.amsvistara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TAModel {

    @Expose
    @SerializedName("ResponseMessage")
    public String responsemessage;
    @Expose
    @SerializedName("ResponseStatus")
    public String responsestatus;
    @Expose
    @SerializedName("InboundTransferRequestMasters")
    public ArrayList<InboundTransferRequestMasters> inboundtransferrequestmasters;
}
