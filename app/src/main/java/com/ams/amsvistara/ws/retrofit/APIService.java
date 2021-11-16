package com.ams.amsvistara.ws.retrofit;

import com.ams.amsvistara.db.table.excess.PvExcessReq;
import com.ams.amsvistara.db.table.master.MasterData;
import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.db.table.master.TagItems;

import com.ams.amsvistara.model.MasterListModel;
import com.ams.amsvistara.model.PVReports;
import com.ams.amsvistara.model.TAModel;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.TaggingReports;
import com.ams.amsvistara.model.req.AuditExcess;
import com.ams.amsvistara.model.res.AuditExcessItem;
import com.ams.amsvistara.model.res.AuditExcessRes;
import com.ams.amsvistara.model.res.AuditMasterRes;
import com.ams.amsvistara.model.res.Dashboard;
import com.ams.amsvistara.model.res.ExcessRes;
import com.ams.amsvistara.model.res.LogoutRes;
import com.ams.amsvistara.model.res.PvAssetRes;
import com.ams.amsvistara.model.res.TAPostRes;
import com.ams.amsvistara.model.res.TagAssetRes;

import com.ams.amsvistara.model.updatedModels.AssetMaster;

import com.ams.amsvistara.model.updatedModels.NewAsset;
import com.ams.amsvistara.model.updatedModels.NewAssetResponce;

import com.ams.amsvistara.model.updatedModels.NewAuditMaster;
import com.ams.amsvistara.model.updatedModels.NewDisposalModel;
import com.ams.amsvistara.model.updatedModels.NewTransferReq;
import com.ams.amsvistara.model.updatedModels.RestApiModel;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface APIService {

    @GET("AssetList?UserId=1&PageIndex=0&SearchText")
    Call<RestApiModel> getAllAssets();


//    @GET("AssetList?UserId=1&PageIndex=0&SearchText")
//    Call<List<updated>> getAllAssets();

    @POST("NewDisposal")
    Call<NewAssetResponce> postDisposeReq( @Body NewDisposalModel newDisposalModel);
//
//    @POST("NewTransfer")
//    Call<NewAssetResponce> postTransferReq(@Url String url, @Body NewTransferReq transferReq, @HeaderMap Map<String, String> headers);
    @POST("NewTransfer")
    Call<NewAssetResponce> postTransferReq( @Body NewTransferReq transferReq);

//    @POST("NewAsset")
//    Call<NewAssetResponce> postAddAsset(@Body MasterListModel newAsset);
    @POST("NewAsset")
    Call<NewAssetResponce> postAddAsset(@Body NewAsset newAsset);
    @POST
    Call<ResponseBody> WS_POST_LOGIN(@Url String url);

    @GET
    Call<Dashboard> WS_GET_DASHBOARD(@Url String url, @HeaderMap Map<String, String> headers);

    @GET
    Call<MasterData> WS_GET_MASTER_DATA(@Url String url, @HeaderMap Map<String, String> headers);

    @POST
    Call<TagAssetRes> WS_POST_TAG_ASSETS(@Url String url, @Body TagItems tagItem, @HeaderMap Map<String, String> headers);

    @POST
    Call<PvAssetRes> WS_POST_PV_ASSETS(@Url String url, @Body PvItems pvItem, @HeaderMap Map<String, String> headers);

    @POST
    Call<LogoutRes> WS_POST_LOGOUT(@Url String url, @HeaderMap Map<String, String> headers);

    @POST
    Call<ExcessRes> WS_POST_EXCESS_PV(@Url String url, @Body PvExcessReq pvExcessReq, @HeaderMap Map<String, String> headers);

    @GET
    Call<TAModel> WS_GET_TRANSFER_ASSET(@Url String url, @HeaderMap Map<String, String> headers);


    @POST
    Call<TAPostRes> WS_POST_TRANSFER_ASSET(@Url String url, @Body TRItems trItems, @HeaderMap Map<String, String> headers);


    @GET
    Call<PVReports> WS_GET_PV_REPORTS(@Url String url, @HeaderMap Map<String, String> headers);


    @GET
    Call<TaggingReports> WS_GET_TAGGING_REPORTS(@Url String url, @HeaderMap Map<String, String> headers);


//    @GET
//    Call<AuditMasterRes> WS_GET_AUDIT_IDS(@Url String url, @HeaderMap Map<String, String> headers);
    @GET
    Call<List<NewAuditMaster>> WS_GET_AUDIT_IDS(@Url String url, @HeaderMap Map<String, String> headers);


    @POST
    Call<AuditExcessRes> WS_POST_EXCESS_AUDIT(@Url String url, @Body AuditExcess auditExcess, @HeaderMap Map<String, String> headers);


    @POST
    Call<ResponseBody> WS_POST_AUDIT_SAVE(@Url String url, @Body List<AuditExcessItem> auditExcessItem, @HeaderMap Map<String, String> headers);
}
