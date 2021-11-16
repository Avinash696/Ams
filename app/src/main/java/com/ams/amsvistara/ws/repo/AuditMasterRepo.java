package com.ams.amsvistara.ws.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import com.ams.amsvistara.model.res.AuditMasterRes;
import com.ams.amsvistara.model.updatedModels.NewAuditMaster;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class AuditMasterRepo implements IAuditMasterRepo {
    private volatile static AuditMasterRepo INSTANCE = null;
    private static String TAG = "auditData";


    private AuditMasterRepo() {
    }

    public static AuditMasterRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (AuditMasterRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AuditMasterRepo();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getAuditMaster(Map<String, String> req, @NonNull AuditMasterCallback callback) {
        String url = SUB_URL+"/api/auditmasters";
//        String url = "http://20.198.101.6/Vistara/WebAPI/api/auditmasters";
//        RestService.getApiServiceOther().WS_GET_AUDIT_IDS(url,req).enqueue(new Callback<AuditMasterRes>() {
//            @Override
//            public void onResponse(Call<AuditMasterRes> call, Response<AuditMasterRes> response) {
//                callback.onDataLoaded(response.body());
//                Log.d(TAG, "onResponse: "+response.body());
//            }
//            @Override
//            public void onFailure(Call<AuditMasterRes> call, Throwable t) {
//                callback.onDataNotAvailable();
//                Log.d(TAG, "onFailure:"+url);
//            }
//        });



//      RestService.getApiServiceOther().WS_GET_AUDIT_IDS(url,req).enqueue(new Callback<NewAuditMaster>() {
//          @Override
//          public void onResponse(Call<NewAuditMaster> call, Response<NewAuditMaster> response) {
//              callback.onDataLoaded(response.body());
//              Log.d(TAG, "onResponse: "+response.body());
//          }
//
//          @Override
//          public void onFailure(Call<NewAuditMaster> call, Throwable t) {
//              Log.d(TAG, "onFailure: "+url);
//          }
//      });
      RestService.getApiServiceOther().WS_GET_AUDIT_IDS(url,req).enqueue(new Callback<List<NewAuditMaster>>() {
          @Override
          public void onResponse(Call<List<NewAuditMaster>> call, Response<List<NewAuditMaster>> response) {
              List<NewAuditMaster> resData = response.body();
              callback.onDataLoaded( resData.get(0));
              Log.d(TAG, "onResponse: "+resData.get(0).getAuditCode());
          }

          @Override
          public void onFailure(Call<List<NewAuditMaster>> call, Throwable t) {
              Log.d(TAG, "onFailure: "+t.getMessage());
          }
      });

    }
}
