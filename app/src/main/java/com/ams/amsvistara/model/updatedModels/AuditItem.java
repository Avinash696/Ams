
package com.ams.amsvistara.model.updatedModels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AuditItem {

    @SerializedName("AuditDetailID")
    @Expose
    private Integer auditDetailID;
    @SerializedName("AuditID")
    @Expose
    private Integer auditID;
    @SerializedName("AssetID")
    @Expose
    private Integer assetID;
    @SerializedName("AssetNumber")
    @Expose
    private String assetNumber;
    @SerializedName("AssetName")
    @Expose
    private String assetName;
    @SerializedName("QRCode")
    @Expose
    private String qRCode;
    @SerializedName("SaveFlag")
    @Expose
    private Boolean saveFlag;
    @SerializedName("AuditDStatus")
    @Expose
    private String auditDStatus;
    @SerializedName("AuditDRemarks")
    @Expose
    private String auditDRemarks;

    public Integer getAuditDetailID() {
        return auditDetailID;
    }

    public void setAuditDetailID(Integer auditDetailID) {
        this.auditDetailID = auditDetailID;
    }

    public Integer getAuditID() {
        return auditID;
    }

    public void setAuditID(Integer auditID) {
        this.auditID = auditID;
    }

    public Integer getAssetID() {
        return assetID;
    }

    public void setAssetID(Integer assetID) {
        this.assetID = assetID;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getQRCode() {
        return qRCode;
    }

    public void setQRCode(String qRCode) {
        this.qRCode = qRCode;
    }

    public Boolean getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(Boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getAuditDStatus() {
        return auditDStatus;
    }

    public void setAuditDStatus(String auditDStatus) {
        this.auditDStatus = auditDStatus;
    }

    public String getAuditDRemarks() {
        return auditDRemarks;
    }

    public void setAuditDRemarks(String auditDRemarks) {
        this.auditDRemarks = auditDRemarks;
    }

}
