package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.model.res.PvAssetRes;

import java.util.Map;

public interface IPvAssetRepo {
    interface PvAssetCallback {
        void onDataLoaded(PvAssetRes pvAssetRes);

        void onDataNotAvailable();
    }
    void postPvAsset(Map<String, String> req, PvItems pvItem, @NonNull PvAssetCallback callback);
}
