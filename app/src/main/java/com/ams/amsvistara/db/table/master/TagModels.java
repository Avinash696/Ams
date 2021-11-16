package com.ams.amsvistara.db.table.master;

import androidx.room.TypeConverters;

import com.ams.amsvistara.db.table.master.convert.TagItemConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TagModels {
    @Expose
    @SerializedName("TagRemarks")
    public String TagRemarks;
    @Expose
    @SerializedName("TagStatus")
    public String TagStatus;
    @Expose
    @SerializedName("TagUser")
    public String TagUser;
    @Expose
    @SerializedName("TagUserID")
    public int TagUserID;
    @Expose
    @SerializedName("TagEndDate")
    public String TagEndDate;
    @Expose
    @SerializedName("TagStartDate")
    public String TagStartDate;
    @Expose
    @SerializedName("TagCode")
    public String TagCode;
    @Expose
    @SerializedName("PVID")
    public int PVID;
    @Expose
    @SerializedName("TAGID")
    public int TAGID;
    @Expose
    @SerializedName("TAGItems")
    @TypeConverters(TagItemConverter.class)
    public List<TagItems> TAGItems=new ArrayList<>();

    public TagModels() {
    }

}
