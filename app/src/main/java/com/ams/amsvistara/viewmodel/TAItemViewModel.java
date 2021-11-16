package com.ams.amsvistara.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.model.TRItems;


public class TAItemViewModel extends ViewModel {
    public MutableLiveData<String> tagCode = new MutableLiveData<>();
    public MutableLiveData<String> tagName = new MutableLiveData<>();
    public MutableLiveData<TRItems> tagModel = new MutableLiveData<>();
    public TAItemViewModel() {
    }

    public void setValue(TRItems trItems) {
        tagModel.setValue(trItems);
        tagCode.setValue(trItems.assetnumber);
        tagName.setValue(trItems.assetname);

    }
}
