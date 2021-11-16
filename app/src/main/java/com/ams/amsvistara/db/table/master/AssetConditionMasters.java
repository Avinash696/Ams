package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetConditionMasters {
    @Expose
    @SerializedName("AssetConditionDescription")
    public String AssetConditionDescription;
    @Expose
    @SerializedName("AssetConditionName")
    public String AssetConditionName;
    @Expose
    @SerializedName("AssetConditionID")
    public int AssetConditionID;

    public AssetConditionMasters() {
    }

}
