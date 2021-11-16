package com.ams.amsvistara.model;

import androidx.lifecycle.MutableLiveData;

public class TransferReqModel {
    public String trId;
    public String trCode;
    public String remark;
    public String requestDate;
    public String transferDate;
    public String status;
    //spinner
    public String spFromLocation;
    public String spFromUser;
    public String spToLocation;
    public String spUser;

    public TransferReqModel(String trId, String trCode, String remark, String requestDate, String transferDate, String status, String spFromLocation, String spFromUser, String spToLocation, String spUser) {
        this.trId = trId;
        this.trCode = trCode;
        this.remark = remark;
        this.requestDate = requestDate;
        this.transferDate = transferDate;
        this.status = status;
        this.spFromLocation = spFromLocation;
        this.spFromUser = spFromUser;
        this.spToLocation = spToLocation;
        this.spUser = spUser;
    }


    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    public String getTrCode() {
        return trCode;
    }

    public void setTrCode(String trCode) {
        this.trCode = trCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpFromLocation() {
        return spFromLocation;
    }

    public void setSpFromLocation(String spFromLocation) {
        this.spFromLocation = spFromLocation;
    }

    public String getSpFromUser() {
        return spFromUser;
    }

    public void setSpFromUser(String spFromUser) {
        this.spFromUser = spFromUser;
    }

    public String getSpToLocation() {
        return spToLocation;
    }

    public void setSpToLocation(String spToLocation) {
        this.spToLocation = spToLocation;
    }

    public String getSpUser() {
        return spUser;
    }

    public void setSpUser(String spUser) {
        this.spUser = spUser;
    }
}
