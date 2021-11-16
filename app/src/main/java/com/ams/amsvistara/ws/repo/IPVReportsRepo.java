package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.PVReports;
import com.ams.amsvistara.model.TAModel;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.TAPostRes;

import java.util.Map;

public interface IPVReportsRepo {
    interface PVReportsCallback {
        void onDataLoaded(PVReports PVReports);

        void onDataNotAvailable();
    }
    void getPVReports(Map<String, String> req, @NonNull PVReportsCallback callback);
}
