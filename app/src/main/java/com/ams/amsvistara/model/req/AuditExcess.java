package com.ams.amsvistara.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuditExcess {


    @Expose
    @SerializedName("AuditERemarks")
    public String auditeremarks;
    @Expose
    @SerializedName("AuditEStatus")
    public String auditestatus;
    @Expose
    @SerializedName("Image5")
    public String image5="";
    @Expose
    @SerializedName("Image4")
    public String image4="";
    @Expose
    @SerializedName("Image3")
    public String image3="";
    @Expose
    @SerializedName("Image2")
    public String image2="";
    @Expose
    @SerializedName("Image1")
    public String image1="";
    @Expose
    @SerializedName("ImageCount")
    public int imagecount=0;
    @Expose
    @SerializedName("LocationString")
    public String locationstring="";
    @Expose
    @SerializedName("Longitude")
    public String longitude;
    @Expose
    @SerializedName("Latitude")
    public String latitude;
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
    @SerializedName("ExcessID")
    public int excessid;
    @Expose
    @SerializedName("AuditID")
    public int auditid;
}
