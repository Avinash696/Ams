package com.ams.amsvistara.ws.repo;

import androidx.annotation.NonNull;

import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.model.res.TagAssetRes;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class TagAssetRepo implements ITagAssetRepo {
    private volatile static TagAssetRepo INSTANCE = null;


    private TagAssetRepo() {
    }

    public static TagAssetRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (TagAssetRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TagAssetRepo();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void postTagAsset(Map<String, String> req, TagItems tagItem, @NonNull TagAssetCallback callback) {
        String url = SUB_URL+"/api/tagasset";
        RestService.getApiServiceOther().WS_POST_TAG_ASSETS(url,tagItem,req).enqueue(new Callback<TagAssetRes>() {
            @Override
            public void onResponse(Call<TagAssetRes> call, Response<TagAssetRes> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<TagAssetRes> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }

}
