package com.ams.amsvistara.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.model.PvReportsItem;
import com.ams.amsvistara.model.ReportItems;


public class PvReportsItemViewModel extends ViewModel {
    public MutableLiveData<String> assetname = new MutableLiveData<>();
    public MutableLiveData<String> assetnumber = new MutableLiveData<>();
    public MutableLiveData<String> assetdate = new MutableLiveData<>();
    public MutableLiveData<ReportItems> tagModel = new MutableLiveData<>();
    public PvReportsItemViewModel() {
    }

    public void setValue(ReportItems trItems) {
        tagModel.setValue(trItems);
        assetname.setValue(trItems.assetname);
        assetnumber.setValue(trItems.assetnumber);
        assetdate.setValue(trItems.activitydate);


    }
}
