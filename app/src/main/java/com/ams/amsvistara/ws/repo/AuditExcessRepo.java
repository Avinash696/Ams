package com.ams.amsvistara.ws.repo;

import android.util.Log;

import androidx.annotation.NonNull;


import com.ams.amsvistara.model.req.AuditExcess;
import com.ams.amsvistara.model.res.AuditExcessRes;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class AuditExcessRepo implements IExcessAuditRepo {
    private volatile static AuditExcessRepo INSTANCE = null;


    private AuditExcessRepo() {
    }

    public static AuditExcessRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (AuditExcessRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AuditExcessRepo();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void postExcessAudit(Map<String, String> req, AuditExcess auditExcess, @NonNull IExcessAuditRepo.ExcessAuditCallback callback) {
        String url = SUB_URL+"/api/auditexcess";
        RestService.getApiServiceOther().WS_POST_EXCESS_AUDIT(url,auditExcess,req).enqueue(new Callback<AuditExcessRes>() {
            @Override
            public void onResponse(Call<AuditExcessRes> call, Response<AuditExcessRes> response) {
                Log.e("###", "###### response.body() ######## " + response.body());
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<AuditExcessRes> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }

}
