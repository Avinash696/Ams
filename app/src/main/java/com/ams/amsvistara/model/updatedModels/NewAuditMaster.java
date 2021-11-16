
package com.ams.amsvistara.model.updatedModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewAuditMaster {

    @SerializedName("AuditItems")
    @Expose
    private List<AuditItem> auditItems = null;
    @SerializedName("AuditID")
    @Expose
    private Integer auditID;
    @SerializedName("TagID")
    @Expose
    private Integer tagID;
    @SerializedName("AuditCode")
    @Expose
    private String auditCode;
    @SerializedName("AuditStartDate")
    @Expose
    private String auditStartDate;
    @SerializedName("AuditEndDate")
    @Expose
    private String auditEndDate;
    @SerializedName("AuditUserId")
    @Expose
    private Integer auditUserId;
    @SerializedName("AuditUser")
    @Expose
    private String auditUser;
    @SerializedName("AuditStatus")
    @Expose
    private String auditStatus;
    @SerializedName("AuditRemarks")
    @Expose
    private String auditRemarks;

    public List<AuditItem> getAuditItems() {
        return auditItems;
    }

    public void setAuditItems(List<AuditItem> auditItems) {
        this.auditItems = auditItems;
    }

    public Integer getAuditID() {
        return auditID;
    }

    public void setAuditID(Integer auditID) {
        this.auditID = auditID;
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }

    public String getAuditCode() {
        return auditCode;
    }

    public void setAuditCode(String auditCode) {
        this.auditCode = auditCode;
    }

    public String getAuditStartDate() {
        return auditStartDate;
    }

    public void setAuditStartDate(String auditStartDate) {
        this.auditStartDate = auditStartDate;
    }

    public String getAuditEndDate() {
        return auditEndDate;
    }

    public void setAuditEndDate(String auditEndDate) {
        this.auditEndDate = auditEndDate;
    }

    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditRemarks() {
        return auditRemarks;
    }

    public void setAuditRemarks(String auditRemarks) {
        this.auditRemarks = auditRemarks;
    }

}
