package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.db.table.excess.PvExcessReq;
import com.ams.amsvistara.model.res.ExcessRes;

import java.util.Map;

public interface IExcessPvRepo {
    interface ExcessCallback {
        void onDataLoaded(ExcessRes excessRes);

        void onDataNotAvailable();
    }
    void postExcessPv(Map<String, String> req, PvExcessReq pvExcessReq, @NonNull ExcessCallback callback);
}
