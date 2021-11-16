package com.ams.amsvistara.ws.repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.res.LogoutRes;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class LogoutRepo implements ILogoutRepo {
    private volatile static LogoutRepo INSTANCE = null;


    private LogoutRepo() {
    }

    public static LogoutRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (LogoutRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LogoutRepo();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getLogout(Map<String, String> req, @NonNull LogoutCallback callback) {
        String url = SUB_URL+"/api/logout";
        RestService.getApiServiceOther().WS_POST_LOGOUT(url,req).enqueue(new Callback<LogoutRes>() {
            @Override
            public void onResponse(Call<LogoutRes> call, Response<LogoutRes> response) {

                if(response.code()==200) {
                    if (response.body() != null && response.body() != null && response.body() != null) {
                        callback.onDataLoaded(response.body());
                    } else {
                        callback.onDataNotAvailable();
                    }
                }else {
                    Log.e("response","##############Dashboard "+response.code());

                }
            }

            @Override
            public void onFailure(Call<LogoutRes> call, Throwable t) {

            }
        });
    }
}
