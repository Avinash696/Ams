package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.res.LogoutRes;

import java.util.Map;

public interface ILogoutRepo {
    interface LogoutCallback {
        void onDataLoaded(LogoutRes logoutRes);

        void onDataNotAvailable();
    }
    void getLogout(Map<String, String> req, @NonNull LogoutCallback callback);
}
