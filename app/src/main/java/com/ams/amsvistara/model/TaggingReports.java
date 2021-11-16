package com.ams.amsvistara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public  class TaggingReports {


    @Expose
    @SerializedName("ResponseMessage")
    public String responsemessage;
    @Expose
    @SerializedName("ResponseStatus")
    public String responsestatus;
    @Expose
    @SerializedName("ReportItems")
    public List<ReportItems> reportitems=new ArrayList<>();
    @Expose
    @SerializedName("Last3DaysDone")
    public int last3daysdone;
    @Expose
    @SerializedName("Pending")
    public int pending;
    @Expose
    @SerializedName("Done")
    public int done;
    @Expose
    @SerializedName("Total")
    public int total;
}
