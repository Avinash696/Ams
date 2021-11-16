
package com.ams.amsvistara.model.updatedModels;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NewTransferReq {

    @SerializedName("RequestVerificationID")
    @Expose
    private Integer requestVerificationID;
    @SerializedName("FinancialYearID")
    @Expose
    private Integer financialYearID;
    @SerializedName("FinancialYear")
    @Expose
    private String financialYear;
    @SerializedName("FromLocationID")
    @Expose
    private Integer fromLocationID;
    @SerializedName("FromLocation")
    @Expose
    private String fromLocation;
    @SerializedName("ToLocationID")
    @Expose
    private Integer toLocationID;
    @SerializedName("ToLocation")
    @Expose
    private String toLocation;
    @SerializedName("TRCode")
    @Expose
    private String tRCode;
    @SerializedName("RequestDate")
    @Expose
    private String requestDate;
    @SerializedName("TransferDate")
    @Expose
    private String transferDate;
    @SerializedName("TRUserID")
    @Expose
    private Integer tRUserID;
    @SerializedName("TRUser")
    @Expose
    private String tRUser;
    @SerializedName("TRStatus")
    @Expose
    private String tRStatus;
    @SerializedName("TRRemarks")
    @Expose
    private String tRRemarks;
    @SerializedName("TRItems")
    @Expose
    private List<TRItem> tRItems ;

    public NewTransferReq(String fromLocation, String toLocation, String tRCode, String requestDate,
                          String transferDate, String tRUser, String tRStatus,
                          String tRRemarks,List<TRItem> tRItems) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.tRCode = tRCode;
        this.requestDate = requestDate;
        this.transferDate = transferDate;
        this.tRUser = tRUser;
        this.tRStatus = tRStatus;
        this.tRRemarks = tRRemarks;
        this.tRItems = tRItems;
    }

    public String gettRCode() {
        return tRCode;
    }

    public void settRCode(String tRCode) {
        this.tRCode = tRCode;
    }

    public Integer gettRUserID() {
        return tRUserID;
    }

    public void settRUserID(Integer tRUserID) {
        this.tRUserID = tRUserID;
    }

    public String gettRUser() {
        return tRUser;
    }

    public void settRUser(String tRUser) {
        this.tRUser = tRUser;
    }

    public String gettRStatus() {
        return tRStatus;
    }

    public void settRStatus(String tRStatus) {
        this.tRStatus = tRStatus;
    }

    public String gettRRemarks() {
        return tRRemarks;
    }

    public void settRRemarks(String tRRemarks) {
        this.tRRemarks = tRRemarks;
    }

    public List<TRItem> gettRItems() {
        return tRItems;
    }

    public void settRItems(List<TRItem> tRItems) {
        this.tRItems = tRItems;
    }

    public Integer getRequestVerificationID() {
        return requestVerificationID;
    }

    public void setRequestVerificationID(Integer requestVerificationID) {
        this.requestVerificationID = requestVerificationID;
    }

    public Integer getFinancialYearID() {
        return financialYearID;
    }

    public void setFinancialYearID(Integer financialYearID) {
        this.financialYearID = financialYearID;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public Integer getFromLocationID() {
        return fromLocationID;
    }

    public void setFromLocationID(Integer fromLocationID) {
        this.fromLocationID = fromLocationID;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Integer getToLocationID() {
        return toLocationID;
    }

    public void setToLocationID(Integer toLocationID) {
        this.toLocationID = toLocationID;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getTRCode() {
        return tRCode;
    }

    public void setTRCode(String tRCode) {
        this.tRCode = tRCode;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public Integer getTRUserID() {
        return tRUserID;
    }

    public void setTRUserID(Integer tRUserID) {
        this.tRUserID = tRUserID;
    }

    public String getTRUser() {
        return tRUser;
    }

    public void setTRUser(String tRUser) {
        this.tRUser = tRUser;
    }

    public String getTRStatus() {
        return tRStatus;
    }

    public void setTRStatus(String tRStatus) {
        this.tRStatus = tRStatus;
    }

    public String getTRRemarks() {
        return tRRemarks;
    }

    public void setTRRemarks(String tRRemarks) {
        this.tRRemarks = tRRemarks;
    }

    public List<TRItem> getTRItems() {
        return tRItems;
    }

    public void setTRItems(List<TRItem> tRItems) {
        this.tRItems = tRItems;
    }

}
