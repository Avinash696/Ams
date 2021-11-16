package com.ams.amsvistara.db.table.excess;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "pv_excess")
public class PvExcessReq {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    @Expose
    @SerializedName("PVERemarks")
    public String pveremarks="";
    @Expose
    @SerializedName("PVEStatus")
    public String pvestatus="";
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
    @SerializedName("SaveFlag")
    public boolean saveflag;
    @Expose
    @SerializedName("CapitalizationDate")
    public String capitalizationdate="";
    @Expose
    @SerializedName("Vendor")
    public String vendor="";
    @Expose
    @SerializedName("ClientRemarks")
    public String clientremarks="";
    @Expose
    @SerializedName("PVName")
    public String pvname="";
    @Expose
    @SerializedName("SpocName")
    public String spocname="";
    @Expose
    @SerializedName("AssetConditionName")
    public String assetconditionname="";
    @Expose
    @SerializedName("AssetConditionID")
    public int assetconditionid=0;
    @Expose
    @SerializedName("AssetSurfaceName")
    public String assetsurfacename="";
    @Expose
    @SerializedName("AssetSurfaceID")
    public int assetsurfaceid=0;
    @Expose
    @SerializedName("AssetTypeName")
    public String assettypename="";
    @Expose
    @SerializedName("AssetTypeID")
    public int assettypeid=0;
    @Expose
    @SerializedName("Model")
    public String model="";
    @Expose
    @SerializedName("Make")
    public String make="";
    @Expose
    @SerializedName("SerialNumber")
    public String serialnumber="";
    @Expose
    @SerializedName("AssetName")
    public String assetname="";
    @Expose
    @SerializedName("AssetNumber")
    public String assetnumber="";
    @Expose
    @SerializedName("SubLocationName")
    public String sublocationname="";
    @Expose
    @SerializedName("SubLocationID")
    public int sublocationid=0;
    @Expose
    @SerializedName("LocationName")
    public String locationname="";
    @Expose
    @SerializedName("LocationID")
    public int locationid=0;
    @Expose
    @SerializedName("AssetID")
    public int assetid=0;
    @Expose
    @SerializedName("PVID")
    public int pvid=0;
    @Expose
    @SerializedName("PVExcessID")
    public int pvexcessid=0;
}
