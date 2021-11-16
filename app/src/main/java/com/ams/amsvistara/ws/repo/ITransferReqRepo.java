package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.updatedModels.NewAssetResponce;
import com.ams.amsvistara.model.updatedModels.NewDisposalModel;
import com.ams.amsvistara.model.updatedModels.NewTransferReq;

import java.util.Map;

public interface ITransferReqRepo {
    interface TransferReqRepoCallback{
        void onDataLoaded(NewAssetResponce responce);

        void onDataNotAvailable();
    }
    void postTransferReq( NewTransferReq transferReq, @NonNull TransferReqRepoCallback callback);
}
