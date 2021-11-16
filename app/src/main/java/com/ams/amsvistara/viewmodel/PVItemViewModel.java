package com.ams.amsvistara.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.db.table.master.PvItems;


public class PVItemViewModel extends ViewModel {
    public MutableLiveData<String> tagCode = new MutableLiveData<>();
    public MutableLiveData<String> tagName = new MutableLiveData<>();
    public MutableLiveData<PvItems> tagModel = new MutableLiveData<>();
    public PVItemViewModel() {
    }

    public void setValue(PvItems assetMasters) {
        tagModel.setValue(assetMasters);
        tagCode.setValue(assetMasters.AssetNumber);
        tagName.setValue(assetMasters.AssetName);

    }
}
