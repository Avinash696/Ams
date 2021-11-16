package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.model.req.AuditExcess;
import com.ams.amsvistara.model.res.AuditExcessRes;
import com.ams.amsvistara.model.res.TagAssetRes;

import java.util.Map;

import retrofit2.http.Body;

public interface IExcessAuditRepo {
    interface ExcessAuditCallback {
        void onDataLoaded(AuditExcessRes auditExcessRes);

        void onDataNotAvailable();
    }
    void postExcessAudit(Map<String, String> req, AuditExcess auditExcess, @NonNull ExcessAuditCallback callback);
}
