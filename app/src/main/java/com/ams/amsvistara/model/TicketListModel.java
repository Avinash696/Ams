package com.ams.amsvistara.model;

import com.google.android.gms.common.api.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketListModel {
    public TicketListModel(String asset, String assignTo, String title, String description, String image, String status) {
        Asset = asset;
        AssignTo = assignTo;
        Title = title;
        Description = description;
        Image = image;
        Status = status;
    }

    public TicketListModel(String status, String asset, String assignTo, String title, String description) {
        Asset = asset;
        AssignTo = assignTo;
        Title = title;
        Description = description;
        Status = status;
    }

    @Expose
    @SerializedName("asset")
    private String Asset;
    private String AssignTo;
    private String Title;
    private String Description;
    private String Image;
    private String Status;

    public String getAsset() {
        return Asset;
    }

    public void setAsset(String asset) {
        Asset = asset;
    }

    public String getAssignTo() {
        return AssignTo;
    }

    public void setAssignTo(String assignTo) {
        AssignTo = assignTo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
