package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.TAModel;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.TAPostRes;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class TARepo implements ITARepo {
    private volatile static TARepo INSTANCE = null;


    private TARepo() {
    }

    public static TARepo getInstance() {
        if (INSTANCE == null) {
            synchronized (TARepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TARepo();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getTA(Map<String, String> req, @NonNull TACallback callback) {
        String url = SUB_URL+"/api/inboundtransfermasters";
        RestService.getApiServiceOther().WS_GET_TRANSFER_ASSET(url,req).enqueue(new Callback<TAModel>() {
            @Override
            public void onResponse(Call<TAModel> call, Response<TAModel> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<TAModel> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }


    @Override
    public void postTA(Map<String, String> req, TRItems trItems, @NonNull TAPostCallback callback) {
        String url = SUB_URL+"/api/receiveasset";
    RestService.getApiServiceOther().WS_POST_TRANSFER_ASSET(url,trItems,req).enqueue(new Callback<TAPostRes>() {
        @Override
        public void onResponse(Call<TAPostRes> call, Response<TAPostRes> response) {
            callback.onDataLoaded(response.body());
        }

        @Override
        public void onFailure(Call<TAPostRes> call, Throwable t) {

        }
    });

    }


}
