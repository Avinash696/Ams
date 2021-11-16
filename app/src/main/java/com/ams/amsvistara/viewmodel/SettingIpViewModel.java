package com.ams.amsvistara.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.model.IpAddressModel;


public class SettingIpViewModel extends ViewModel {
    public MutableLiveData<String> IpAddress = new MutableLiveData<>();
    public MutableLiveData<String> SubAddress = new MutableLiveData<>();

    private MutableLiveData<IpAddressModel> ipAddressModelMutableLiveData;

    public MutableLiveData<IpAddressModel> getAddress() {

        if (ipAddressModelMutableLiveData == null) {
            ipAddressModelMutableLiveData = new MutableLiveData<>();
        }
        return ipAddressModelMutableLiveData;

    }

    public void onClick(View view) {

        IpAddressModel ipAddress_ = new IpAddressModel(IpAddress.getValue(),SubAddress.getValue());

        ipAddressModelMutableLiveData.setValue(ipAddress_);


    }
}
