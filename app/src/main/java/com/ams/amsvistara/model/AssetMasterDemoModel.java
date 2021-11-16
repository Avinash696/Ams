package com.ams.amsvistara.model;

public class AssetMasterDemoModel {
    public String AssetCode;
    public String AssetName;

    public String getAssetCode() {
        return AssetCode;
    }

    public void setAssetCode(String assetCode) {
        AssetCode = assetCode;
    }

    public String getAssetName() {
        return AssetName;
    }

    public void setAssetName(String assetName) {
        AssetName = assetName;
    }

    public AssetMasterDemoModel(String assetCode, String assetName) {
        AssetCode = assetCode;
        AssetName = assetName;
    }
}
