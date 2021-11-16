package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class ExcessAuditViewModel extends BaseViewModel {
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

    public MutableLiveData<String> ImageCount = new MutableLiveData<>();
    public MutableLiveData<String> AssetName = new MutableLiveData<>();
    public MutableLiveData<String> AssetNumber = new MutableLiveData<>();
    public MutableLiveData<Integer> AuditId = new MutableLiveData<>();
    public MutableLiveData<String> ExcessId = new MutableLiveData<>();
    public MutableLiveData<String> AssetId = new MutableLiveData<>();
    public MutableLiveData<String> Latitude = new MutableLiveData<>();
    public MutableLiveData<String> Longitude = new MutableLiveData<>();
    public MutableLiveData<String> LocationString = new MutableLiveData<>();
    public MutableLiveData<String> AuditEStatus = new MutableLiveData<>();
    public MutableLiveData<String> AuditERemarks = new MutableLiveData<>();
    public void init(){
        AssetNumber.setValue("");
        AssetName.setValue("");
        AuditERemarks.setValue("");
        AssetId.setValue("");
        ExcessId.setValue("");
        LocationString.setValue("");
        imageOne.setValue("");
        imageTwo.setValue("");
        imageThree.setValue("");
        imageFour.setValue("");
        imageFive.setValue("");
        ImageCount.setValue("0");

    }
    public ExcessAuditViewModel(@NonNull Application application) {
        super(application);
        imageOne.setValue("");
        imageTwo.setValue("");
        imageThree.setValue("");
        imageFour.setValue("");
        imageFive.setValue("");

    }

    public void onClick(View view) {

        if (ImageCount.getValue().equalsIgnoreCase("0")) {
            Toast.makeText(getApplication(), "Add Some Asset Image. Tap On Camera Icon.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetName.getValue())) {
            Toast.makeText(getApplication(), "Enter Asset Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetNumber.getValue())) {
            Toast.makeText(getApplication(), "Enter Asset Number.", Toast.LENGTH_SHORT).show();
        }
    }
}
