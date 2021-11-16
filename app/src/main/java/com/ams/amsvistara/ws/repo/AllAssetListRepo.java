package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.RestApiModel;
import com.ams.amsvistara.ws.retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAssetListRepo implements IAllAssetListRepo {
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
    public void getAllAssetList( @NonNull AllAssetListCallback callback) {
        RetrofitHelper.getClient().getAllAssets().enqueue(new Callback<RestApiModel>() {
            @Override
            public void onResponse(Call<RestApiModel> call, Response<RestApiModel> response) {
//                callback.onDataLoaded(response.body().AssetMasters);
            }

            @Override
            public void onFailure(Call<RestApiModel> call, Throwable t) {

            }
        });
    }
}
