package com.ams.amsvistara.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.TransferReqModel;
import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.NewAssetResponce;
import com.ams.amsvistara.model.updatedModels.NewTransferReq;
import com.ams.amsvistara.model.updatedModels.TRItem;
import com.ams.amsvistara.ws.repo.AddNewAssetRepo;
import com.ams.amsvistara.ws.repo.IAddNewAssetRepo;
import com.ams.amsvistara.ws.repo.ITransferReqRepo;
import com.ams.amsvistara.ws.repo.TransferReqRepo;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransferReqViewModel extends ViewModel {
    public MutableLiveData<String> trId = new MutableLiveData<>();
    public MutableLiveData<String> trCode = new MutableLiveData<>();
    public MutableLiveData<String> remark = new MutableLiveData<>();
    public MutableLiveData<String> requestDate = new MutableLiveData<>();
    public MutableLiveData<String> transferDate = new MutableLiveData<>();
    public MutableLiveData<String> status = new MutableLiveData<>();

    //spinner set
    public MutableLiveData<String> spFromLocation = new MutableLiveData<>();
    public MutableLiveData<String> spFromUser = new MutableLiveData<>();
    public MutableLiveData<String> spToLocation = new MutableLiveData<>();
    public MutableLiveData<String> spUser = new MutableLiveData<>();
    //    public MutableLiveData<TRItem> trItems = new MutableLiveData<>();
    public MutableLiveData<List<TRItem>> trItems = new MutableLiveData<>();

    //dummy with model
//     public MutableLiveData<TransferReqModel> stringMutableLiveData;
    public MutableLiveData<NewTransferReq> stringMutableLiveData;

    //status of responce
    public MutableLiveData<NewAssetResponce> responceMutableLiveData;

    //set transfer obj(singleton)
    public MutableLiveData<NewTransferReq> SetTransferReqObj() {
        if (stringMutableLiveData == null) {
            stringMutableLiveData = new MutableLiveData<>();
        }
        return stringMutableLiveData;
    }

    public void SetTransferReq() {       //model type need to define
        List<TRItem> trItemList = new ArrayList<>();
        TRItem trItem = new TRItem();
        trItem.setAssetID(2);
        trItemList.add(trItem);
        trItems.postValue(trItemList);

//        NewTransferReq transferReq = new NewTransferReq(spFromLocation.getValue(), spToLocation.getValue(), trCode.getValue(), requestDate.getValue(), transferDate.getValue(),
//                spUser.getValue(), status.getValue(), remark.getValue(), trItems.getValue());
        NewTransferReq transferReq = new NewTransferReq(spFromLocation.getValue(),spToLocation.getValue(),trCode.getValue(),"09/21/2021 11:01:01 AM","09/21/2021 11:01:01 AM",
                spUser.getValue(),status.getValue(),remark.getValue(),trItemList);
//
//        Log.d("kant", "SetTransferReq: "+transferReq.getTRItems().get(0).getAssetID());

//        NewTransferReq transferReq = new NewTransferReq(spFromLocation.getValue(),  spToLocation.getValue(),  trCode.getValue(),
//                requestDate.getValue(),  transferDate.getValue(),  spUser.getValue(),  status.getValue(), remark.getValue());
        stringMutableLiveData.setValue(transferReq);
        TransferReqRepo.getInstance().postTransferReq(transferReq, new ITransferReqRepo.TransferReqRepoCallback() {
            @Override
            public void onDataLoaded(NewAssetResponce responce) {
//                       Log.d("rawatt", "onDataLoaded: ");
//                       responceMutableLiveData.setValue(responce);
//                       responceMutableLiveData.postValue(responce);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }

//    public void setTransferReq(NewTransferReq transferReq, Map<String, String> req) {
////          stringMutableLiveData.setValue(transferReq);
//        TransferReqRepo.getInstance().postTransferReq(req, transferReq, new ITransferReqRepo.TransferReqRepoCallback() {
//            @Override
//            public void onDataLoaded(NewAssetResponce responce) {
//                Toast.makeText(AMSApplication.getInstance(), "" + responce.getResponse(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                Toast.makeText(AMSApplication.getInstance(), "No Responce", Toast.LENGTH_SHORT).show();
//            }
//        });

}
