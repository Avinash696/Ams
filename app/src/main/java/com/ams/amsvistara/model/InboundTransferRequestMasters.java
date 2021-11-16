package com.ams.amsvistara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InboundTransferRequestMasters {
    @Expose
    @SerializedName("TRRemarks")
    public String trremarks;
    @Expose
    @SerializedName("TRStatus")
    public String trstatus;
    @Expose
    @SerializedName("TRUser")
    public String truser;
    @Expose
    @SerializedName("TRUserID")
    public int truserid;
    @Expose
    @SerializedName("TransferDate")
    public String transferdate;
    @Expose
    @SerializedName("RequestDate")
    public String requestdate;
    @Expose
    @SerializedName("TRCode")
    public String trcode;
    @Expose
    @SerializedName("ToLocation")
    public String tolocation;
    @Expose
    @SerializedName("ToLocationID")
    public int tolocationid;
    @Expose
    @SerializedName("FromLocation")
    public String fromlocation;
    @Expose
    @SerializedName("FromLocationID")
    public int fromlocationid;
    @Expose
    @SerializedName("FinancialYear")
    public String financialyear;
    @Expose
    @SerializedName("FinancialYearID")
    public int financialyearid;
    @Expose
    @SerializedName("TransferRequestID")
    public int transferrequestid;
    @Expose
    @SerializedName("TRItems")
    public ArrayList<TRItems> tritems;
}
