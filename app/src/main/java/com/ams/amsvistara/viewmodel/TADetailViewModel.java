package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.TAPostRes;
import com.ams.amsvistara.ws.repo.ITARepo;
import com.ams.amsvistara.ws.repo.TARepo;

import java.util.Map;

public class TADetailViewModel extends BaseViewModel {
    public TADetailViewModel(@NonNull Application application) {
        super(application);
    }


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

    public MutableLiveData<Integer> tagDetailID = new MutableLiveData<>();
    public MutableLiveData<Integer> tagID = new MutableLiveData<>();

    public MutableLiveData<Boolean> callBackPress = new MutableLiveData<>();
    public MutableLiveData<TRItems> TRItems = new MutableLiveData<>();


    public void saveTAPostAsset(TRItems trItems,Map<String, String> req) {
        //  Gson gson = new Gson();
        /*String jsonTagItems = gson.toJson(tagItems);
        Log.e("####", "########## jsonTagItems ######### " + jsonTagItems);
*/

        trItems.Image1 = imageOne.getValue();
        trItems.Image2 = imageTwo.getValue();
        trItems.Image3 = imageThree.getValue();
        trItems.Image4 = imageFour.getValue();
        trItems.Image5 = imageFive.getValue();
        TARepo.getInstance().postTA(req, trItems, new ITARepo.TAPostCallback() {
            @Override
            public void onDataLoaded(TAPostRes taPostRes) {
                callBackPress.setValue(true);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
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
