package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.res.Dashboard;

import java.util.Map;

public interface IDashboardRepo {
    interface DashboardCallback {
        void onDataLoaded(Dashboard dashboardReqModel);

        void onDataNotAvailable();
    }
    void getDashboard(Map<String, String> req, @NonNull DashboardCallback callback);
}
