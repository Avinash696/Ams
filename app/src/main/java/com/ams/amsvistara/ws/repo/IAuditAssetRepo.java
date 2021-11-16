package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.req.AuditExcess;
import com.ams.amsvistara.model.res.AuditExcessItem;
import com.ams.amsvistara.model.res.AuditExcessRes;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

public interface IAuditAssetRepo {
    interface  AuditAssetCallback {
        void onDataLoaded(ResponseBody responseBody);

        void onDataNotAvailable();
    }
    void postAuditAsset(Map<String, String> req, List<AuditExcessItem> auditExcessItem, @NonNull AuditAssetCallback callback);
}
