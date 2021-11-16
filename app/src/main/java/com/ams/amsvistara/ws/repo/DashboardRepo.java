package com.ams.amsvistara.ws.repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.model.res.Dashboard;
import com.ams.amsvistara.ui.activity.MainActivity;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.SUB_URL;
import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class DashboardRepo implements IDashboardRepo {
    private volatile static DashboardRepo INSTANCE = null;


    private DashboardRepo() {
    }

    public static DashboardRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (DashboardRepo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DashboardRepo();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getDashboard(Map<String, String> req, @NonNull DashboardCallback callback) {
        String url = SUB_URL+"/api/appmenus";
        RestService.getApiServiceOther().WS_GET_DASHBOARD(url,req).enqueue(new Callback<Dashboard>() {
            @Override
            public void onResponse(Call<Dashboard> call, Response<Dashboard> response) {

                if(response.code()==200) {
                    if (response.body() != null && response.body() != null && response.body() != null) {
                        callback.onDataLoaded(response.body());
                    } else {
                        callback.onDataNotAvailable();
                    }
                }else {
                    Log.e("response","##############Dashboard "+response.code());
                    if(response.code()==401){
                        SharedPrefUtil.putString(USER_TOKEN,"", AMSApplication.instance);
                    //    Toast.makeText(AMSApplication.instance, "Your session has expired. Please re-login", Toast.LENGTH_SHORT).show();
                    }
                    Dashboard dashboard=new Dashboard();
                    dashboard.responsemessage="0";
                    callback.onDataLoaded(dashboard);
                }
            }

            @Override
            public void onFailure(Call<Dashboard> call, Throwable t) {

            }
        });
    }
}
