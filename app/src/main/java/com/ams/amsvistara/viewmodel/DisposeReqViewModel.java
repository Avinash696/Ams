package com.ams.amsvistara.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.model.DisposeReqModel;

import com.ams.amsvistara.model.updatedModels.DRItem;
import com.ams.amsvistara.model.updatedModels.NewDisposalModel;
import com.ams.amsvistara.model.updatedModels.TRItem;

import java.util.ArrayList;
import java.util.List;

public class DisposeReqViewModel extends ViewModel {
    public MutableLiveData<String> drId = new MutableLiveData<>();
    public MutableLiveData<String> drCode = new MutableLiveData<>();
    public MutableLiveData<String> remark = new MutableLiveData<>();
    public MutableLiveData<String> requestDate = new MutableLiveData<>();
    public MutableLiveData<String> disposeDate = new MutableLiveData<>();
    public MutableLiveData<String> status = new MutableLiveData<>();
    //spinner
    public MutableLiveData<String> disposalType = new MutableLiveData<>();
    public MutableLiveData<String> location = new MutableLiveData<>();
    public MutableLiveData<String> user = new MutableLiveData<>();
//    public MutableLiveData<DisposeReqModel> disposeReqModelMutableLiveData;
    public MutableLiveData<NewDisposalModel> disposeReqModelMutableLiveData;
    public MutableLiveData<List<DRItem> > DRItemsList = new MutableLiveData<>();

//    public MutableLiveData<DisposeReqModel> getDisposeReq() {
//        if (disposeReqModelMutableLiveData == null) {
//            disposeReqModelMutableLiveData = new MutableLiveData<>();
//        }
//        return disposeReqModelMutableLiveData;
//    }

    public MutableLiveData<NewDisposalModel> getDisposeReq() {
        if (disposeReqModelMutableLiveData == null) {
            disposeReqModelMutableLiveData = new MutableLiveData<>();
        }
        return disposeReqModelMutableLiveData;
    }
    public void setDisposeReq() {
        List<DRItem> drItemsList = new ArrayList<>();
        DRItem trItem = new DRItem();
        trItem.setAsetID(2);
        drItemsList.add(trItem);
//        DRItemsList.postValue(drItemsList);
        DRItemsList.setValue(drItemsList);
//        DisposeReqModel disposeReqModel = new DisposeReqModel(drId.getValue(), drCode.getValue(), remark.getValue(),
//                requestDate.getValue(), disposeDate.getValue(), status.getValue());
//        DisposeReqModel disposeReqModel =new DisposeReqModel(drId.getValue(),drCode.getValue(),remark.getValue(),requestDate.getValue(),disposeDate.getValue(),status.getValue(),disposalType.getValue(),location.getValue(),user.getValue());
//        disposeReqModelMutableLiveData.setValue(disposeReqModel);

        NewDisposalModel disposalModel = new NewDisposalModel( DRItemsList.getValue(),drCode.getValue(),  remark.getValue(),  status.getValue(),
                user.getValue(),  drId.getValue(),  "09/21/2021 11:01:01 AM",  disposalType.getValue(),  location.getValue(),  requestDate.getValue());

//        NewDisposalModel disposalModel = new NewDisposalModel(DRItemsList.getValue(), "2", "2",  "2",
//                "2",  "2", "09/21/2021 11:01:01 AM",  "2",  "2",
//                "09/21/2021 11:01:01 AM");
//        Log.d("kant", "setDisposeReq: "+DRItemsList.getValue());
//        Log.d("kant", "setDisposeReq: "+DRItemsList.getValue(),drCode.getValue(),  remark.getValue(),  status.getValue(),
//                user.getValue(),  drId.getValue(),  "09/21/2021 11:01:01 AM",  disposalType.getValue(),  location.getValue(),  requestDate.getValue());
        disposeReqModelMutableLiveData.setValue(disposalModel);
    }
}
