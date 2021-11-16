package com.ams.amsvistara.ws.repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ams.amsvistara.db.table.master.MasterData;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;

public class MasterDataRepo implements IMasterDataRepo {
    private volatile static MasterDataRepo INSTANCE = null;


    private MasterDataRepo() {
    }

    public static MasterDataRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (MasterDataRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MasterDataRepo();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getMasterData(Map<String, String> req, @NonNull IMasterDataRepo.MasterDataCallback callback) {
        String url = SUB_URL+"/api/masters";
        RestService.getApiServiceOther().WS_GET_MASTER_DATA(url,req).enqueue(new Callback<MasterData>() {
            @Override
            public void onResponse(Call<MasterData> call, Response<MasterData> response) {

                if(response.code()==200) {
                    if (response.body() != null && response.body() != null && response.body() != null) {
                        Log.e("response","##############getMaster "+response.toString());
                        callback.onDataLoaded(response.body());
                    } else {
                        callback.onDataNotAvailable();
                    }
                }else {
                    if(response.code()==401){
                    //    Toast.makeText(AMSApplication.instance, "Your session has expired. Please re-login", Toast.LENGTH_SHORT).show();
                    }
                    callback.onDataNotAvailable();
                    Log.e("response","##############Master "+response.code());
                  //  MasterData masterData=new MasterData();
            //        dashboard.responsemessage="0";
                 //   callback.onDataLoaded(dashboard);
                }
            }

            @Override
            public void onFailure(Call<MasterData> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }
}
