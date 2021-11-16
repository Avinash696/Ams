package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.NewAssetResponce;
import com.ams.amsvistara.model.updatedModels.NewDisposalModel;

import java.util.Map;

public interface IDisposalReqRepo {
    interface DisposeRequestCallback {
        void onDataLoaded(NewAssetResponce responce);

        void onDataNotAvailable();
    }
    void postDisposeReq( NewDisposalModel disposalModel, @NonNull DisposeRequestCallback callback);
}
