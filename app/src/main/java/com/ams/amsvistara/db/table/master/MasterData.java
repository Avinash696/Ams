package com.ams.amsvistara.db.table.master;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ams.amsvistara.db.table.master.convert.AssetCategoryConverter;
import com.ams.amsvistara.db.table.master.convert.AssetConditionConverter;
import com.ams.amsvistara.db.table.master.convert.AssetSubCategoryConverter;
import com.ams.amsvistara.db.table.master.convert.AssetSurfaceConverter;
import com.ams.amsvistara.db.table.master.convert.AssetTagTypeConverter;
import com.ams.amsvistara.db.table.master.convert.AssetTypeConverter;
import com.ams.amsvistara.db.table.master.convert.FinancialYearConverter;
import com.ams.amsvistara.db.table.master.convert.LocationConverter;
import com.ams.amsvistara.db.table.master.convert.PvModelConverter;
import com.ams.amsvistara.db.table.master.convert.SubLocationConverter;
import com.ams.amsvistara.db.table.master.convert.TagModelConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "master_data")
public class MasterData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    @Expose
    @SerializedName("ResponseMessage")
    public String ResponseMessage;
    @Expose
    @SerializedName("ResponseStatus")
    public String ResponseStatus;
    @Expose
    @SerializedName("TAGModels")
    @TypeConverters(TagModelConverter.class)
    public List<TagModels> TAGModels= new ArrayList<>();
    @Expose
    @SerializedName("PVModels")
    @TypeConverters(PvModelConverter.class)
    public List<PvModels> PVModels = new ArrayList<>();
    @Expose
    @SerializedName("AssetTagTypeMasters")
    @TypeConverters(AssetTagTypeConverter.class)
    public List<AssetTagTypeMasters> AssetTagTypeMasters=new ArrayList<>();
    @Expose
    @SerializedName("AssetSurfaceMasters")
    @TypeConverters(AssetSurfaceConverter.class)
    public List<AssetSurfaceMasters> AssetSurfaceMasters=new ArrayList<>();
    @Expose
    @SerializedName("AssetTypeMasters")
    @TypeConverters(AssetTypeConverter.class)
    public List<AssetTypeMasters> AssetTypeMasters=new ArrayList<>();
    @Expose
    @SerializedName("AssetConditionMasters")
    @TypeConverters(AssetConditionConverter.class)
    public List<AssetConditionMasters> AssetConditionMasters=new ArrayList<>();
    @Expose
    @SerializedName("AssetSubCategoryMasters")
    @TypeConverters(AssetSubCategoryConverter.class)
    public List<AssetSubCategoryMasters> AssetSubCategoryMasters =new ArrayList<>();
    @Expose
    @SerializedName("AssetCategoryMasters")
    @TypeConverters(AssetCategoryConverter.class)
    public List<AssetCategoryMasters> AssetCategoryMasters=new ArrayList<>();
    @Expose
    @SerializedName("SubLocationMasters")
    @TypeConverters(SubLocationConverter.class)
    public List<SubLocationMasters> SubLocationMasters=new ArrayList<>();
    @Expose
    @SerializedName("LocationMasters")
    @TypeConverters(LocationConverter.class)
    public List<LocationMasters> LocationMasters =new ArrayList<>();
    @Expose
    @SerializedName("FinancialYears")
    @TypeConverters(FinancialYearConverter.class)
    public List<FinancialYears> FinancialYears=new ArrayList<>();

    public MasterData() {
    }
}
