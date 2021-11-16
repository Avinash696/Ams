package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.model.res.PvAssetRes;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class PvAssetRepo implements IPvAssetRepo {
    private volatile static PvAssetRepo INSTANCE = null;


    private PvAssetRepo() {
    }

    public static PvAssetRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (PvAssetRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PvAssetRepo();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void postPvAsset(Map<String, String> req, PvItems tagItem, @NonNull IPvAssetRepo.PvAssetCallback callback) {
        String url = SUB_URL+"/api/pvasset";
        RestService.getApiServiceOther().WS_POST_PV_ASSETS(url,tagItem,req).enqueue(new Callback<PvAssetRes>() {
            @Override
            public void onResponse(Call<PvAssetRes> call, Response<PvAssetRes> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<PvAssetRes> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }

/*
    @Override
    public void getMasterData(Map<String, String> req, @NonNull MasterDataCallback callback) {
        String url = "/api.trtclientcenter/api/masters";
        RestService.getApiServiceOther().WS_GET_MASTER_DATA(url,req).enqueue(new Callback<MasterData>() {
            @Override
            public void onResponse(Call<MasterData> call, Response<MasterData> response) {

                if(response.code()==200) {
                    if (response.body() != null && response.body() != null && response.body() != null) {
                        Log.e("response","############## "+response.toString());
                        callback.onDataLoaded(response.body());
                    } else {
                        callback.onDataNotAvailable();
                    }
                }else {
                    Log.e("response","############## "+response.code());
                  //  MasterData masterData=new MasterData();
            //        dashboard.responsemessage="0";
                 //   callback.onDataLoaded(dashboard);
                }
            }

            @Override
            public void onFailure(Call<MasterData> call, Throwable t) {

            }
        });
    }*/
}
