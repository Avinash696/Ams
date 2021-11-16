package com.ams.amsvistara.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public  class AuditMasterRes {
    @Expose
    @SerializedName("ResponseMessage")
    public String responsemessage;
    @Expose
    @SerializedName("ResponseStatus")
    public String responsestatus;
    @Expose
    @SerializedName("AuditMasters")
    public List<AuditMasters> auditmasters=new ArrayList<>();
}
