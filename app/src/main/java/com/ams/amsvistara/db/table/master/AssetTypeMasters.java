package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetTypeMasters {
    @Expose
    @SerializedName("AssetTypeDescription")
    public String AssetTypeDescription;
    @Expose
    @SerializedName("AssetTypeName")
    public String AssetTypeName;
    @Expose
    @SerializedName("AssetTypeId")
    public int AssetTypeId;

    public AssetTypeMasters() {
    }


}
