package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.MasterListModel;
import com.ams.amsvistara.model.TAModel;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.TAPostRes;
import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.NewAsset;
import com.ams.amsvistara.model.updatedModels.NewAssetResponce;
import com.ams.amsvistara.ws.retrofit.RestService;
import com.ams.amsvistara.ws.retrofit.RetrofitHelper;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class AddNewAssetRepo implements IAddNewAssetRepo {
    private volatile static AddNewAssetRepo INSTANCE = null;


    private AddNewAssetRepo() {
    }

    public static AddNewAssetRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (TARepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AddNewAssetRepo();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void postAsset(NewAsset newAsset, @NonNull AddNewAssetCallback callback) {
//        String url = "http://20.198.101.6/Vistara/MobileApi/NewAsset";
//        RestService.getApiServiceOther().postAddAsset( assetMaster).enqueue(new Callback<NewAssetResponce>() {
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

        RetrofitHelper.getClient().postAddAsset(newAsset).enqueue(new Callback<NewAssetResponce>() {
            @Override
            public void onResponse(Call<NewAssetResponce> call, Response<NewAssetResponce> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<NewAssetResponce> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }


}
