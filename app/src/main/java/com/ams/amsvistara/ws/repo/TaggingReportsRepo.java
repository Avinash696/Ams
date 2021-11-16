package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.model.PVReports;
import com.ams.amsvistara.model.TaggingReports;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class TaggingReportsRepo implements ITaggingReportsRepo {
    private volatile static TaggingReportsRepo INSTANCE = null;


    private TaggingReportsRepo() {
    }

    public static TaggingReportsRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (TaggingReportsRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaggingReportsRepo();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getTaggingReports(Map<String, String> req, @NonNull TaggingReportsCallback callback) {
        String url = SUB_URL+"/api/tagreport";
        RestService.getApiServiceOther().WS_GET_TAGGING_REPORTS(url,req).enqueue(new Callback<TaggingReports>() {
            @Override
            public void onResponse(Call<TaggingReports> call, Response<TaggingReports> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<TaggingReports> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }


/*

    @Override
    public void getPVReports(Map<String, String> req, @NonNull PVReportsCallback callback) {
        String url = "/api.trtclientcenter/api/inboundtransfermasters";
        RestService.getApiServiceOther().WS_GET_PV_REPORTS(url,req).enqueue(new Callback<PVReports>() {
            @Override
            public void onResponse(Call<PVReports> call, Response<PVReports> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<PVReports> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

*/



}
