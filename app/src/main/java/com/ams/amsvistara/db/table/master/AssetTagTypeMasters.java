package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetTagTypeMasters {
    @Expose
    @SerializedName("AssetTagTypeDescription")
    public String AssetTagTypeDescription;
    @Expose
    @SerializedName("AssetTagTypeName")
    public String AssetTagTypeName;
    @Expose
    @SerializedName("AssetTagTypeID")
    public int AssetTagTypeID;

    public AssetTagTypeMasters() {
    }

}
