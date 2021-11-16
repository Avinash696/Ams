package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.db.table.excess.PvExcessReq;
import com.ams.amsvistara.model.res.ExcessRes;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class ExcessPvRepo implements IExcessPvRepo {
    private volatile static ExcessPvRepo INSTANCE = null;


    private ExcessPvRepo() {
    }

    public static ExcessPvRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (ExcessPvRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ExcessPvRepo();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void postExcessPv(Map<String, String> req, PvExcessReq pvExcessReq, @NonNull ExcessCallback callback) {
        String url = SUB_URL+"/api/pvexcess";
        RestService.getApiServiceOther().WS_POST_EXCESS_PV(url,pvExcessReq,req).enqueue(new Callback<ExcessRes>() {
            @Override
            public void onResponse(Call<ExcessRes> call, Response<ExcessRes> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ExcessRes> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }
}
