package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.db.AppDatabase;
import com.ams.amsvistara.db.table.master.MasterData;
import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.db.table.master.TagModels;
import com.ams.amsvistara.model.res.TagAssetRes;
import com.ams.amsvistara.ws.repo.ITagAssetRepo;
import com.ams.amsvistara.ws.repo.TagAssetRepo;

import java.util.ArrayList;
import java.util.Map;

public class TaggingDetailViewModel extends BaseViewModel {
    /*public MutableLiveData<byte[]> imageOne = new MutableLiveData<>();
    public MutableLiveData<byte[]> imageTwo = new MutableLiveData<>();
    public MutableLiveData<byte[]> imageThree = new MutableLiveData<>();
    public MutableLiveData<byte[]> imageFour = new MutableLiveData<>();
    public MutableLiveData<byte[]> imageFive = new MutableLiveData<>();
*/
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
    public MutableLiveData<String> VendorName = new MutableLiveData<>();
    public MutableLiveData<String> CapDate = new MutableLiveData<>();
    public MutableLiveData<String> PONo = new MutableLiveData<>();
    public MutableLiveData<String> QRCode = new MutableLiveData<>();
    public MutableLiveData<String> Latitude = new MutableLiveData<>();
    public MutableLiveData<String> Longitude = new MutableLiveData<>();
    public MutableLiveData<String> LocationStr = new MutableLiveData<>();
    public MutableLiveData<String> ImageCount = new MutableLiveData<>();
    public MutableLiveData<Boolean> SaveFlag = new MutableLiveData<>();

    public TaggingDetailViewModel(@NonNull Application application)
    {

        super(application);
        imageOne.setValue("");
        imageTwo.setValue("");
        imageThree.setValue("");
        imageFour.setValue("");
        imageFive.setValue("");
    }

    public MutableLiveData<Integer> tagDetailID = new MutableLiveData<>();
    public MutableLiveData<Integer> tagID = new MutableLiveData<>();

    public MutableLiveData<Boolean> callBackPress = new MutableLiveData<>();


    public TagItems fetchTagDetailData(int tagID, int tagDetailID) {
        TagItems tagItems = null;
        ArrayList<TagModels> tagModels = new ArrayList<>();
        tagModels.addAll(AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData().TAGModels);
        for (int i = 0; i < tagModels.size(); i++) {

            if (tagModels.get(i).TAGID == tagID) {
                //    pvItemsArrayList.addAll(pvModel.get(i).PVItems);
                for (int j = 0; j < tagModels.get(i).TAGItems.size(); j++) {

                    if (tagModels.get(i).TAGItems.get(j).TAGDetailID == tagDetailID) {
                        tagItems = new TagItems();
                        Log.e("####", "########## TAGDetailID ######### " + tagModels.get(i).TAGItems.get(j).TAGDetailID);
                        Log.e("####", "########## Tag AssetName ######### " + tagModels.get(i).TAGItems.get(j).AssetName);
                        Log.e("####", "########## Vendor ######### " + tagModels.get(i).TAGItems.get(j).Vendor);
                        Log.e("####", "########## Tag CapitalizationDate ######### " + tagModels.get(i).TAGItems.get(j).CapitalizationDate);

                        Log.e("#IMG#", " " + tagModels.get(i).TAGItems.get(j).ImageCount);
                        Log.e("#IMG#", " " + tagModels.get(i).TAGItems.get(j).Image1);
                        tagItems = tagModels.get(i).TAGItems.get(j);

                    }
                }

            }
        }
        return tagItems;
    }

    public void saveTagAsset(TagItems tagItems, Map<String, String> req, int tagID, int tagDetailID) {
        //  Gson gson = new Gson();
        /*String jsonTagItems = gson.toJson(tagItems);
        Log.e("####", "########## jsonTagItems ######### " + jsonTagItems);
*/

        tagItems.Image1 = ""+imageOne.getValue();
        tagItems.Image2 = ""+imageTwo.getValue();
        tagItems.Image3 = ""+imageThree.getValue();
        tagItems.Image4 = ""+imageFour.getValue();
        tagItems.Image5 = ""+imageFive.getValue();
        TagAssetRepo.getInstance().postTagAsset(req, tagItems, new ITagAssetRepo.TagAssetCallback() {
            @Override
            public void onDataLoaded(TagAssetRes tagAssetRes) {
                Log.e("####", "######## tagAssetRes ########## " + tagAssetRes.responseMessage);
                Toast.makeText(AMSApplication.instance, ""+tagAssetRes.responseStatus, Toast.LENGTH_SHORT).show();
                updateTagDetailData(tagID, tagDetailID, false);

            }

            @Override
            public void onDataNotAvailable() {
                Log.e("####", "######## tagAssetRes onDataNotAvailable ########## ");
            }
        });
    }

    public TagItems updateTagDetailData(int tagID, int tagDetailID, boolean saveFlag) {
        TagItems tagItems = new TagItems();
        //   ArrayList<TagModels> tagModels = new ArrayList<>();
        MasterData masterData = AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData();
        // tagModels.addAll(masterData.TAGModels);
        for (int i = 0; i < masterData.TAGModels.size(); i++) {
            // tagItems=new TagItems();

            if (masterData.TAGModels.get(i).TAGID == tagID) {
                //    pvItemsArrayList.addAll(pvModel.get(i).PVItems);
                for (int j = 0; j < masterData.TAGModels.get(i).TAGItems.size(); j++) {
                    if (masterData.TAGModels.get(i).TAGItems.get(j).TAGDetailID == tagDetailID) {
                        //Log.e("####", "########## PVDetailID ######### " + masterData.TAGModels.get(i).TAGItems.get(j).TAGDetailID);
                        masterData.TAGModels.get(i).TAGItems.get(j).AssetID = masterData.TAGModels.get(i).TAGItems.get(j).AssetID;
                        masterData.TAGModels.get(i).TAGItems.get(j).AssetName = masterData.TAGModels.get(i).TAGItems.get(j).AssetName;
                        masterData.TAGModels.get(i).TAGItems.get(j).AssetNumber = masterData.TAGModels.get(i).TAGItems.get(j).AssetNumber;
                        masterData.TAGModels.get(i).TAGItems.get(j).Vendor = "" + masterData.TAGModels.get(i).TAGItems.get(j).Vendor;
                        masterData.TAGModels.get(i).TAGItems.get(j).CapitalizationDate = "" + masterData.TAGModels.get(i).TAGItems.get(j).CapitalizationDate;
                        masterData.TAGModels.get(i).TAGItems.get(j).QRCode = "" + QRCode.getValue();
                        masterData.TAGModels.get(i).TAGItems.get(j).Latitude = "" + Latitude.getValue();
                        masterData.TAGModels.get(i).TAGItems.get(j).Longitude = "" + Longitude.getValue();
                        masterData.TAGModels.get(i).TAGItems.get(j).LocationString = "" + LocationStr.getValue();
                        masterData.TAGModels.get(i).TAGItems.get(j).TAGID = tagID;
                        masterData.TAGModels.get(i).TAGItems.get(j).TAGDetailID = tagDetailID;
                        masterData.TAGModels.get(i).TAGItems.get(j).ImageCount = Integer.parseInt(ImageCount.getValue());

                        masterData.TAGModels.get(i).TAGItems.get(j).TagDStatus = masterData.TAGModels.get(i).TAGItems.get(j).TagDStatus;
                        masterData.TAGModels.get(i).TAGItems.get(j).TagDRemarks = masterData.TAGModels.get(i).TAGItems.get(j).TagDRemarks;

                        masterData.TAGModels.get(i).TAGItems.get(j).SaveFlag = saveFlag;
                        masterData.TAGModels.get(i).TAGItems.get(j).Image1 = "" + imageOneP.getValue();
                        masterData.TAGModels.get(i).TAGItems.get(j).Image2 = "" + imageTwoP.getValue();
                        masterData.TAGModels.get(i).TAGItems.get(j).Image3 = "" + imageThreeP.getValue();
                        masterData.TAGModels.get(i).TAGItems.get(j).Image4 = "" + imageFourP.getValue();
                        masterData.TAGModels.get(i).TAGItems.get(j).Image5 = "" + imageFiveP.getValue();
                        AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().updateAllMasterData(masterData);
                        tagItems = masterData.TAGModels.get(i).TAGItems.get(j);
                        Log.e("####", "########## tagItems ######### " + masterData.TAGModels.get(i).TAGItems.get(j).SaveFlag);
                        if (saveFlag) {
                            callBackPress.setValue(true);
                        }
                    }
                }

            }
        }
        return tagItems;
    }

    public void setUiWithTagDetail(TagItems tagItems) {
        ImageCount.setValue("" + tagItems.ImageCount);
        AssetName.setValue(tagItems.AssetName);
        VendorName.setValue(tagItems.Vendor);
        CapDate.setValue(tagItems.CapitalizationDate);
        PONo.setValue(tagItems.PONumber);
        QRCode.setValue(tagItems.QRCode);
        Latitude.setValue(tagItems.Latitude);
        Longitude.setValue(tagItems.Longitude);
        SaveFlag.setValue(tagItems.SaveFlag);

    }


    public boolean isValidForSave() {

        if (TextUtils.isEmpty(QRCode.getValue())) {
            Toast.makeText(AMSApplication.instance, "Scan Tag", Toast.LENGTH_SHORT).show();
            return false;
        } else if (ImageCount.getValue().equalsIgnoreCase("0")) {
            Toast.makeText(AMSApplication.instance, "Need To Add Images", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

/*

 * */