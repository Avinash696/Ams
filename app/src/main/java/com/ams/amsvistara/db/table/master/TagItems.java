package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagItems {
    @Expose
    @SerializedName("TagDRemarks")
    public String TagDRemarks;
    @Expose
    @SerializedName("SaveFlag")
    public boolean SaveFlag;
    @Expose
    @SerializedName("TagDStatus")
    public String TagDStatus;
    @Expose
    @SerializedName("Image5")
    public String Image5;
    @Expose
    @SerializedName("Image4")
    public String Image4;
    @Expose
    @SerializedName("Image3")
    public String Image3;
    @Expose
    @SerializedName("Image2")
    public String Image2;
    @Expose
    @SerializedName("Image1")
    public String Image1;
    @Expose
    @SerializedName("ImageCount")
    public int ImageCount;
    @Expose
    @SerializedName("LocationString")
    public String LocationString;
    @Expose
    @SerializedName("Longitude")
    public String Longitude;
    @Expose
    @SerializedName("Latitude")
    public String Latitude;
    @Expose
    @SerializedName("QRCode")
    public String QRCode;
    @Expose
    @SerializedName("PONumber")
    public String PONumber;
    @Expose
    @SerializedName("CapitalizationDate")
    public String CapitalizationDate;
    @Expose
    @SerializedName("Vendor")
    public String Vendor;
    @Expose
    @SerializedName("AssetName")
    public String AssetName;
    @Expose
    @SerializedName("AssetNumber")
    public String AssetNumber;
    @Expose
    @SerializedName("AssetID")
    public int AssetID;
    @Expose
    @SerializedName("TAGID")
    public int TAGID;
    @Expose
    @SerializedName("TAGDetailID")
    public int TAGDetailID;

    public TagItems() {
    }

}
