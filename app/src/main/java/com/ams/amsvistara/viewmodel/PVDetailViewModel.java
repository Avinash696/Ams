package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.db.AppDatabase;
import com.ams.amsvistara.db.table.master.MasterData;
import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.db.table.master.PvModels;
import com.ams.amsvistara.model.res.PvAssetRes;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.ws.repo.IPvAssetRepo;
import com.ams.amsvistara.ws.repo.PvAssetRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class PVDetailViewModel extends BaseViewModel {

    public MutableLiveData<String> imageOne = new MutableLiveData<>();
    public MutableLiveData<String> imageTwo = new MutableLiveData<>();
    public MutableLiveData<String> imageThree = new MutableLiveData<>();
    public MutableLiveData<String> imageFour = new MutableLiveData<>();
    public MutableLiveData<String> imageFive = new MutableLiveData<>();
    public MutableLiveData<String> imageOneP = new MutableLiveData<>();
    public MutableLiveData<String> imageTwoP = new MutableLiveData<>();
    public MutableLiveData<String> imageThreeP = new MutableLiveData<>();
    public MutableLiveData<String> imageFourP = new MutableLiveData<>();
    public MutableLiveData<String> imageFiveP = new MutableLiveData<>();

    public MutableLiveData<String> AssetNumber = new MutableLiveData<>();
    public MutableLiveData<String> AssetName = new MutableLiveData<>();
    public MutableLiveData<String> ImageCount = new MutableLiveData<>();
    public MutableLiveData<String> AssetType = new MutableLiveData<>();
    public MutableLiveData<Integer> AssetTypeCode = new MutableLiveData<>();
    public MutableLiveData<String> AssetSurface = new MutableLiveData<>();
    public MutableLiveData<Integer> AssetSurfaceCode = new MutableLiveData<>();
    /*public MutableLiveData<String> HeatedAsset = new MutableLiveData<>();*/
    public MutableLiveData<String> AssetCondition = new MutableLiveData<>();
    public MutableLiveData<Integer> AssetConditionCode = new MutableLiveData<>();
    public MutableLiveData<String> AssetNameText = new MutableLiveData<>();
    public MutableLiveData<String> MakeTxt = new MutableLiveData<>();
    public MutableLiveData<String> ModelTxt = new MutableLiveData<>();
    public MutableLiveData<String> SerialNo = new MutableLiveData<>();
    public MutableLiveData<String> Location = new MutableLiveData<>();
    public MutableLiveData<String> SubLocation = new MutableLiveData<>();
    public MutableLiveData<Integer> LocationID = new MutableLiveData<>();
    public MutableLiveData<Integer> SubLocationID = new MutableLiveData<>();
    public MutableLiveData<String> SPOCName = new MutableLiveData<>();
    public MutableLiveData<String> PVName = new MutableLiveData<>();
    public MutableLiveData<String> ClientRemarks = new MutableLiveData<>();
    public MutableLiveData<Boolean> saveFlag = new MutableLiveData<>();
    public MutableLiveData<String> isRadioSelection = new MutableLiveData<>();
    public MutableLiveData<PvItems> saveTaggingTable = new MutableLiveData<>();
    public MutableLiveData<Integer> pVDetailID = new MutableLiveData<>();
    public MutableLiveData<Integer> pVID = new MutableLiveData<>();

    public MutableLiveData<Boolean> callBackPress = new MutableLiveData<>();

    public PVDetailViewModel(@NonNull Application application) {
        super(application);
        imageOne.setValue("");
        imageTwo.setValue("");
        imageThree.setValue("");
        imageFour.setValue("");
        imageFive.setValue("");
    }

    public PvItems fetchPVDetailData(int pvID, int pvDetailID) {
        PvItems pvItems = null;
        ArrayList<PvModels> pvModel = new ArrayList<>();
        pvModel.addAll(AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData().PVModels);
        for (int i = 0; i < pvModel.size(); i++) {


            if (pvModel.get(i).PhysicalVerificationID == pvID) {
                //    pvItemsArrayList.addAll(pvModel.get(i).PVItems);
                for (int j = 0; j < pvModel.get(i).PVItems.size(); j++) {
                    if (pvModel.get(i).PVItems.get(j).PVDetailID == pvDetailID) {
                        pvItems = new PvItems();
                        //      Log.e("####", "########## PVDetailID ######### " + pvModel.get(i).PVItems.get(j).PVDetailID);
                        pvItems = pvModel.get(i).PVItems.get(j);

                    }
                }

            }
        }
        return pvItems;
    }


    public void savePvAsset(PvItems pvItems, Map<String, String> req, int pvID, int pvDetailID) {
        //   Gson gson = new Gson();
       /* String jsonTagItems = gson.toJson(pvItems);
        Log.e("####", "########## jsonTagItems ######### " + jsonTagItems);
*/

        pvItems.Image1 = ""+imageOne.getValue();
        pvItems.Image2 = ""+imageTwo.getValue();
        pvItems.Image3 = ""+imageThree.getValue();
        pvItems.Image4 = ""+imageFour.getValue();
        pvItems.Image5 = ""+imageFive.getValue();

        PvAssetRepo.getInstance().postPvAsset(req, pvItems, new IPvAssetRepo.PvAssetCallback() {
            @Override
            public void onDataLoaded(PvAssetRes pvAssetRes) {
                Log.e("####", "######## tagAssetRes ########## " + pvAssetRes.responseMessage);
                Log.e("####", "######## pvID ########## " + pvID);
                Log.e("####", "######## pvDetailID ########## " + pvDetailID);
                updatePvDetailData(pvID, pvDetailID, false);

            }

            @Override
            public void onDataNotAvailable() {
                Log.e("####", "######## tagAssetRes onDataNotAvailable ########## ");
            }
        });
    }

    public PvItems updatePvDetailData(int pvID, int pvDetailID, boolean saveFlag) {
        PvItems pvItems = new PvItems();
        //   ArrayList<TagModels> tagModels = new ArrayList<>();
        MasterData masterData = AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData();
        // tagModels.addAll(masterData.TAGModels);
        for (int i = 0; i < masterData.PVModels.size(); i++) {
            // tagItems=new TagItems();

            if (masterData.PVModels.get(i).PhysicalVerificationID == pvID) {
                //    pvItemsArrayList.addAll(pvModel.get(i).PVItems);
                AssetTypeCode.setValue(1);
                AssetSurfaceCode.setValue(1);
                AssetConditionCode.setValue(1);
                for (int j = 0; j < masterData.PVModels.get(i).PVItems.size(); j++) {
                    if (masterData.PVModels.get(i).PVItems.get(j).PVDetailID == pvDetailID) {
                        masterData.PVModels.get(i).PVItems.get(j).AssetID = masterData.PVModels.get(i).PVItems.get(j).AssetID;
                        masterData.PVModels.get(i).PVItems.get(j).AssetName = "" + masterData.PVModels.get(i).PVItems.get(j).AssetName;
                        masterData.PVModels.get(i).PVItems.get(j).AssetNumber = "" + masterData.PVModels.get(i).PVItems.get(j).AssetNumber;
                        masterData.PVModels.get(i).PVItems.get(j).Vendor = "" + masterData.PVModels.get(i).PVItems.get(j).Vendor;
                        masterData.PVModels.get(i).PVItems.get(j).CapitalizationDate = "" + masterData.PVModels.get(i).PVItems.get(j).CapitalizationDate;
                        masterData.PVModels.get(i).PVItems.get(j).LocationID = LocationID.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).LocationName = "" + Location.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).SubLocationID = SubLocationID.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).SubLocationName = "" + SubLocation.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).SerialNumber = "" + SerialNo.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).Make = "" + MakeTxt.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).Model = "" + ModelTxt.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).AssetTypeID = AssetTypeCode.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).AssetTypeName = "" + AssetType.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).AssetSurfaceID = AssetSurfaceCode.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).AssetSurfaceName = "" + AssetSurface.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).AssetConditionID = AssetConditionCode.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).AssetConditionName = "" + AssetCondition.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).SpocName = "" + SPOCName.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).PVName = "" + PVName.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).ClientRemarks = "" + ClientRemarks.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).PVID = pvID;
                        masterData.PVModels.get(i).PVItems.get(j).PVDetailID = pvDetailID;
                        masterData.PVModels.get(i).PVItems.get(j).ImageCount = Integer.parseInt(ImageCount.getValue());
                        masterData.PVModels.get(i).PVItems.get(j).PVDStatus = masterData.PVModels.get(i).PVItems.get(j).PVDStatus;
                        masterData.PVModels.get(i).PVItems.get(j).PVDRemarks = masterData.PVModels.get(i).PVItems.get(j).PVDRemarks;
                        masterData.PVModels.get(i).PVItems.get(j).SaveFlag = saveFlag;
                        masterData.PVModels.get(i).PVItems.get(j).Image1 = "" + imageOneP.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).Image2 = "" + imageTwoP.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).Image3 = "" + imageThreeP.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).Image4 = "" + imageFourP.getValue();
                        masterData.PVModels.get(i).PVItems.get(j).Image5 = "" + imageFiveP.getValue();
                        AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().updateAllMasterData(masterData);
                        pvItems = masterData.PVModels.get(i).PVItems.get(j);
                        Log.e("####", "########## tagItems ######### " + masterData.PVModels.get(i).PVItems.get(j).SaveFlag);
                        if (saveFlag) {
                            callBackPress.setValue(true);
                        }
                    }
                }

            }
        }
        return pvItems;
    }


    public void setUiWithPvDetail(PvItems pvItems) {
        //if(pvItems.AssetTypeID==)
        ImageCount.setValue("" + pvItems.ImageCount);
        AssetNameText.setValue(pvItems.AssetName);
        MakeTxt.setValue(pvItems.Make);
        ModelTxt.setValue(pvItems.Model);
        SerialNo.setValue(pvItems.SerialNumber);
        LocationID.setValue(pvItems.LocationID);
        Location.setValue(pvItems.LocationName);
        SubLocationID.setValue(pvItems.SubLocationID);
        SubLocation.setValue(pvItems.SubLocationName);
        SPOCName.setValue(pvItems.SpocName);
        PVName.setValue(pvItems.PVName);
        ClientRemarks.setValue(pvItems.ClientRemarks);
        saveFlag.setValue(pvItems.SaveFlag);
        AssetTypeCode.setValue(pvItems.AssetTypeID);
        AssetSurfaceCode.setValue(pvItems.AssetSurfaceID);
        AssetConditionCode.setValue(pvItems.AssetConditionID);

    }

    public void onClick(View view) {
        isRadioSelection.setValue("check");
        if (ImageCount.getValue().equalsIgnoreCase("0")) {
            Toast.makeText(getApplication(), "Add Some Asset Image. Tap On Camera Icon.", Toast.LENGTH_SHORT).show();
        } else if (ImageCount.getValue().equalsIgnoreCase("0")) {
            Toast.makeText(getApplication(), "Add Some Asset Image. Tap On Camera Icon.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetType.getValue())) {
            Toast.makeText(getApplication(), "Select Asset Type.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetSurface.getValue())) {
            Toast.makeText(getApplication(), "Select Asset Surface.", Toast.LENGTH_SHORT).show();
        } /*else if (TextUtils.isEmpty(HeatedAsset.getValue())) {
            Toast.makeText(getApplication(), "Select Heated Asset.", Toast.LENGTH_SHORT).show();
        }*/ else if (TextUtils.isEmpty(AssetCondition.getValue())) {
            Toast.makeText(getApplication(), "Select Asset Condition.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetNameText.getValue())) {
            Toast.makeText(getApplication(), "Enter Asset Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(MakeTxt.getValue())) {
            Toast.makeText(getApplication(), "Enter Make", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ModelTxt.getValue())) {
            Toast.makeText(getApplication(), "Enter Model", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(SerialNo.getValue())) {
            Toast.makeText(getApplication(), "Enter Serial No.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Location.getValue())) {
            Toast.makeText(getApplication(), "Enter Location", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(SubLocation.getValue())) {
            Toast.makeText(getApplication(), "Enter Sub Location", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(SPOCName.getValue())) {
            Toast.makeText(getApplication(), "Enter SPOC Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(PVName.getValue())) {
            Toast.makeText(getApplication(), "Enter PV Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ClientRemarks.getValue())) {
            Toast.makeText(getApplication(), "Enter Client Remarks", Toast.LENGTH_SHORT).show();
        } else if (Location.getValue().equalsIgnoreCase("Select Location")) {
            Toast.makeText(getApplication(), "Select Location", Toast.LENGTH_SHORT).show();
        } else if (SubLocation.getValue().equalsIgnoreCase("Select Sub Location")) {
            Toast.makeText(getApplication(), "Select Sub Location", Toast.LENGTH_SHORT).show();
        } else {

            //     saveFlag.setValue(true);

            try {
                callBackPress.setValue(false);
                PvItems tagItems = updatePvDetailData(pVID.getValue(), pVDetailID.getValue(), true);
                if (tagItems != null) {
                    Toast.makeText(AMSApplication.instance, "Saved", Toast.LENGTH_SHORT).show();
                    Map<String, String> req = new HashMap<String, String>();
                    req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", AMSApplication.instance));
                    savePvAsset(tagItems, req, pVID.getValue(), pVDetailID.getValue());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

         /*   PvItems pvItemTable = new PvItems();

            pvItemTable.AssetCode = AssetCode.getValue();
            pvItemTable.AssetName = AssetName.getValue();
            pvItemTable.ImageCount = ImageCount.getValue();
            pvItemTable.AssetType = AssetType.getValue();
            pvItemTable.AssetSurface = AssetSurface.getValue();
            //    pvItemTable.HeatedAsset = HeatedAsset.getValue();
            pvItemTable.AssetCondition = AssetCondition.getValue();
            pvItemTable.AssetNameText = AssetNameText.getValue();

            pvItemTable.MakeTxt = MakeTxt.getValue();
            pvItemTable.ModelTxt = ModelTxt.getValue();
            pvItemTable.SerialNo = SerialNo.getValue();
            pvItemTable.Location = Location.getValue();

            pvItemTable.SubLocation = SubLocation.getValue();
            pvItemTable.SPOCName = SPOCName.getValue();
            pvItemTable.PVName = PVName.getValue();
            pvItemTable.ClientRemarks = ClientRemarks.getValue();
            pvItemTable.saveFlag = saveFlag.getValue();

            for (int i = 0; i < Integer.parseInt(ImageCount.getValue()); i++) {
                if (i == 1) {
                    pvItemTable.imageOne = imageOne.getValue();
                }
                if (i == 2) {
                    pvItemTable.imageTwo = imageTwo.getValue();
                }
                if (i == 3) {
                    pvItemTable.imageThree = imageThree.getValue();
                }
                if (i == 4) {
                    pvItemTable.imageFour = imageFour.getValue();
                }
                if (i == 5) {
                    pvItemTable.imageFive = imageFive.getValue();
                }
            }

            saveTaggingTable.setValue(pvItemTable);
              *//*  Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().AssetName);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().AssetCode);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().ImageCount);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().AssetType);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().AssetSurface);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().HeatedAsset);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().AssetCondition);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().AssetNameText);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().MakeTxt);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().ModelTxt);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().SerialNo);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().Location);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().SubLocation);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().SPOCName);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().PVName);
                Log.e("####", "saveTaggingTable ############# " + saveTaggingTable.getValue().ClientRemarks);*//*
            if (!AppConstant.isNetworkConnected) {
                long value_ = AppDatabase.getAppDatabase(AMSApplication.instance).pVItemTableDao().insertSingleInfo(pvItemTable);

           *//* Log.e("####", "getAppDatabase ############# " + value_);
            try {
                Log.e("####", " ############# " + AppDatabase.getAppDatabase(AMSApplication.instance).pVItemTableDao().getPVItemTable().get(0).AssetCode);
                Log.e("####", " ############# " + AppDatabase.getAppDatabase(AMSApplication.instance).pVItemTableDao().getPVItemTable().get(0).AssetName);
            } catch (Exception e) {
                e.printStackTrace();
            }*//*
            } else {

            }*/
        }

    }
/*

    public boolean checkRecordAvail(String assetCode) {
        if (AppDatabase.getAppDatabase(AMSApplication.instance).pVItemTableDao().getSaveFlagPVItemTable(assetCode)) {
            saveTaggingTable.setValue(AppDatabase.getAppDatabase(AMSApplication.instance).pVItemTableDao().getSavedPVItemTable(assetCode));

            AssetCode.setValue(saveTaggingTable.getValue().AssetCode);
            AssetName.setValue(saveTaggingTable.getValue().AssetName);
            ImageCount.setValue(saveTaggingTable.getValue().ImageCount);
            AssetType.setValue(saveTaggingTable.getValue().AssetType);
            AssetSurface.setValue(saveTaggingTable.getValue().AssetSurface);
            //  HeatedAsset.setValue(saveTaggingTable.getValue().HeatedAsset);
            AssetCondition.setValue(saveTaggingTable.getValue().AssetCondition);
            AssetNameText.setValue(saveTaggingTable.getValue().AssetNameText);

            MakeTxt.setValue(saveTaggingTable.getValue().MakeTxt);
            ModelTxt.setValue(saveTaggingTable.getValue().ModelTxt);
            SerialNo.setValue(saveTaggingTable.getValue().SerialNo);
            Location.setValue(saveTaggingTable.getValue().Location);

            SubLocation.setValue(saveTaggingTable.getValue().SubLocation);
            SPOCName.setValue(saveTaggingTable.getValue().SPOCName);
            PVName.setValue(saveTaggingTable.getValue().PVName);
            ClientRemarks.setValue(saveTaggingTable.getValue().ClientRemarks);
            saveFlag.setValue(saveTaggingTable.getValue().saveFlag);
        } else {

        }

        return false;
    }

*/

}
