package com.ams.amsvistara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaggingListModel {
    @SerializedName("tagName")
    @Expose
    public String tagName;
    @SerializedName("tagCode")
    @Expose
    public String tagCode;

    public TaggingListModel(String tagCode, String tagName) {
        this.tagName = tagName;
        this.tagCode = tagCode;
    }
}
