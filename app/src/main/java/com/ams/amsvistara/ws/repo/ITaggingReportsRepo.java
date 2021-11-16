package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.PVReports;
import com.ams.amsvistara.model.TaggingReports;

import java.util.Map;

public interface ITaggingReportsRepo {
    interface TaggingReportsCallback {
        void onDataLoaded(TaggingReports TaggingReports);

        void onDataNotAvailable();
    }
    void getTaggingReports(Map<String, String> req, @NonNull TaggingReportsCallback callback);
}
