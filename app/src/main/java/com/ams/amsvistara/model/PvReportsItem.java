package com.ams.amsvistara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PvReportsItem {

    @Expose
    @SerializedName("AssetName")
    public String assetname;
    @Expose
    @SerializedName("AssetNumber")
    public String assetnumber;

}
