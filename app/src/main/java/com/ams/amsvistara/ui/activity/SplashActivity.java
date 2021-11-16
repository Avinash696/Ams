package com.ams.amsvistara.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ams.amsvistara.BuildConfig;
import com.ams.amsvistara.R;
import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.constants.AppConstant;
import com.ams.amsvistara.utils.CheckNetwork;
import com.ams.amsvistara.utils.SharedPrefUtil;

import static com.ams.amsvistara.constants.AppConstant.BASE_URL;
import static com.ams.amsvistara.constants.AppConstant.SUB_URL;
import static com.ams.amsvistara.constants.AppConstant.android_id;
import static com.ams.amsvistara.constants.ConstantStr.SETTING_IP;
import static com.ams.amsvistara.constants.ConstantStr.SETTING_SUB;
import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class SplashActivity extends AppCompatActivity {

    private TextView txtAppVersion;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        txtAppVersion=(TextView) findViewById(R.id.txtAppVersion);
        txtAppVersion.setText("V:"+ BuildConfig.VERSION_NAME);
       android_id = Settings.Secure.getString(AMSApplication.instance.getContentResolver(),
                Settings.Secure.ANDROID_ID)+"-"+Build.SERIAL;

       Log.e("####","#### android_id ###### "+android_id);

        try {
            AppConstant.DEVICE_MODEL = Build.MODEL;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    CheckNetwork network = new CheckNetwork(getApplicationContext());
                    network.registerNetworkCallback();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                CheckNetwork network = new CheckNetwork(getApplicationContext());
                AppConstant.isNetworkConnected = network.isConnected();
            }

            BASE_URL = "http://" + SharedPrefUtil.getString(SETTING_IP, "", SplashActivity.this);
            SUB_URL= SharedPrefUtil.getString(SETTING_SUB, "", SplashActivity.this);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!TextUtils.isEmpty(SharedPrefUtil.getString(USER_TOKEN, "", SplashActivity.this))) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, SettingIpActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}