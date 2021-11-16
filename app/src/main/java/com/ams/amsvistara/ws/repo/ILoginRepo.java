package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;


public interface ILoginRepo {
    interface LoginCallback {
        void onDataLoaded(Response<ResponseBody> loginReqModel);

        void onDataNotAvailable();
    }
    void getLogin(Map<String, String> req, @NonNull LoginCallback callback);
}
