package com.ams.amsvistara.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.model.MasterListModel;
import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.NewAsset;
import com.ams.amsvistara.model.updatedModels.NewAssetResponce;
import com.ams.amsvistara.model.updatedModels.RestApiModel;
import com.ams.amsvistara.ws.repo.AddNewAssetRepo;
import com.ams.amsvistara.ws.repo.AllAssetListRepo;
import com.ams.amsvistara.ws.repo.IAddNewAssetRepo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class MasterDetailViewModel extends ViewModel {
    public MutableLiveData<byte[]> imageOne = new MutableLiveData<>();
    public MutableLiveData<byte[]> imageTwo = new MutableLiveData<>();
    public MutableLiveData<byte[]> imageThree = new MutableLiveData<>();
    public MutableLiveData<byte[]> imageFour = new MutableLiveData<>();
    public MutableLiveData<byte[]> imageFive = new MutableLiveData<>();

    public MutableLiveData<String> thirdPartyAsset = new MutableLiveData<>();
    public MutableLiveData<String> AssetCode = new MutableLiveData<>();
    public MutableLiveData<String> AssetNumber = new MutableLiveData<>();
    public MutableLiveData<String> OldAssetNumber = new MutableLiveData<>();

    public MutableLiveData<String> AssetName = new MutableLiveData<>();
    public MutableLiveData<String> Description = new MutableLiveData<>();
    public MutableLiveData<String> LocationCodeERP = new MutableLiveData<>();
    public MutableLiveData<String> LocationNameERP = new MutableLiveData<>();
    public MutableLiveData<String> SubLocationCodeERP = new MutableLiveData<>();
    public MutableLiveData<String> SubLocationName = new MutableLiveData<>();
    public MutableLiveData<String> Department = new MutableLiveData<>();
    public MutableLiveData<String> Custodian = new MutableLiveData<>();
    public MutableLiveData<String> Plant = new MutableLiveData<>();
    public MutableLiveData<String> VenderName = new MutableLiveData<>();
    public MutableLiveData<String> UserfulLife = new MutableLiveData<>();
    public MutableLiveData<String> CapDate = new MutableLiveData<>();
    public MutableLiveData<String> AcquisitionDate = new MutableLiveData<>();
    public MutableLiveData<String> DepreciationStartDate = new MutableLiveData<>();
    public MutableLiveData<String> DepreciationKey = new MutableLiveData<>();
    public MutableLiveData<String> EvaluationGroup = new MutableLiveData<>();
    public MutableLiveData<String> BussinessArea = new MutableLiveData<>();
    public MutableLiveData<String> Quantity = new MutableLiveData<>();
    public MutableLiveData<String> OpeningGrossValue = new MutableLiveData<>();
    public MutableLiveData<String> AcquisitionValue = new MutableLiveData<>();
    public MutableLiveData<String> CumulativeAcquistionValue = new MutableLiveData<>();
    public MutableLiveData<String> TranstionAcquistionValue = new MutableLiveData<>();
    public MutableLiveData<String> WriteupValue = new MutableLiveData<>();
    public MutableLiveData<String> WriteoffValue = new MutableLiveData<>();
    public MutableLiveData<String> AccumulatedDeprection = new MutableLiveData<>();
    public MutableLiveData<String> PlannedDepreciationValue = new MutableLiveData<>();
    public MutableLiveData<String> TranstionAccumulatedDepreciation = new MutableLiveData<>();
    public MutableLiveData<String> CurrentYearDepreciation = new MutableLiveData<>();
    public MutableLiveData<String> OpeningWDV = new MutableLiveData<>();
    public MutableLiveData<String> ClosingWDV = new MutableLiveData<>();
    public MutableLiveData<String> ClosingGrossValue = new MutableLiveData<>();
    public MutableLiveData<String> CompanyCode = new MutableLiveData<>();
    public MutableLiveData<String> CostProfitCenter = new MutableLiveData<>();
    public MutableLiveData<String> CostProfitDesc = new MutableLiveData<>();
    public MutableLiveData<String> SubNumber = new MutableLiveData<>();
    public MutableLiveData<String> FARQuantity = new MutableLiveData<>();
    public MutableLiveData<String> PONumber = new MutableLiveData<>();
    public MutableLiveData<String> EndOflife = new MutableLiveData<>();
    public MutableLiveData<String> UnitOfMeasurement = new MutableLiveData<>();
    public MutableLiveData<String> DocumentNumber = new MutableLiveData<>();
    public MutableLiveData<String> WBSElement = new MutableLiveData<>();
    public MutableLiveData<String> ProjectCode = new MutableLiveData<>();
    public MutableLiveData<String> ProjectDesc = new MutableLiveData<>();
    public MutableLiveData<String> AssetStatus = new MutableLiveData<>();
    public MutableLiveData<String> FARSerialNumber = new MutableLiveData<>();
    public MutableLiveData<String> DecapDate = new MutableLiveData<>();
    public MutableLiveData<String> Currency = new MutableLiveData<>();
    public MutableLiveData<String> CurrentAPC = new MutableLiveData<>();
    public MutableLiveData<String> NetBookValue = new MutableLiveData<>();
    public MutableLiveData<String> Taggability = new MutableLiveData<>();
    public MutableLiveData<String> EPC = new MutableLiveData<>();
    public MutableLiveData<String> GeoLocation = new MutableLiveData<>();
    public MutableLiveData<String> PVRemarks = new MutableLiveData<>();
    public MutableLiveData<String> ClientRemarks = new MutableLiveData<>();
    public MutableLiveData<String> PVStatus = new MutableLiveData<>();
//    public MutableLiveData<Integer> AssignId = new MutableLiveData<>();
    public MutableLiveData<String> AssignId = new MutableLiveData<>();

    public MutableLiveData<String> ImageCount = new MutableLiveData<>();
    public MutableLiveData<String> AssetCondition = new MutableLiveData<>();
    public MutableLiveData<String> AssetNameText = new MutableLiveData<>();
    public MutableLiveData<String> MakeTxt = new MutableLiveData<>();
    public MutableLiveData<String> ModelTxt = new MutableLiveData<>();
    public MutableLiveData<String> SerialNo = new MutableLiveData<>();
    public MutableLiveData<String> Location = new MutableLiveData<>();
    public MutableLiveData<String> SubLocation = new MutableLiveData<>();
    public MutableLiveData<String> SPOCName = new MutableLiveData<>();
    public MutableLiveData<String> PVName = new MutableLiveData<>();
//    public MutableLiveData<String> ClientRemarks = new MutableLiveData<>();
    public MutableLiveData<Boolean> saveFlag = new MutableLiveData<>();
    public MutableLiveData<String> TemporaryTagNo = new MutableLiveData<>();
    //list model
    public MutableLiveData<AssetMaster> assetMasterMutableLiveData =new MutableLiveData<>();
    //spinner field
    public MutableLiveData<String> spAdFinanceYear=new MutableLiveData<>();
    public MutableLiveData<String> spAdCategory=new MutableLiveData<>();
    public MutableLiveData<String> spAdSubCategory=new MutableLiveData<>();
    public MutableLiveData<String> spAdLocation=new MutableLiveData<>();
    public MutableLiveData<String> spAdSubLocation=new MutableLiveData<>();
    public MutableLiveData<String> spAdAssetCondition=new MutableLiveData<>();
    public MutableLiveData<String> spSdAssetType=new MutableLiveData<>();
    public MutableLiveData<String> spSdAssetSurface=new MutableLiveData<>();
    public MutableLiveData<String> spSdTagType=new MutableLiveData<>();
    public MutableLiveData<String> spSdParentChild=new MutableLiveData<>();
    public MutableLiveData<String> spOdAssignId=new MutableLiveData<>();
    public MutableLiveData<String> spOdUtilizationLevel=new MutableLiveData<>();
    //extra
    public MutableLiveData<Integer> financialYearID=new MutableLiveData<>(2);
    public MutableLiveData<Integer> locationID=new MutableLiveData<>(2);
    public MutableLiveData<Integer> subLocationID=new MutableLiveData<>(2);
    public MutableLiveData<Integer> assetCategoryID=new MutableLiveData<>(2);
    public MutableLiveData<Integer> assetSubCategoryID=new MutableLiveData<>(2);
    //sanitization VARIABLE

    public MutableLiveData<Integer> SanitizationAssetTypeID=new MutableLiveData<>(2);
    public MutableLiveData<Integer> SanitizationAssetTypeName=new MutableLiveData<>(2);
    public MutableLiveData<Integer> SanitizationAssetName=new MutableLiveData<>(2);
    public MutableLiveData<Integer> SanitizationAssetSurfaceID=new MutableLiveData<>(2);
    public MutableLiveData<Integer> SanitizationAssetSurfaceName=new MutableLiveData<>(2);
    public MutableLiveData<Integer> SanitizationTagTypeID=new MutableLiveData<>(2);
    public MutableLiveData<Integer> SanitizationTagTypeName=new MutableLiveData<>(2);
    public MutableLiveData<Integer> SanitizationTempTagNo=new MutableLiveData<>(2);
    public MutableLiveData<Integer> CreatedBy=new MutableLiveData<>(2);
    public MutableLiveData<Integer> CreatedByName=new MutableLiveData<>(2);
    public MutableLiveData<Integer> UpdateBy=new MutableLiveData<>(2);
    public MutableLiveData<Integer> UpdateByName=new MutableLiveData<>(2);
    public MutableLiveData<Integer> UpdateRemarks=new MutableLiveData<>(2);
    public MutableLiveData<Integer> ParentId=new MutableLiveData<>(2);
    public MutableLiveData<Integer> AssignedTo=new MutableLiveData<>(2);
    public MutableLiveData<Integer> UtilizationType=new MutableLiveData<>(2);
    //    public MutableLiveData<MasterListModel> masterListModelMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<NewAsset> masterListModelMutableLiveData = new MutableLiveData<>();


    //set data in obj
//    public MutableLiveData<MasterListModel> setMasterDetailObj(){
//        if(masterListModelMutableLiveData == null){
//            masterListModelMutableLiveData =new MutableLiveData<>();
//        }
//        return masterListModelMutableLiveData;
//    }
//
    public MutableLiveData<NewAsset> setMasterDetailObj(){
        if(masterListModelMutableLiveData == null){
            masterListModelMutableLiveData =new MutableLiveData<>();
        }
        return masterListModelMutableLiveData;
    }


//fun working on hit
    public void onClickFn() {
//        MasterListModel masterDetailModel = new MasterListModel( spAdFinanceYear.getValue(),  financialYear, Integer locationID,  locationName, Integer subLocationID,
//                 subLocationName, Integer assetCategoryID,  assetCategoryName, Integer assetSubCategoryID,
//                 assetSubcategoryName,  locationCodeERP,  locationNameERP,  subLocationCodeERP,  subLocationNameERP,
//                 department,  custodian,  assetCode,  serialNumber,  assetNumber,  assetNumberOld,
//                 assetName,  assetMake,  assetModel,  assetDescription, Integer assetConditionID,  assetCondition,
//                 depreciationKey,  plant,  vendor,  evaluationGroup,  businessArea, Integer sanitizationAssetTypeID,
//                 sanitizationAssetTypeName,  sanitizationAssetName, Integer sanitizationAssetSurfaceID,  sanitizationAssetSurfaceName,
//                Integer sanitizationTagTypeID,  sanitizationTagTypeName,  sanitizationTempTagNo,  spocName,
//                 remarks1,  remarks2, Integer createdBy,  createdByName, Integer updateBy,  updateByName,
//                 updateRemarks, Integer parentId, Integer assignId,  parentAsset,  assignedTo,
//                 vendorInvoiceImage,  fileExtension,  ven,  thirdPartyAsset,  spOdUtilizationLevel);
//        MasterListModel masterDetailModel =new MasterListModel();
//        masterDetailModel.setFinancialYear(assetMasterMutableLiveData.getValue().FinancialYear);
////        assetMasterMutableLiveData.getValue().AssetName
//        spAdFinanceYear.setValue(assetMasterMutableLiveData.getValue().FinancialYear);
//        spAdLocation.setValue(assetMasterMutableLiveData.getValue().LocationName);
//        spAdSubLocation.setValue(assetMasterMutableLiveData.getValue().SubLocationName);
//        spAdCategory.setValue(assetMasterMutableLiveData.getValue().AssetCategoryName);
//        spAdSubCategory.setValue(assetMasterMutableLiveData.getValue().SubLocationName);
//        LocationNameERP.setValue(assetMasterMutableLiveData.getValue().LocationNameERP);
         NewAsset newAsset = new NewAsset();
         newAsset.setFinancialYear(spAdFinanceYear.getValue());
         newAsset.setLocationName(spAdLocation.getValue());
         newAsset.setSubLocationName(spAdSubLocation.getValue());
         newAsset.setAssetCategoryName(spAdCategory.getValue());
         newAsset.setAssetSubcategoryName(spAdSubCategory.getValue());
         newAsset.setLocationCodeERP(LocationCodeERP.getValue());
         newAsset.setLocationNameERP(LocationNameERP.getValue());
         newAsset.setSubLocationCodeERP(SubLocationCodeERP.getValue());
//         newAsset.setSubLocationNameERP(SubLocat.getValue());
         newAsset.setDepartment(Department.getValue());
         newAsset.setCustodian(Custodian.getValue());
         newAsset.setAssetCode(AssetCode.getValue());
         newAsset.setSerialNumber(SerialNo.getValue());
         newAsset.setAssetNumber(AssetNumber.getValue());
         newAsset.setAssetNumberOld(OldAssetNumber.getValue());
         newAsset.setAssetName(AssetName.getValue());
         newAsset.setAssetMake(MakeTxt.getValue());
         newAsset.setAssetModel(ModelTxt.getValue());
         newAsset.setAssetDescription(Description.getValue());
         newAsset.setAssetCondition(AssetCondition.getValue());
         newAsset.setDepreciationKey(DepreciationKey.getValue());
         newAsset.setPlant(Plant.getValue());
         newAsset.setVendor(VenderName.getValue());
         newAsset.setEvaluationGroup(EvaluationGroup.getValue());
         newAsset.setBusinessArea(BussinessArea.getValue());
//         newAsset.setSanitizationAssetTypeID(.getValue());
         newAsset.setSpocName(SPOCName.getValue());
         newAsset.setRemarks1(ClientRemarks.getValue());
         newAsset.setRemarks2(PVRemarks.getValue());
//         newAsset.setAssignId(AssignId.getValue());
         newAsset.setAssignId(Integer.valueOf(AssignId.getValue()));
         newAsset.setParentAsset(spSdParentChild.getValue());
         newAsset.setThirdPartyAsset(thirdPartyAsset.getValue());
//         newAsset.setUtilizationType(.getValue());

//         newAsset.setCreatedBy(.getValue());

        masterListModelMutableLiveData.setValue(newAsset);
//        private String utilizationType;

//        MasterListModel masterDetailModel =new MasterListModel();


//        masterListModelMutableLiveData.setValue(masterDetailModel);
//        updateMasterFn(masterDetailModel);
    }
    public void updateMasterFn(MasterListModel masterDetailModel){
//        masterListModelMutableLiveData.setValue(assetMaster);
//        AddNewAssetRepo.getInstance().postAsset( masterDetailModel, new IAddNewAssetRepo.AddNewAssetCallback() {
//            Override
//            public void onDataLoaded(NewAssetResponce responce) {
//                Toast.makeText(AMSApplication.getInstance(), ""+responce.getResponse(), Toast.LENGTH_SHORT).show();
//                Log.d("whiteWalker", "onDataLoaded: ");
//            }
//
//            Override
//            public void onDataNotAvailable() {
//                Toast.makeText(AMSApplication.getInstance(), "No Responce", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
