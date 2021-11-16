package com.ams.amsvistara.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ams.amsvistara.BuildConfig;
import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.ActivitySettingIpBinding;
import com.ams.amsvistara.model.IpAddressModel;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.viewmodel.SettingIpViewModel;

import java.util.Objects;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.ams.amsvistara.constants.AppConstant.BASE_URL;
import static com.ams.amsvistara.constants.AppConstant.SUB_URL;
import static com.ams.amsvistara.constants.ConstantStr.SETTING_IP;
import static com.ams.amsvistara.constants.ConstantStr.SETTING_SUB;

public class SettingIpActivity extends AppCompatActivity {
    ActivitySettingIpBinding binding;
    SettingIpViewModel settingIpViewModel;

    private static final int RequestPermissionCode = 202;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_setting_ip);


        settingIpViewModel = new ViewModelProvider(this).get(SettingIpViewModel.class);

        binding = DataBindingUtil.setContentView(SettingIpActivity.this, R.layout.activity_setting_ip);

        binding.setLifecycleOwner(this);

        binding.setViewModel(settingIpViewModel);

        binding.txtAppVersion.setText("V:"+ BuildConfig.VERSION_NAME);
        //  Log.e("####","SharedPrefUtil -- "+SharedPrefUtil.getString(SETTING_IP, "", SettingIpActivity.this));
        if (SharedPrefUtil.getString(SETTING_IP, "", SettingIpActivity.this).length() > 0) {

                settingIpViewModel.IpAddress.setValue("" + SharedPrefUtil.getString(SETTING_IP, "", SettingIpActivity.this));
                //   binding.txtIpAddress.setText("" + SharedPrefUtil.getString(SETTING_IP, "", SettingIpActivity.this));

        }
        Log.e("####","###### SUB URL ##### "+SharedPrefUtil.getString(SETTING_SUB, "", SettingIpActivity.this));
        if (SharedPrefUtil.getString(SETTING_SUB, "", SettingIpActivity.this).length() > 0) {
            settingIpViewModel.SubAddress.setValue("" + SharedPrefUtil.getString(SETTING_SUB, "", SettingIpActivity.this));
        }

        if (CheckingPermissionIsEnabledOrNot()) {

        } else {
            RequestMultiplePermission();

        }
        settingIpViewModel.getAddress().observe(this, new Observer<IpAddressModel>() {
            @Override
            public void onChanged(IpAddressModel ipAddressModel) {
                if (TextUtils.isEmpty(Objects.requireNonNull(ipAddressModel).getStrIpAddress())) {
                    binding.txtIpAddress.setError("Enter an Ip Address");
                    binding.txtIpAddress.requestFocus();
                }else   if (TextUtils.isEmpty(Objects.requireNonNull(ipAddressModel).getStrSubAddress())) {
                    binding.txtSubAddress.setError("Enter an Sub Address");
                    binding.txtSubAddress.requestFocus();
                }  else {
                    if (CheckingPermissionIsEnabledOrNot()) {
                        if (turnOnLocationService()) {
                            binding.progressBar.setVisibility(View.VISIBLE);
                            SharedPrefUtil.putString(SETTING_IP, ipAddressModel.getStrIpAddress(), SettingIpActivity.this);
                            SharedPrefUtil.putString(SETTING_SUB, ipAddressModel.getStrSubAddress(), SettingIpActivity.this);
                            BASE_URL = "http://" + ipAddressModel.getStrIpAddress().trim();
                            SUB_URL = "" + ipAddressModel.getStrSubAddress().trim();
                            Intent intent = new Intent(SettingIpActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(SettingIpActivity.this,"GPS Network Not Enabled. Please Turn On the Location.",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        RequestMultiplePermission();

                    }

                }
            }
        });


    }

    private boolean turnOnLocationService() {
        LocationManager lm = (LocationManager) SettingIpActivity.this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            new AlertDialog.Builder(this)
                    .setMessage("GPS Network Not Enabled. Please Turn On the Location.")
                    .setPositiveButton("Open Location Setting", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            SettingIpActivity.this.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
        return gps_enabled;
    }

    private void RequestMultiplePermission() {

        ActivityCompat.requestPermissions(SettingIpActivity.this, new String[]
                {
                        CAMERA,
                        READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE,
                        ACCESS_FINE_LOCATION,
                        ACCESS_COARSE_LOCATION,
                        ACCESS_NETWORK_STATE
                }, RequestPermissionCode);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean READ_EXTERNAL_STORAGE = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean WRITE_EXTERNAL_STORAGE = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean ACCESS_FINE_LOCATION = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean ACCESS_COARSE_LOCATION = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean ACCESS_NETWORK_STATE = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    if (CameraPermission && READ_EXTERNAL_STORAGE && WRITE_EXTERNAL_STORAGE && ACCESS_FINE_LOCATION && ACCESS_COARSE_LOCATION && ACCESS_NETWORK_STATE) {

                        //    Toast.makeText(SettingIpActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SettingIpActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    public boolean CheckingPermissionIsEnabledOrNot() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int ForthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int FifthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        int SixthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_NETWORK_STATE);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ForthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FifthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SixthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }
}