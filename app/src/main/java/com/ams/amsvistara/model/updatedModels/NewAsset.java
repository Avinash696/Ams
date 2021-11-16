
package com.ams.amsvistara.model.updatedModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewAsset {

    @SerializedName("FinancialYearID")
    @Expose
    private Integer financialYearID;
    @SerializedName("FinancialYear")
    @Expose
    private String financialYear;

    public NewAsset() {
    }

    @SerializedName("LocationID")
    @Expose
    private Integer locationID;
    @SerializedName("LocationName")
    @Expose
    private String locationName;
    @SerializedName("SubLocationID")
    @Expose
    private Integer subLocationID;
    @SerializedName("SubLocationName")
    @Expose
    private String subLocationName;
    @SerializedName("AssetCategoryID")
    @Expose
    private Integer assetCategoryID;
    @SerializedName("AssetCategoryName")
    @Expose
    private String assetCategoryName;
    @SerializedName("AssetSubCategoryID")
    @Expose
    private Integer assetSubCategoryID;
    @SerializedName("AssetSubcategoryName")
    @Expose
    private String assetSubcategoryName;
    @SerializedName("LocationCodeERP")
    @Expose
    private String locationCodeERP;
    @SerializedName("LocationNameERP")
    @Expose
    private String locationNameERP;
    @SerializedName("SubLocationCodeERP")
    @Expose
    private String subLocationCodeERP;
    @SerializedName("SubLocationNameERP")
    @Expose
    private String subLocationNameERP;
    @SerializedName("Department")
    @Expose
    private String department;
    @SerializedName("Custodian")
    @Expose
    private String custodian;
    @SerializedName("AssetCode")
    @Expose
    private String assetCode;
    @SerializedName("SerialNumber")
    @Expose
    private String serialNumber;
    @SerializedName("AssetNumber")
    @Expose
    private String assetNumber;
    @SerializedName("AssetNumberOld")
    @Expose
    private String assetNumberOld;
    @SerializedName("AssetName")
    @Expose
    private String assetName;
    @SerializedName("AssetMake")
    @Expose
    private String assetMake;
    @SerializedName("AssetModel")
    @Expose
    private String assetModel;
    @SerializedName("AssetDescription")
    @Expose
    private String assetDescription;
    @SerializedName("AssetConditionID")
    @Expose
    private Integer assetConditionID;
    @SerializedName("AssetCondition")
    @Expose
    private String assetCondition;
    @SerializedName("DepreciationKey")
    @Expose
    private String depreciationKey;
    @SerializedName("Plant")
    @Expose
    private String plant;
    @SerializedName("Vendor")
    @Expose
    private String vendor;
    @SerializedName("EvaluationGroup")
    @Expose
    private String evaluationGroup;
    @SerializedName("BusinessArea")
    @Expose
    private String businessArea;
    @SerializedName("SanitizationAssetTypeID")
    @Expose
    private Integer sanitizationAssetTypeID;
    @SerializedName("SanitizationAssetTypeName")
    @Expose
    private String sanitizationAssetTypeName;
    @SerializedName("SanitizationAssetName")
    @Expose
    private String sanitizationAssetName;
    @SerializedName("SanitizationAssetSurfaceID")
    @Expose
    private Integer sanitizationAssetSurfaceID;
    @SerializedName("SanitizationAssetSurfaceName")
    @Expose
    private String sanitizationAssetSurfaceName;
    @SerializedName("SanitizationTagTypeID")
    @Expose
    private Integer sanitizationTagTypeID;
    @SerializedName("SanitizationTagTypeName")
    @Expose
    private String sanitizationTagTypeName;
    @SerializedName("SanitizationTempTagNo")
    @Expose
    private String sanitizationTempTagNo;
    @SerializedName("SpocName")
    @Expose
    private String spocName;
    @SerializedName("Remarks1")
    @Expose
    private String remarks1;
    @SerializedName("Remarks2")
    @Expose
    private String remarks2;
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;
    @SerializedName("CreatedByName")
    @Expose
    private String createdByName;
    @SerializedName("UpdateBy")
    @Expose
    private Integer updateBy;
    @SerializedName("UpdateByName")
    @Expose
    private String updateByName;
    @SerializedName("UpdateRemarks")
    @Expose
    private String updateRemarks;
    @SerializedName("ParentId")
    @Expose
    private Integer parentId;
    @SerializedName("AssignId")
    @Expose
    private Integer assignId;
    @SerializedName("ParentAsset")
    @Expose
    private String parentAsset;
    @SerializedName("AssignedTo")
    @Expose
    private String assignedTo;
    @SerializedName("VendorInvoiceImage")
    @Expose
    private String vendorInvoiceImage;
    @SerializedName("FileExtension")
    @Expose
    private String fileExtension;
    @SerializedName("PreviousVendorInvoiceImage")
    @Expose
    private String previousVendorInvoiceImage;
    @SerializedName("ThirdPartyAsset")
    @Expose
    private String thirdPartyAsset;
    @SerializedName("UtilizationType")
    @Expose
    private String utilizationType;

    public NewAsset(Integer financialYearID, String financialYear, Integer locationID, String locationName, Integer subLocationID,
                    String subLocationName, Integer assetCategoryID, String assetCategoryName, Integer assetSubCategoryID,
                    String assetSubcategoryName, String locationCodeERP, String locationNameERP, String subLocationCodeERP, String subLocationNameERP,
                    String department, String custodian, String assetCode, String serialNumber, String assetNumber, String assetNumberOld,
                    String assetName, String assetMake, String assetModel, String assetDescription, Integer assetConditionID, String assetCondition,
                    String depreciationKey, String plant, String vendor, String evaluationGroup, String businessArea, Integer sanitizationAssetTypeID,
                    String sanitizationAssetTypeName, String sanitizationAssetName, Integer sanitizationAssetSurfaceID, String sanitizationAssetSurfaceName,
                    Integer sanitizationTagTypeID, String sanitizationTagTypeName, String sanitizationTempTagNo, String spocName,
                    String remarks1, String remarks2, Integer createdBy, String createdByName, Integer updateBy, String updateByName,
                    String updateRemarks, Integer parentId, Integer assignId, String parentAsset, String assignedTo,
                    String vendorInvoiceImage, String fileExtension, String previousVendorInvoiceImage, String thirdPartyAsset, String utilizationType) {
        this.financialYearID = financialYearID;
        this.financialYear = financialYear;
        this.locationID = locationID;
        this.locationName = locationName;
        this.subLocationID = subLocationID;
        this.subLocationName = subLocationName;
        this.assetCategoryID = assetCategoryID;
        this.assetCategoryName = assetCategoryName;
        this.assetSubCategoryID = assetSubCategoryID;
        this.assetSubcategoryName = assetSubcategoryName;
        this.locationCodeERP = locationCodeERP;
        this.locationNameERP = locationNameERP;
        this.subLocationCodeERP = subLocationCodeERP;
        this.subLocationNameERP = subLocationNameERP;
        this.department = department;
        this.custodian = custodian;
        this.assetCode = assetCode;
        this.serialNumber = serialNumber;
        this.assetNumber = assetNumber;
        this.assetNumberOld = assetNumberOld;
        this.assetName = assetName;
        this.assetMake = assetMake;
        this.assetModel = assetModel;
        this.assetDescription = assetDescription;
        this.assetConditionID = assetConditionID;
        this.assetCondition = assetCondition;
        this.depreciationKey = depreciationKey;
        this.plant = plant;
        this.vendor = vendor;
        this.evaluationGroup = evaluationGroup;
        this.businessArea = businessArea;
        this.sanitizationAssetTypeID = sanitizationAssetTypeID;
        this.sanitizationAssetTypeName = sanitizationAssetTypeName;
        this.sanitizationAssetName = sanitizationAssetName;
        this.sanitizationAssetSurfaceID = sanitizationAssetSurfaceID;
        this.sanitizationAssetSurfaceName = sanitizationAssetSurfaceName;
        this.sanitizationTagTypeID = sanitizationTagTypeID;
        this.sanitizationTagTypeName = sanitizationTagTypeName;
        this.sanitizationTempTagNo = sanitizationTempTagNo;
        this.spocName = spocName;
        this.remarks1 = remarks1;
        this.remarks2 = remarks2;
        this.createdBy = createdBy;
        this.createdByName = createdByName;
        this.updateBy = updateBy;
        this.updateByName = updateByName;
        this.updateRemarks = updateRemarks;
        this.parentId = parentId;
        this.assignId = assignId;
        this.parentAsset = parentAsset;
        this.assignedTo = assignedTo;
        this.vendorInvoiceImage = vendorInvoiceImage;
        this.fileExtension = fileExtension;
        this.previousVendorInvoiceImage = previousVendorInvoiceImage;
        this.thirdPartyAsset = thirdPartyAsset;
        this.utilizationType = utilizationType;
    }

    public Integer getFinancialYearID() {
        return financialYearID;
    }

    public void setFinancialYearID(Integer financialYearID) {
        this.financialYearID = financialYearID;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getSubLocationID() {
        return subLocationID;
    }

    public void setSubLocationID(Integer subLocationID) {
        this.subLocationID = subLocationID;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }

    public Integer getAssetCategoryID() {
        return assetCategoryID;
    }

    public void setAssetCategoryID(Integer assetCategoryID) {
        this.assetCategoryID = assetCategoryID;
    }

    public String getAssetCategoryName() {
        return assetCategoryName;
    }

    public void setAssetCategoryName(String assetCategoryName) {
        this.assetCategoryName = assetCategoryName;
    }

    public Integer getAssetSubCategoryID() {
        return assetSubCategoryID;
    }

    public void setAssetSubCategoryID(Integer assetSubCategoryID) {
        this.assetSubCategoryID = assetSubCategoryID;
    }

    public String getAssetSubcategoryName() {
        return assetSubcategoryName;
    }

    public void setAssetSubcategoryName(String assetSubcategoryName) {
        this.assetSubcategoryName = assetSubcategoryName;
    }

    public String getLocationCodeERP() {
        return locationCodeERP;
    }

    public void setLocationCodeERP(String locationCodeERP) {
        this.locationCodeERP = locationCodeERP;
    }

    public String getLocationNameERP() {
        return locationNameERP;
    }

    public void setLocationNameERP(String locationNameERP) {
        this.locationNameERP = locationNameERP;
    }

    public String getSubLocationCodeERP() {
        return subLocationCodeERP;
    }

    public void setSubLocationCodeERP(String subLocationCodeERP) {
        this.subLocationCodeERP = subLocationCodeERP;
    }

    public String getSubLocationNameERP() {
        return subLocationNameERP;
    }

    public void setSubLocationNameERP(String subLocationNameERP) {
        this.subLocationNameERP = subLocationNameERP;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getAssetNumberOld() {
        return assetNumberOld;
    }

    public void setAssetNumberOld(String assetNumberOld) {
        this.assetNumberOld = assetNumberOld;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetMake() {
        return assetMake;
    }

    public void setAssetMake(String assetMake) {
        this.assetMake = assetMake;
    }

    public String getAssetModel() {
        return assetModel;
    }

    public void setAssetModel(String assetModel) {
        this.assetModel = assetModel;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public Integer getAssetConditionID() {
        return assetConditionID;
    }

    public void setAssetConditionID(Integer assetConditionID) {
        this.assetConditionID = assetConditionID;
    }

    public String getAssetCondition() {
        return assetCondition;
    }

    public void setAssetCondition(String assetCondition) {
        this.assetCondition = assetCondition;
    }

    public String getDepreciationKey() {
        return depreciationKey;
    }

    public void setDepreciationKey(String depreciationKey) {
        this.depreciationKey = depreciationKey;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getEvaluationGroup() {
        return evaluationGroup;
    }

    public void setEvaluationGroup(String evaluationGroup) {
        this.evaluationGroup = evaluationGroup;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public Integer getSanitizationAssetTypeID() {
        return sanitizationAssetTypeID;
    }

    public void setSanitizationAssetTypeID(Integer sanitizationAssetTypeID) {
        this.sanitizationAssetTypeID = sanitizationAssetTypeID;
    }

    public String getSanitizationAssetTypeName() {
        return sanitizationAssetTypeName;
    }

    public void setSanitizationAssetTypeName(String sanitizationAssetTypeName) {
        this.sanitizationAssetTypeName = sanitizationAssetTypeName;
    }

    public String getSanitizationAssetName() {
        return sanitizationAssetName;
    }

    public void setSanitizationAssetName(String sanitizationAssetName) {
        this.sanitizationAssetName = sanitizationAssetName;
    }

    public Integer getSanitizationAssetSurfaceID() {
        return sanitizationAssetSurfaceID;
    }

    public void setSanitizationAssetSurfaceID(Integer sanitizationAssetSurfaceID) {
        this.sanitizationAssetSurfaceID = sanitizationAssetSurfaceID;
    }

    public String getSanitizationAssetSurfaceName() {
        return sanitizationAssetSurfaceName;
    }

    public void setSanitizationAssetSurfaceName(String sanitizationAssetSurfaceName) {
        this.sanitizationAssetSurfaceName = sanitizationAssetSurfaceName;
    }

    public Integer getSanitizationTagTypeID() {
        return sanitizationTagTypeID;
    }

    public void setSanitizationTagTypeID(Integer sanitizationTagTypeID) {
        this.sanitizationTagTypeID = sanitizationTagTypeID;
    }

    public String getSanitizationTagTypeName() {
        return sanitizationTagTypeName;
    }

    public void setSanitizationTagTypeName(String sanitizationTagTypeName) {
        this.sanitizationTagTypeName = sanitizationTagTypeName;
    }

    public String getSanitizationTempTagNo() {
        return sanitizationTempTagNo;
    }

    public void setSanitizationTempTagNo(String sanitizationTempTagNo) {
        this.sanitizationTempTagNo = sanitizationTempTagNo;
    }

    public String getSpocName() {
        return spocName;
    }

    public void setSpocName(String spocName) {
        this.spocName = spocName;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }

    public String getRemarks2() {
        return remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public String getUpdateRemarks() {
        return updateRemarks;
    }

    public void setUpdateRemarks(String updateRemarks) {
        this.updateRemarks = updateRemarks;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAssignId() {
        return assignId;
    }

    public void setAssignId(Integer assignId) {
        this.assignId = assignId;
    }

    public String getParentAsset() {
        return parentAsset;
    }

    public void setParentAsset(String parentAsset) {
        this.parentAsset = parentAsset;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getVendorInvoiceImage() {
        return vendorInvoiceImage;
    }

    public void setVendorInvoiceImage(String vendorInvoiceImage) {
        this.vendorInvoiceImage = vendorInvoiceImage;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getPreviousVendorInvoiceImage() {
        return previousVendorInvoiceImage;
    }

    public void setPreviousVendorInvoiceImage(String previousVendorInvoiceImage) {
        this.previousVendorInvoiceImage = previousVendorInvoiceImage;
    }

    public String getThirdPartyAsset() {
        return thirdPartyAsset;
    }

    public void setThirdPartyAsset(String thirdPartyAsset) {
        this.thirdPartyAsset = thirdPartyAsset;
    }

    public String getUtilizationType() {
        return utilizationType;
    }

    public void setUtilizationType(String utilizationType) {
        this.utilizationType = utilizationType;
    }

}
