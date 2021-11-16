package com.ams.amsvistara.ws.repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.NewAssetResponce;
import com.ams.amsvistara.model.updatedModels.NewDisposalModel;
import com.ams.amsvistara.model.updatedModels.NewTransferReq;
import com.ams.amsvistara.ws.retrofit.RestService;
import com.ams.amsvistara.ws.retrofit.RetrofitHelper;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferReqRepo implements ITransferReqRepo {
    private volatile static TransferReqRepo INSTANCE = null;


    private TransferReqRepo() {
    }

    public static TransferReqRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (TARepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TransferReqRepo();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void postTransferReq( NewTransferReq transferReq, @NonNull TransferReqRepoCallback callback) {
        String url = "http://20.198.101.6/Vistara/MobileApi/NewTransfer";
//        RestService.getApiServiceOther().postTransferReq(url, transferReq, req).enqueue(new Callback<NewAssetResponce>() {
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

        RetrofitHelper.getClient().postTransferReq(transferReq).enqueue(new Callback<NewAssetResponce>() {
            @Override
            public void onResponse(Call<NewAssetResponce> call, Response<NewAssetResponce> response) {
                callback.onDataLoaded(response.body());
//                Log.d("rawattt", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<NewAssetResponce> call, Throwable t) {
                callback.onDataNotAvailable();
//                Log.d("rawattt", "onResponse: ");

            }
        });
    }
}
