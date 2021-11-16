package com.ams.amsvistara.model;

import androidx.lifecycle.MutableLiveData;

public class DisposeReqModel {
    public String drId ;
    public String drCode;
    public String remark ;
    public String requestDate ;
    public String disposeDate ;
    public String status ;
    //spinner
    public String disposetype;
    public String location;
    public String users;

    public String getDisposetype() {
        return disposetype;
    }

    public void setDisposetype(String disposetype) {
        this.disposetype = disposetype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public DisposeReqModel(String drId, String drCode, String remark, String requestDate, String disposeDate, String status, String disposetype, String location, String users) {
        this.drId = drId;
        this.drCode = drCode;
        this.remark = remark;
        this.requestDate = requestDate;
        this.disposeDate = disposeDate;
        this.status = status;
        this.disposetype = disposetype;
        this.location = location;
        this.users = users;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getDrCode() {
        return drCode;
    }

    public void setDrCode(String drCode) {
        this.drCode = drCode;
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

    public String getDisposeDate() {
        return disposeDate;
    }

    public void setDisposeDate(String disposeDate) {
        this.disposeDate = disposeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DisposeReqModel(String drId, String drCode, String remark, String requestDate, String disposeDate, String status) {
        this.drId = drId;
        this.drCode = drCode;
        this.remark = remark;
        this.requestDate = requestDate;
        this.disposeDate = disposeDate;
        this.status = status;
    }
}
