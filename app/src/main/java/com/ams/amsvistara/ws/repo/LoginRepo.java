package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class LoginRepo implements ILoginRepo {
    private volatile static LoginRepo INSTANCE = null;


    private LoginRepo() {
    }

    public static LoginRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (LoginRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginRepo();
                }
            }
        }
        return INSTANCE;
    }
/*
    @Override
    public void getBPList(@NonNull BPReq req, @NonNull LoadDataCallback callback) {
        String url = "/api/Expense/GetCompletedBeatPlans";
        RestService.getApiService().WS_GET_BP(url, req).enqueue(new Callback<BPRes>() {
            @Override
            public void onResponse(@NonNull Call<BPRes> call, @NonNull Response<BPRes> response) {
                if (response.body() != null && response.body().Data != null && response.body().Data.BeatPlanner != null) {
                    callback.onDataLoaded(response.body().Data.BeatPlanner);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BPRes> call, @NonNull Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable();

            }
        });
    }*/

    @Override
    public void getLogin(@NonNull Map<String, String> req, @NonNull LoginCallback callback) {
        String url = SUB_URL+"/login";
        RestService.getApiService(req).WS_POST_LOGIN(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //Log.e("####","########### LoginReqModel "+response.headers().get("Token"));
                if (response.body() != null) {
                    callback.onDataLoaded(response);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
