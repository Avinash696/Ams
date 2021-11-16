package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.PVReports;
import com.ams.amsvistara.model.TAModel;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.TAPostRes;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class PVReportsRepo implements IPVReportsRepo {
    private volatile static PVReportsRepo INSTANCE = null;


    private PVReportsRepo() {
    }

    public static PVReportsRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (PVReportsRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PVReportsRepo();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getPVReports(Map<String, String> req, @NonNull PVReportsCallback callback) {
        String url = SUB_URL+"/api/pvreport";
        RestService.getApiServiceOther().WS_GET_PV_REPORTS(url,req).enqueue(new Callback<PVReports>() {
            @Override
            public void onResponse(Call<PVReports> call, Response<PVReports> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<PVReports> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }


   /* @Override
    public void getR(Map<String, String> req, @NonNull PVReportsCallback callback) {
        String url = "/api.trtclientcenter/api/inboundtransfermasters";
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

    }*/


}
