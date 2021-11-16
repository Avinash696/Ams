package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.model.res.TagAssetRes;

import java.util.Map;

public interface ITagAssetRepo {
    interface TagAssetCallback {
        void onDataLoaded(TagAssetRes tagAssetRes);

        void onDataNotAvailable();
    }
    void postTagAsset(Map<String, String> req, TagItems tagItem, @NonNull TagAssetCallback callback);
}
