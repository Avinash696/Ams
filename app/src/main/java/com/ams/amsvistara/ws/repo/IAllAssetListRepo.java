package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.res.AuditExcessItem;
import com.ams.amsvistara.model.updatedModels.AssetMaster;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

public interface IAllAssetListRepo {
    interface  AllAssetListCallback {
        void onDataLoaded(AssetMaster responseBody);

        void onDataNotAvailable();
    }
    void getAllAssetList( @NonNull AllAssetListCallback callback);
}
