package com.ams.amsvistara.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.constants.AppConstant;

public class CheckNetwork {

    Context context;

    public CheckNetwork(Context context) {
        this.context = context;
    }

    // Network Check
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void registerNetworkCallback() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();

            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                                                                   @Override
                                                                   public void onAvailable(Network network) {
                                                                   //      Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show();
                                                                       AppConstant.isNetworkConnected = true; // Global Static Variable
                                                                   }

                                                                   @Override
                                                                   public void onLost(Network network) {
                                                                       Toast.makeText(context, "Not Connected", Toast.LENGTH_SHORT).show();
                                                                       AppConstant.isNetworkConnected = false; // Global Static Variable
                                                                   }
                                                               }

            );
            AppConstant.isNetworkConnected = false;
        } catch (Exception e) {
            AppConstant.isNetworkConnected = false;
        }
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) AMSApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
}
