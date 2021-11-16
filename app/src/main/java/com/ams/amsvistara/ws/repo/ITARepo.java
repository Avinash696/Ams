package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.TAModel;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.TAPostRes;

import java.util.Map;

public interface ITARepo {
    interface TACallback {
        void onDataLoaded(TAModel  taModel);

        void onDataNotAvailable();
    }
    interface TAPostCallback {
        void onDataLoaded(TAPostRes taPostRes);

        void onDataNotAvailable();
    }
    void getTA(Map<String, String> req, @NonNull TACallback callback);
    void postTA(Map<String, String> req, TRItems trItems, @NonNull TAPostCallback callback);
}
