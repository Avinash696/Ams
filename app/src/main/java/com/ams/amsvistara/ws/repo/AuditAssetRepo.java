package com.ams.amsvistara.ws.repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.req.AuditExcess;
import com.ams.amsvistara.model.res.AuditExcessItem;
import com.ams.amsvistara.model.res.AuditExcessRes;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class AuditAssetRepo implements IAuditAssetRepo {
    private volatile static AuditAssetRepo INSTANCE = null;


    private AuditAssetRepo() {
    }

    public static AuditAssetRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (AuditAssetRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AuditAssetRepo();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void postAuditAsset(Map<String, String> req, List<AuditExcessItem> auditExcessItem, @NonNull AuditAssetCallback callback) {
        String url = SUB_URL+"/api/bulkauditasset";
        RestService.getApiServiceOther().WS_POST_AUDIT_SAVE(url,auditExcessItem,req).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("###", "###### response.body() ######## " + response.body());
                callback.onDataLoaded(response.body());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }
/*
    @Override
    public void postAuditAsset(Map<String, String> req, List<AuditExcessItem> auditExcessItem, @NonNull IAuditAssetRepo.AuditAssetCallback callback) {
        String url = "/api.trtclientcenter/api/bulkauditasset";
        RestService.getApiServiceOther().WS_POST_AUDIT_SAVE(url,auditExcessItem,req).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("###", "###### response.body() ######## " + response.body());
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }*/

}
