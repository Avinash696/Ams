package com.ams.amsvistara.model.updatedModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewDisposalModel {
    public String DRCode;
    @SerializedName("DRItems")
    @Expose
    public List<DRItem> DRItemsList;
    public String DRRemarks;
    public String DRStatus;
    public String DRUser;
    public String DRUserID;

    public String getDRCode() {
        return DRCode;
    }

    public void setDRCode(String DRCode) {
        this.DRCode = DRCode;
    }

    public List<DRItem> getDRItemsList() {
        return DRItemsList;
    }

    public void setDRItemsList(List<DRItem> DRItemsList) {
        this.DRItemsList = DRItemsList;
    }

    public String getDRRemarks() {
        return DRRemarks;
    }

    public void setDRRemarks(String DRRemarks) {
        this.DRRemarks = DRRemarks;
    }

    public NewDisposalModel(List<DRItem> DRItemsList, String DRCode, String DRRemarks, String DRStatus, String DRUser, String DRUserID, String disposalDate, String disposalType, String fromLocation, String requestDate) {
        this.DRItemsList = DRItemsList;
        this.DRCode = DRCode;
        this.DRRemarks = DRRemarks;
        this.DRStatus = DRStatus;
        this.DRUser = DRUser;
        this.DRUserID = DRUserID;
        DisposalDate = disposalDate;
        DisposalType = disposalType;
        FromLocation = fromLocation;
        RequestDate = requestDate;
    }

    public String getDRStatus() {
        return DRStatus;
    }

    public void setDRStatus(String DRStatus) {
        this.DRStatus = DRStatus;
    }

    public String getDRUser() {
        return DRUser;
    }

    public void setDRUser(String DRUser) {
        this.DRUser = DRUser;
    }

    public String getDRUserID() {
        return DRUserID;
    }

    public void setDRUserID(String DRUserID) {
        this.DRUserID = DRUserID;
    }

    public String getDisposalDate() {
        return DisposalDate;
    }

    public void setDisposalDate(String disposalDate) {
        DisposalDate = disposalDate;
    }

    public String getDisposalRequestID() {
        return DisposalRequestID;
    }

    public void setDisposalRequestID(String disposalRequestID) {
        DisposalRequestID = disposalRequestID;
    }

    public String getDisposalType() {
        return DisposalType;
    }

    public void setDisposalType(String disposalType) {
        DisposalType = disposalType;
    }

    public String getFinancialYear() {
        return FinancialYear;
    }

    public void setFinancialYear(String financialYear) {
        FinancialYear = financialYear;
    }

    public String getFinancialYearID() {
        return FinancialYearID;
    }

    public void setFinancialYearID(String financialYearID) {
        FinancialYearID = financialYearID;
    }

    public String getFromLocation() {
        return FromLocation;
    }

    public void setFromLocation(String fromLocation) {
        FromLocation = fromLocation;
    }

    public String getFromLocationID() {
        return FromLocationID;
    }

    public void setFromLocationID(String fromLocationID) {
        FromLocationID = fromLocationID;
    }

    public String getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(String requestDate) {
        RequestDate = requestDate;
    }

    public String DisposalDate;
    public String DisposalRequestID;
    public String DisposalType;
    public String FinancialYear;
    public String FinancialYearID;
    public String FromLocation;
    public String FromLocationID;
    public String RequestDate;
}