package com.ams.amsvistara.ws.repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.updatedModels.NewAssetResponce;
import com.ams.amsvistara.model.updatedModels.NewDisposalModel;
import com.ams.amsvistara.model.updatedModels.RestApiModel;
import com.ams.amsvistara.ws.retrofit.RestService;
import com.ams.amsvistara.ws.retrofit.RetrofitHelper;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisposalReqRepo implements IDisposalReqRepo{
    private volatile static AllAssetListRepo INSTANCE = null;

    public static AllAssetListRepo getInstance() {
        synchronized (AllAssetListRepo.class) {
            if (INSTANCE == null) {
                INSTANCE = new AllAssetListRepo();
            }
        }
        return INSTANCE;
    }

    @Override
    public void postDisposeReq( NewDisposalModel disposalModel, @NonNull DisposeRequestCallback callback) {
        String url = "http://20.198.101.6/Vistara/MobileApi/NewDisposal";
//        RestService.getApiServiceOther().postDisposeReq(url, disposalModel, req).enqueue(new Callback<NewAssetResponce>() {
//            @Override
//            public void onResponse(Call<NewAssetResponce> call, Response<NewAssetResponce> response) {
//                callback.onDataLoaded(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<NewAssetResponce> call, Throwable t) {
//                callback.onDataNotAvailable();
//            }
//        });

        RetrofitHelper.getClient().postDisposeReq(disposalModel).enqueue(new Callback<NewAssetResponce>() {
            @Override
            public void onResponse(Call<NewAssetResponce> call, Response<NewAssetResponce> response) {
                callback.onDataLoaded(response.body());
//                Log.d("rawatt", "onResponse: ");
            }

            @Override
            public void onFailure(Call<NewAssetResponce> call, Throwable t) {
                callback.onDataNotAvailable();
//                Log.d("rawattt", "onResponse: ");

            }
        });
    }
}
