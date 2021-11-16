package com.ams.amsvistara.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GridViewModel {
    @SerializedName("tabName")
    @Expose
    public String tabName;
    @SerializedName("tabId")
    @Expose
    public String tabId;
    @SerializedName("tabStatus")
    @Expose
    public String tabStatus;

    public GridViewModel(String tabName, String tabId, String tabStatus) {
        this.tabName = tabName;
        this.tabId = tabId;
        this.tabStatus = tabStatus;
    }

}
