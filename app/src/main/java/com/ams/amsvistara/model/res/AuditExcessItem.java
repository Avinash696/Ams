package com.ams.amsvistara.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuditExcessItem  implements Serializable {

    @Expose
    @SerializedName("AuditDRemarks")
    public String auditdremarks;
    @Expose
    @SerializedName("SaveFlag")
    public boolean saveflag;
    @Expose
    @SerializedName("AuditDStatus")
    public String auditdstatus;
    @Expose
    @SerializedName("QRCode")
    public String qrcode;
    @Expose
    @SerializedName("AssetName")
    public String assetname;
    @Expose
    @SerializedName("AssetNumber")
    public String assetnumber;
    @Expose
    @SerializedName("AssetID")
    public int assetid;
    @Expose
    @SerializedName("AuditID")
    public int auditid;
    @Expose
    @SerializedName("AuditDetailID")
    public int auditdetailid;

  /*  @Expose
    @SerializedName("AssetName")
    public String assetName;
    @Expose
    @SerializedName("AssetNumber")
    public String assetNumber;
    @Expose
    @SerializedName("QrCode")
    public String qrCode;
    @Expose
    @SerializedName("FoundFlag")
    public boolean foundFlag;*/

}
