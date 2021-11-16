
package com.ams.amsvistara.model.updatedModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TRItem {

    @SerializedName("AssetID")
    @Expose
    private Integer assetID;

    public Integer getAssetID() {
        return assetID;
    }

    public void setAssetID(Integer assetID) {
        this.assetID = assetID;
    }

}
