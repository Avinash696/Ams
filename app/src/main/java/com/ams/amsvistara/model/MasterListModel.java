package com.ams.amsvistara.model;

import androidx.lifecycle.MutableLiveData;

import com.ams.amsvistara.viewmodel.AllAssetsListViewModel;

public class MasterListModel {
    public String financialYear;
    public String locationName;
    public String subLocationName;
    public String assetCategoryName;
    public String assetSubCategoryName;
    public String thirdPartyAsset;
    public String assetCode;
    public String serialNumber;
    public String assetNumber;
    public String assetNumberOld;
    public String assetName;
    public String make;
    public String model;
    public String description;

    public MasterListModel() {
    }

    public MasterListModel(String financialYear, String thirdPartyAsset) {
        this.financialYear = financialYear;
        this.thirdPartyAsset = thirdPartyAsset;
    }

    public MasterListModel(String financialYear, String locationName, String subLocationName, String assetCategoryName,
                           String assetSubCategoryName, String thirdPartyAsset, String assetCode, String serialNumber, String assetNumber,
                           String assetNumberOld, String assetName, String make, String model, String description, String locationCodeERP,
                           String locationNameERP, String subLocationCodeERP, String subLocationNameERP, String department, String plant, String venderName,
                           String usefulLife, String assetCondition, String isInUse, String capitalizaionDate, String acquisitionDate, String depreciationStartDate,
                           String depreciationKey, String evaluationGroup, String businessArea, String quantity, String openingGrossValue, String acquisitionValue,
                           String cumulativeAcquistionValue, String transtionAcquistionValue, String writeUpValue, String writeOffValue, String accumulatedDeprection,
                           String plannedDepreciationValue, String tranitionAccumulatedDepreciation, String currentYearDepreciation, String openingWDV, String closingWDV,
                           String closingGrossValue, String assetType, String assetSurfaceName, String tagTypeName, String tempTagNumber, String spocName, String remark,
                           String endOfLife, String parenChild, String assignTo, String vendorAssets, String utilizaationLevel, String companyCode, String costProfitCenter,
                           String costProfitCenterDesc, String subNumber, String farQuantity, String unitOfMeasurement, String poNumber, String documentNumber, String WBSelement,
                           String projectCode, String projectDesc, String assetStatus, String farSerialNumber, String decapDate, String currency, String currentAPC, String netBookValue,
                           String taggability, String epc, String geoLocation, String pvRemarks, String clientRemarks, String pvStatus) {
        this.financialYear = financialYear;
        this.locationName = locationName;
        this.subLocationName = subLocationName;
        this.assetCategoryName = assetCategoryName;
        this.assetSubCategoryName = assetSubCategoryName;
        this.thirdPartyAsset = thirdPartyAsset;
        this.assetCode = assetCode;
        this.serialNumber = serialNumber;
        this.assetNumber = assetNumber;
        this.assetNumberOld = assetNumberOld;
        this.assetName = assetName;
        this.make = make;
        this.model = model;
        this.description = description;
        this.locationCodeERP = locationCodeERP;
        this.locationNameERP = locationNameERP;
        this.subLocationCodeERP = subLocationCodeERP;
        this.subLocationNameERP = subLocationNameERP;
        this.department = department;
        this.plant = plant;
        this.venderName = venderName;
        this.usefulLife = usefulLife;
        this.assetCondition = assetCondition;
        this.isInUse = isInUse;
        this.capitalizaionDate = capitalizaionDate;
        this.acquisitionDate = acquisitionDate;
        this.depreciationStartDate = depreciationStartDate;
        this.depreciationKey = depreciationKey;
        this.evaluationGroup = evaluationGroup;
        this.businessArea = businessArea;
        this.quantity = quantity;
        this.openingGrossValue = openingGrossValue;
        this.acquisitionValue = acquisitionValue;
        this.cumulativeAcquistionValue = cumulativeAcquistionValue;
        this.transtionAcquistionValue = transtionAcquistionValue;
        this.writeUpValue = writeUpValue;
        this.writeOffValue = writeOffValue;
        this.accumulatedDeprection = accumulatedDeprection;
        this.plannedDepreciationValue = plannedDepreciationValue;
        this.tranitionAccumulatedDepreciation = tranitionAccumulatedDepreciation;
        this.currentYearDepreciation = currentYearDepreciation;
        this.openingWDV = openingWDV;
        this.closingWDV = closingWDV;
        this.closingGrossValue = closingGrossValue;
        this.assetType = assetType;
        this.assetSurfaceName = assetSurfaceName;
        this.tagTypeName = tagTypeName;
        this.tempTagNumber = tempTagNumber;
        this.spocName = spocName;
        this.remark = remark;
        this.endOfLife = endOfLife;
        this.parenChild = parenChild;
        this.assignTo = assignTo;
        this.vendorAssets = vendorAssets;
        this.utilizaationLevel = utilizaationLevel;
        this.companyCode = companyCode;
        this.costProfitCenter = costProfitCenter;
        this.costProfitCenterDesc = costProfitCenterDesc;
        this.subNumber = subNumber;
        this.farQuantity = farQuantity;
        this.unitOfMeasurement = unitOfMeasurement;
        this.poNumber = poNumber;
        this.documentNumber = documentNumber;
        this.WBSelement = WBSelement;
        this.projectCode = projectCode;
        this.projectDesc = projectDesc;
        this.assetStatus = assetStatus;
        this.farSerialNumber = farSerialNumber;
        this.decapDate = decapDate;
        this.currency = currency;
        this.currentAPC = currentAPC;
        this.netBookValue = netBookValue;
        this.taggability = taggability;
        this.epc = epc;
        this.geoLocation = geoLocation;
        this.pvRemarks = pvRemarks;
        this.clientRemarks = clientRemarks;
        this.pvStatus = pvStatus;
    }

    public String locationCodeERP;
    public String locationNameERP;
    public String subLocationCodeERP;
    public String subLocationNameERP;
    public String department;
    public String plant;
    public String venderName;
    public String usefulLife;
    public String assetCondition;
    public String isInUse;
    public String capitalizaionDate;
    public String acquisitionDate;
    public String depreciationStartDate;
    public String depreciationKey;
    public String evaluationGroup;
    public String businessArea;
    public String quantity;
    public String openingGrossValue;
    public String acquisitionValue;
    public String cumulativeAcquistionValue;
    public String transtionAcquistionValue;
    public String writeUpValue;
    public String writeOffValue;
    public String accumulatedDeprection;
    public String plannedDepreciationValue;
    public String tranitionAccumulatedDepreciation;
    public String currentYearDepreciation;
    public String openingWDV;
    public String closingWDV;
    public String closingGrossValue;
    public String assetType;
    public String assetSurfaceName;
    public String tagTypeName;
    public String tempTagNumber;
    public String spocName;
    public String remark;
    public String endOfLife;
    public String parenChild;
    public String assignTo;
    public String vendorAssets;
    public String utilizaationLevel;
    public String companyCode;
    public String costProfitCenter;
    public String costProfitCenterDesc;
    public String subNumber;
    public String farQuantity;
    public String unitOfMeasurement;
    public String poNumber;
    public String documentNumber;
    public String WBSelement;
    public String projectCode;
    public String projectDesc;
    public String assetStatus;
    public String farSerialNumber;
    public String decapDate;
    public String currency;
    public String currentAPC;
    public String netBookValue;
    public String taggability;
    public String epc;
    public String geoLocation;
    public String pvRemarks;
    public String clientRemarks;
    public String pvStatus;

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }

    public String getAssetCategoryName() {
        return assetCategoryName;
    }

    public void setAssetCategoryName(String assetCategoryName) {
        this.assetCategoryName = assetCategoryName;
    }

    public String getAssetSubCategoryName() {
        return assetSubCategoryName;
    }

    public void setAssetSubCategoryName(String assetSubCategoryName) {
        this.assetSubCategoryName = assetSubCategoryName;
    }

    public String getThirdPartyAsset() {
        return thirdPartyAsset;
    }

    public void setThirdPartyAsset(String thirdPartyAsset) {
        this.thirdPartyAsset = thirdPartyAsset;
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

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
    }

    public String getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(String usefulLife) {
        this.usefulLife = usefulLife;
    }

    public String getAssetCondition() {
        return assetCondition;
    }

    public void setAssetCondition(String assetCondition) {
        this.assetCondition = assetCondition;
    }

    public String getIsInUse() {
        return isInUse;
    }

    public void setIsInUse(String isInUse) {
        this.isInUse = isInUse;
    }

    public String getCapitalizaionDate() {
        return capitalizaionDate;
    }

    public void setCapitalizaionDate(String capitalizaionDate) {
        this.capitalizaionDate = capitalizaionDate;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getDepreciationStartDate() {
        return depreciationStartDate;
    }

    public void setDepreciationStartDate(String depreciationStartDate) {
        this.depreciationStartDate = depreciationStartDate;
    }

    public String getDepreciationKey() {
        return depreciationKey;
    }

    public void setDepreciationKey(String depreciationKey) {
        this.depreciationKey = depreciationKey;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOpeningGrossValue() {
        return openingGrossValue;
    }

    public void setOpeningGrossValue(String openingGrossValue) {
        this.openingGrossValue = openingGrossValue;
    }

    public String getAcquisitionValue() {
        return acquisitionValue;
    }

    public void setAcquisitionValue(String acquisitionValue) {
        this.acquisitionValue = acquisitionValue;
    }

    public String getCumulativeAcquistionValue() {
        return cumulativeAcquistionValue;
    }

    public void setCumulativeAcquistionValue(String cumulativeAcquistionValue) {
        this.cumulativeAcquistionValue = cumulativeAcquistionValue;
    }

    public String getTranstionAcquistionValue() {
        return transtionAcquistionValue;
    }

    public void setTranstionAcquistionValue(String transtionAcquistionValue) {
        this.transtionAcquistionValue = transtionAcquistionValue;
    }

    public String getWriteUpValue() {
        return writeUpValue;
    }

    public void setWriteUpValue(String writeUpValue) {
        this.writeUpValue = writeUpValue;
    }

    public String getWriteOffValue() {
        return writeOffValue;
    }

    public void setWriteOffValue(String writeOffValue) {
        this.writeOffValue = writeOffValue;
    }

    public String getAccumulatedDeprection() {
        return accumulatedDeprection;
    }

    public void setAccumulatedDeprection(String accumulatedDeprection) {
        this.accumulatedDeprection = accumulatedDeprection;
    }

    public String getPlannedDepreciationValue() {
        return plannedDepreciationValue;
    }

    public void setPlannedDepreciationValue(String plannedDepreciationValue) {
        this.plannedDepreciationValue = plannedDepreciationValue;
    }

    public String getTranitionAccumulatedDepreciation() {
        return tranitionAccumulatedDepreciation;
    }

    public void setTranitionAccumulatedDepreciation(String tranitionAccumulatedDepreciation) {
        this.tranitionAccumulatedDepreciation = tranitionAccumulatedDepreciation;
    }

    public String getCurrentYearDepreciation() {
        return currentYearDepreciation;
    }

    public void setCurrentYearDepreciation(String currentYearDepreciation) {
        this.currentYearDepreciation = currentYearDepreciation;
    }

    public String getOpeningWDV() {
        return openingWDV;
    }

    public void setOpeningWDV(String openingWDV) {
        this.openingWDV = openingWDV;
    }

    public String getClosingWDV() {
        return closingWDV;
    }

    public void setClosingWDV(String closingWDV) {
        this.closingWDV = closingWDV;
    }

    public String getClosingGrossValue() {
        return closingGrossValue;
    }

    public void setClosingGrossValue(String closingGrossValue) {
        this.closingGrossValue = closingGrossValue;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetSurfaceName() {
        return assetSurfaceName;
    }

    public void setAssetSurfaceName(String assetSurfaceName) {
        this.assetSurfaceName = assetSurfaceName;
    }

    public String getTagTypeName() {
        return tagTypeName;
    }

    public void setTagTypeName(String tagTypeName) {
        this.tagTypeName = tagTypeName;
    }

    public String getTempTagNumber() {
        return tempTagNumber;
    }

    public void setTempTagNumber(String tempTagNumber) {
        this.tempTagNumber = tempTagNumber;
    }

    public String getSpocName() {
        return spocName;
    }

    public void setSpocName(String spocName) {
        this.spocName = spocName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEndOfLife() {
        return endOfLife;
    }

    public void setEndOfLife(String endOfLife) {
        this.endOfLife = endOfLife;
    }

    public String getParenChild() {
        return parenChild;
    }

    public void setParenChild(String parenChild) {
        this.parenChild = parenChild;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getVendorAssets() {
        return vendorAssets;
    }

    public void setVendorAssets(String vendorAssets) {
        this.vendorAssets = vendorAssets;
    }

    public String getUtilizaationLevel() {
        return utilizaationLevel;
    }

    public void setUtilizaationLevel(String utilizaationLevel) {
        this.utilizaationLevel = utilizaationLevel;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCostProfitCenter() {
        return costProfitCenter;
    }

    public void setCostProfitCenter(String costProfitCenter) {
        this.costProfitCenter = costProfitCenter;
    }

    public String getCostProfitCenterDesc() {
        return costProfitCenterDesc;
    }

    public void setCostProfitCenterDesc(String costProfitCenterDesc) {
        this.costProfitCenterDesc = costProfitCenterDesc;
    }

    public String getSubNumber() {
        return subNumber;
    }

    public void setSubNumber(String subNumber) {
        this.subNumber = subNumber;
    }

    public String getFarQuantity() {
        return farQuantity;
    }

    public void setFarQuantity(String farQuantity) {
        this.farQuantity = farQuantity;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getWBSelement() {
        return WBSelement;
    }

    public void setWBSelement(String WBSelement) {
        this.WBSelement = WBSelement;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    public String getFarSerialNumber() {
        return farSerialNumber;
    }

    public void setFarSerialNumber(String farSerialNumber) {
        this.farSerialNumber = farSerialNumber;
    }

    public String getDecapDate() {
        return decapDate;
    }

    public void setDecapDate(String decapDate) {
        this.decapDate = decapDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrentAPC() {
        return currentAPC;
    }

    public void setCurrentAPC(String currentAPC) {
        this.currentAPC = currentAPC;
    }

    public String getNetBookValue() {
        return netBookValue;
    }

    public void setNetBookValue(String netBookValue) {
        this.netBookValue = netBookValue;
    }

    public String getTaggability() {
        return taggability;
    }

    public void setTaggability(String taggability) {
        this.taggability = taggability;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getPvRemarks() {
        return pvRemarks;
    }

    public void setPvRemarks(String pvRemarks) {
        this.pvRemarks = pvRemarks;
    }

    public String getClientRemarks() {
        return clientRemarks;
    }

    public void setClientRemarks(String clientRemarks) {
        this.clientRemarks = clientRemarks;
    }

    public String getPvStatus() {
        return pvStatus;
    }

    public void setPvStatus(String pvStatus) {
        this.pvStatus = pvStatus;
    }
}
