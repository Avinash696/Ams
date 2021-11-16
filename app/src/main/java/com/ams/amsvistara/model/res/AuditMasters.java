package com.ams.amsvistara.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuditMasters implements Serializable {
    @Expose
    @SerializedName("AuditRemarks")
    public String auditremarks;
    @Expose
    @SerializedName("AuditStatus")
    public String auditstatus;
    @Expose
    @SerializedName("AuditUser")
    public String audituser;
    @Expose
    @SerializedName("AuditUserID")
    public int audituserid;
    @Expose
    @SerializedName("AuditEndDate")
    public String auditenddate;
    @Expose
    @SerializedName("AuditStartDate")
    public String auditstartdate;
    @Expose
    @SerializedName("AuditCode")
    public String auditcode;
    @Expose
    @SerializedName("TagID")
    public int tagid;
    @Expose
    @SerializedName("AuditID")
    public int auditid;
    @Expose
    @SerializedName("AuditItems")
    public List<AuditExcessItem> audititems=new ArrayList<>();
}
