package com.ams.amsvistara.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.RestApiModel;
import com.ams.amsvistara.ws.repo.AllAssetListRepo;

import com.ams.amsvistara.ws.retrofit.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAssetsListViewModel extends ViewModel {

    public MutableLiveData<AssetMaster> masterMutableLiveData = new MutableLiveData<>();;
    public MutableLiveData<List<AssetMaster>> restApiModelMutableLiveData = new MutableLiveData<>();;
    public MutableLiveData<String> AssetName = new MutableLiveData<>();
    public MutableLiveData<String> AssetCode = new MutableLiveData<>();

    public void setValue(AssetMaster assetMaster){
        masterMutableLiveData.setValue(assetMaster);
        AssetName.setValue(assetMaster.AssetName);
        AssetCode.setValue(assetMaster.AssetCode);
    }
    //new Viwemodel

//    public ArrayList<AssetMaster> featchMasterListData() {
//        AssetMaster assetMaster = new ArrayList<>();
//        assetMaster.
//
//    }
    public void setListValue(){
        RetrofitHelper.getClient().getAllAssets().enqueue(new Callback<RestApiModel>() {
            @Override
            public void onResponse(Call<RestApiModel> call, Response<RestApiModel> response) {
//                restApiModelMutableLiveData.setValue(response.body().AssetMasters);
                masterMutableLiveData.setValue((AssetMaster) response.body().AssetMasters);
            }

            @Override
            public void onFailure(Call<RestApiModel> call, Throwable t) {

            }
        });
    }

}
