package com.ams.amsvistara.model.updatedModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DRItem {
    @SerializedName("AssetID")
    @Expose
     public Integer  AsetID;


     public Integer  getAsetID() {
         return AsetID;
     }

     public void setAsetID(Integer  asetID) {
         AsetID = asetID;
     }
 }