package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.MasterListModel;
import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.NewAsset;
import com.ams.amsvistara.model.updatedModels.NewAssetResponce;

import java.util.Map;

public interface IAddNewAssetRepo {
    interface AddNewAssetCallback {
        void onDataLoaded(NewAssetResponce responce);

        void onDataNotAvailable();
    }
//    void postAsset(MasterListModel assetMaster, @NonNull AddNewAssetCallback callback);
    void postAsset(NewAsset newAsset, @NonNull AddNewAssetCallback callback);

}
