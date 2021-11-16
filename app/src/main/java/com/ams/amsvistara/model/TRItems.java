package com.ams.amsvistara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TRItems implements Serializable {
    @Expose
    @SerializedName("TRDStatus")
    public String trdstatus;
    @Expose
    @SerializedName("LocationString")
    public String locationstring;
    @Expose
    @SerializedName("Longitude")
    public String longitude;
    @Expose
    @SerializedName("Latitude")
    public String latitude;
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
    public int imagecount;
    @Expose
    @SerializedName("SaveFlag")
    public boolean saveflag;
    @Expose
    @SerializedName("CapitalizationDate")
    public String capitalizationdate;
    @Expose
    @SerializedName("Vendor")
    public String vendor;
    @Expose
    @SerializedName("ClientRemarks")
    public String clientremarks;
    @Expose
    @SerializedName("TRName")
    public String trname;
    @Expose
    @SerializedName("SpocName")
    public String spocname;
    @Expose
    @SerializedName("AssetConditionName")
    public String assetconditionname;
    @Expose
    @SerializedName("AssetConditionID")
    public int assetconditionid;
    @Expose
    @SerializedName("AssetSurfaceName")
    public String assetsurfacename;
    @Expose
    @SerializedName("AssetSurfaceID")
    public int assetsurfaceid;
    @Expose
    @SerializedName("AssetTypeName")
    public String assettypename;
    @Expose
    @SerializedName("AssetTypeID")
    public int assettypeid;
    @Expose
    @SerializedName("Model")
    public String model;
    @Expose
    @SerializedName("Make")
    public String make;
    @Expose
    @SerializedName("SerialNumber")
    public String serialnumber;
    @Expose
    @SerializedName("AssetName")
    public String assetname;
    @Expose
    @SerializedName("AssetNumber")
    public String assetnumber;
    @Expose
    @SerializedName("SubLocationName")
    public String sublocationname;
    @Expose
    @SerializedName("SubLocationID")
    public int sublocationid;
    @Expose
    @SerializedName("LocationName")
    public String locationname;
    @Expose
    @SerializedName("LocationID")
    public int locationid;
    @Expose
    @SerializedName("AssetID")
    public int assetid;
    @Expose
    @SerializedName("TRID")
    public int trid;
    @Expose
    @SerializedName("TRDetailID")
    public int trdetailid;
    @Expose
    @SerializedName("TRDRemarks")
    public int trdremarks;
    @Expose
    @SerializedName("QRCode")
    public String qrcode;

}
