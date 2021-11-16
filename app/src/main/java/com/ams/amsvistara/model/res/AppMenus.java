package com.ams.amsvistara.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppMenus {
    @Expose
    @SerializedName("Counters")
    public String counters;
    @Expose
    @SerializedName("MenuTitle")
    public String menutitle;
    @Expose
    @SerializedName("MenuId")
    public int menuid;
}
