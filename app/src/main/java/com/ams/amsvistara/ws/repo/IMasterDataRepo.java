package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.db.table.master.MasterData;

import java.util.Map;

public interface IMasterDataRepo {
    interface MasterDataCallback {
        void onDataLoaded(MasterData masterData);

        void onDataNotAvailable();
    }
    void getMasterData(Map<String, String> req, @NonNull MasterDataCallback callback);
}
