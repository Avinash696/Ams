package com.ams.amsvistara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TaggingReportsModel {



    @Expose
    @SerializedName("totalTaggingCount")
    public String totalTaggingCount;
    @Expose
    @SerializedName("doneTaggingCount")
    public String doneTaggingCount;

    List<TaggingReportsItem> pvReportsItems =new ArrayList<>();

    @Expose
    @SerializedName("lastThreeMonthTotalTaggingCount")
    public String lastThreeMonthTotalTaggingCount;
    @Expose
    @SerializedName("lastThreeMonthDoneTaggingCount")
    public String lastThreeMonthDoneTaggingCount;

}
