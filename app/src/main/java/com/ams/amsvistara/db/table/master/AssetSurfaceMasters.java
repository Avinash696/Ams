package com.ams.amsvistara.db.table.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetSurfaceMasters {
    @Expose
    @SerializedName("AssetSurfaceDescription")
    public String AssetSurfaceDescription;
    @Expose
    @SerializedName("AssetSurfaceName")
    public String AssetSurfaceName;
    @Expose
    @SerializedName("AssetSurfaceID")
    public int AssetSurfaceID;

    public AssetSurfaceMasters() {
    }


}
