package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.TAModel;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.AuditMasterRes;
import com.ams.amsvistara.model.res.AuditMasters;
import com.ams.amsvistara.model.res.TAPostRes;
import com.ams.amsvistara.model.updatedModels.NewAuditMaster;

import java.util.Map;

public interface IAuditMasterRepo {
//    interface AuditMasterCallback {
//        void onDataLoaded(AuditMasterRes auditMasters);
//
//        void onDataNotAvailable();
//    }
//    interface AuditMasterPostCallback {
//        void onDataLoaded(AuditMasterRes auditMasters);
//
//        void onDataNotAvailable();
//    }
//    void getAuditMaster(Map<String, String> req, @NonNull AuditMasterCallback callback);


    //new model
    interface AuditMasterCallback {
        void onDataLoaded(NewAuditMaster newAuditMaster);

        void onDataNotAvailable();
    }
        interface AuditMasterPostCallback {
        void onDataLoaded(NewAuditMaster newAuditMaster);

        void onDataNotAvailable();
    }
    void getAuditMaster(Map<String, String> req, @NonNull AuditMasterCallback callback);

}
