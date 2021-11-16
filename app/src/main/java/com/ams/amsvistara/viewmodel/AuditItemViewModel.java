package com.ams.amsvistara.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.AuditExcessItem;


public class AuditItemViewModel extends ViewModel {
    public MutableLiveData<String> assetName = new MutableLiveData<>();
    public MutableLiveData<String> assetNumber = new MutableLiveData<>();
    public MutableLiveData<String> qrCode = new MutableLiveData<>();
    public MutableLiveData<AuditExcessItem> tagModel = new MutableLiveData<>();
    public AuditItemViewModel() {
    }
    public void setValue(AuditExcessItem trItems) {
        tagModel.setValue(trItems);
        assetNumber.setValue(trItems.assetnumber);
        assetName.setValue(trItems.assetname);
        qrCode.setValue(trItems.qrcode);

    }
}
