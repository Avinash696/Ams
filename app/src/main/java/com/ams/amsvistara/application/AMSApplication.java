package com.ams.amsvistara.application;


import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.ams.amsvistara.ws.repo.AllAssetListRepo;
import com.ams.amsvistara.ws.retrofit.APIService;
import com.ams.amsvistara.ws.retrofit.RetrofitHelper;

   public class AMSApplication extends Application {

    public static AMSApplication instance = null;
    public static APIService  apiService= null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MultiDex.install(this);
    }
       public static Context getInstance() {
        if (null == instance) {
            instance = new AMSApplication();
        }
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}