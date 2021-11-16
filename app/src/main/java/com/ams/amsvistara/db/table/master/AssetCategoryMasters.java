package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetCategoryMasters {
    @Expose
    @SerializedName("AssetCategoryDescription")
    public String AssetCategoryDescription;
    @Expose
    @SerializedName("AssetCategoryName")
    public String AssetCategoryName;
    @Expose
    @SerializedName("AssetCategoryID")
    public int AssetCategoryID;

    public AssetCategoryMasters() {
    }

}
