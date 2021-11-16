package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetSubCategoryMasters {
    @Expose
    @SerializedName("AssetSubCategoryDescription")
    public String AssetSubCategoryDescription;
    @Expose
    @SerializedName("AssetSubCategoryName")
    public String AssetSubCategoryName;
    @Expose
    @SerializedName("AssetCategoryID")
    public int AssetCategoryID;
    @Expose
    @SerializedName("AssetSubCategoryID")
    public int AssetSubCategoryID;

    public AssetSubCategoryMasters() {
    }

}
