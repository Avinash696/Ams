package com.ams.amsvistara.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.ui.AppBarConfiguration;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.MainGridView;
import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.constants.AppConstant;
import com.ams.amsvistara.databinding.ActivityMainBinding;
import com.ams.amsvistara.db.AppDatabase;
import com.ams.amsvistara.db.table.excess.PvExcessReq;
import com.ams.amsvistara.db.table.master.MasterData;
import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.db.table.master.PvModels;
import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.db.table.master.TagModels;
import com.ams.amsvistara.model.res.AppMenus;
import com.ams.amsvistara.model.res.Dashboard;
import com.ams.amsvistara.model.res.ExcessRes;
import com.ams.amsvistara.model.res.LogoutRes;
import com.ams.amsvistara.model.res.PvAssetRes;
import com.ams.amsvistara.model.res.TagAssetRes;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.ws.repo.DashboardRepo;
import com.ams.amsvistara.ws.repo.ExcessPvRepo;
import com.ams.amsvistara.ws.repo.IDashboardRepo;
import com.ams.amsvistara.ws.repo.IExcessPvRepo;
import com.ams.amsvistara.ws.repo.ILogoutRepo;
import com.ams.amsvistara.ws.repo.IMasterDataRepo;
import com.ams.amsvistara.ws.repo.IPvAssetRepo;
import com.ams.amsvistara.ws.repo.ITagAssetRepo;
import com.ams.amsvistara.ws.repo.LogoutRepo;
import com.ams.amsvistara.ws.repo.MasterDataRepo;
import com.ams.amsvistara.ws.repo.PvAssetRepo;
import com.ams.amsvistara.ws.repo.TagAssetRepo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.ams.amsvistara.constants.ConstantStr.ASSET_CODE;
import static com.ams.amsvistara.constants.ConstantStr.ASSET_NAME;
import static com.ams.amsvistara.constants.ConstantStr.USER_LOCATION;
import static com.ams.amsvistara.constants.ConstantStr.USER_NAME;
import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;


public class MainActivity extends AppCompatActivity implements LocationListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private static final int RequestPermissionCode = 202;
    LocationManager locationManager;
    private boolean unsavedRecord = false;
    FusedLocationProviderClient mFusedLocationClient;
    Location mLastLocation;
    private int pvTotalCount = 0;
    private int tagTotalCount = 0;
    private int pvCompleteCount = 0;
    private int tagCompleteCount = 0;
    private boolean isFABOpen;
    int masterCount = 0;
    Map<String, String> req = new HashMap<String, String>();
    private int unsavedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_main);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.userName.setText("" + SharedPrefUtil.getString(USER_NAME, "", MainActivity.this));
        binding.userLocation.setText("" + SharedPrefUtil.getString(USER_LOCATION, "", MainActivity.this));
        req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", MainActivity.this));
        // Log.e("####", "###### ");
        // unsavedRecord = AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getItemCountLive();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        if (CheckingPermissionIsEnabledOrNot()) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    getLocation();
                }
            }, 2000);
        } else {
            RequestMultiplePermission();

        }

        binding.fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.fab.callOnClick();
                binding.fab1.hide();
                binding.fab2.hide();
                checkForLocalData();
                if (unsavedRecord) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Sync Offline Record")
                            .setMessage("Offline records are available. Please sync the saved records then try refresh again.")
                            .setPositiveButton("SYNC OFFLINE RECORDS", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    try {
                                        binding.progressBar.setVisibility(View.VISIBLE);
                                        syncExcessLocalData();
                                        syncPvLocalData();
                                        syncTaggingLocalData();


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .setNegativeButton("CANCEL", null)
                            .show();
                } else {
                    pvTotalCount = 0;
                    tagTotalCount = 0;
                    pvCompleteCount = 0;
                    tagCompleteCount = 0;
                    masterCount = 0;
                    unsavedCount = 0;
                    binding.progressBar.setVisibility(View.VISIBLE);
                    AppDatabase.getAppDatabase(MainActivity.this).masterDataDao().deleteAll();
                    fetchMasterData(req);
                }
            }
        });
        binding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.fab.callOnClick();
                binding.fab1.hide();
                binding.fab2.hide();

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("LOGOUT")
                        .setMessage("Are you sure you want to log out? Synchronize all offline records before logging off.")
                        .setPositiveButton("LOGOUT NOW", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                try {
                                    binding.progressBar.setVisibility(View.VISIBLE);

                                    LogoutRepo.getInstance().getLogout(req, new ILogoutRepo.LogoutCallback() {
                                        @Override
                                        public void onDataLoaded(LogoutRes logoutRes) {
                                            try {
                                                AppDatabase.getAppDatabase(MainActivity.this).masterDataDao().deleteAll();
                                                AppDatabase.getAppDatabase(MainActivity.this).pvExcessDao().deleteAll();
                                                SharedPrefUtil.putString(USER_NAME, "", MainActivity.this);
                                                SharedPrefUtil.putString(USER_TOKEN, "", MainActivity.this);
                                                SharedPrefUtil.putString(ASSET_CODE, "", MainActivity.this);
                                                SharedPrefUtil.putString(ASSET_NAME, "", MainActivity.this);
                                                SharedPrefUtil.putString(USER_LOCATION, "", MainActivity.this);

                                                AppConstant.TAG_CURRENT_VALUE = "";
                                                AppConstant.CURRENT_LONGITUDE = "";
                                                AppConstant.CURRENT_LATITUDE = "";
                                                AppConstant.DEVICE_MODEL = "";
                                                clearAppData();
                                                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                                finish();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onDataNotAvailable() {
                                            binding.progressBar.setVisibility(View.VISIBLE);
                                            Toast.makeText(MainActivity.this, "Unable to logout.", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("CANCEL", null)
                        .show();

            }
        });


//    callDashboard();
      /*  DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //  NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();*/


        mFusedLocationClient.getLastLocation().addOnSuccessListener(
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            mLastLocation = location;
                            Log.e("####", "location Lat " + location.getLatitude());
                            Log.e("####", "location Lon " + location.getLongitude());
                            if (AppConstant.CURRENT_LATITUDE.length() < 4) {
                                AppConstant.CURRENT_LATITUDE = "" + location.getLatitude();
                                AppConstant.CURRENT_LONGITUDE = "" + location.getLongitude();

                            }
                         /*   mLocationTextView.setText(
                                    getString(R.string.location_text,
                                            mLastLocation.getLatitude(),
                                            mLastLocation.getLongitude(),
                                            mLastLocation.getTime()));*/
                        } else {
                            //    mLocationTextView.setText(R.string.no_location);
                        }
                    }

                });

      /*  fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);*/
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    binding.fab1.show();
                    binding.fab2.show();
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            pvTotalCount = 0;
            tagTotalCount = 0;
            pvCompleteCount = 0;
            tagCompleteCount = 0;
            masterCount = 0;
            masterCount = AppDatabase.getAppDatabase(MainActivity.this).masterDataDao().getItemCountLive();
            Log.e("#####", "########## masterCount ######### " + masterCount);
            if (masterCount == 0) {
                binding.progressBar.setVisibility(View.VISIBLE);
                fetchMasterData(req);
            } else {
                checkForLocalData();
                callDashboard();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callDashboard() {
        DashboardRepo.getInstance().getDashboard(req, new IDashboardRepo.DashboardCallback() {
            @Override
            public void onDataLoaded(Dashboard dashboardReqModel) {

                Log.e("", "onDataLoaded");
                if (!dashboardReqModel.responsemessage.equalsIgnoreCase("0")) {

                    try {

                        for (int i = 0; i < dashboardReqModel.appmenus.size(); i++) {

                            if (dashboardReqModel.appmenus.get(i).menuid == 1) {
                                List<String> splitList = null;
                                int completed = 0;
                                if (dashboardReqModel.appmenus.get(i).counters.length() > 0 || dashboardReqModel.appmenus.get(i).counters.contains("/")) {
                                    splitList = Arrays.asList(dashboardReqModel.appmenus.get(i).counters.split("/"));
                                    completed = Integer.parseInt(splitList.get(0));
                                  /*  Log.e("####", "########## splitList1 ########### " + splitList.get(0));
                                    Log.e("####", "########## splitList2 ########### " + splitList.get(1));*/
                                }
                                completed = completed + tagCompleteCount;
                                dashboardReqModel.appmenus.get(i).counters = "" + completed + "/" + tagTotalCount;
                            }
                            if (dashboardReqModel.appmenus.get(i).menuid == 2) {
                                List<String> splitList = null;
                                int completed = 0;

                                if (dashboardReqModel.appmenus.get(i).counters.length() > 0 || dashboardReqModel.appmenus.get(i).counters.contains("/")) {
                                    splitList = Arrays.asList(dashboardReqModel.appmenus.get(i).counters.split("/"));
                                    completed = Integer.parseInt(splitList.get(0));
                                    Log.e("####", "########## splitList1 ########### " + splitList.get(0));
                                    Log.e("####", "########## splitList2 ########### " + splitList.get(1));
                                }
                                Log.e("####", "########## completed-- ########### " + completed);
                                Log.e("####", "########## pvCompleteCount-- ########### " + pvCompleteCount);
                                completed = completed + pvCompleteCount;
                                Log.e("####", "########## completed++ ########### " + completed);

                                dashboardReqModel.appmenus.get(i).counters = "" + completed + "/" + pvTotalCount;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < dashboardReqModel.appmenus.size(); i++) {

                        Log.e("####", "########## dashboardReqModel.appmenus ########### " + dashboardReqModel.appmenus.get(i).menuid);
                        Log.e("####", "########## dashboardReqModel.appmenus ########### " + dashboardReqModel.appmenus.get(i).menutitle);
                        Log.e("####", "########## dashboardReqModel.appmenus ########### " + dashboardReqModel.appmenus.get(i).counters);
                    }
                    AppMenus appMenus= new AppMenus();
                    appMenus.menuid=9009;
                    appMenus.menutitle="Asset List";
                    appMenus.counters="";
                    dashboardReqModel.appmenus.add(appMenus);
                    MainGridView adapterViewAndroid = new MainGridView(MainActivity.this, dashboardReqModel.appmenus, CheckingPermissionIsEnabledOrNot());

                    binding.gridView.setAdapter(adapterViewAndroid);
                    binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int i, long id) {

                        }
                    });
                } else {

                  /*  Toast.makeText(MainActivity.this, "Your session has expired. Please re-login1", Toast.LENGTH_SHORT).show();
                    SharedPrefUtil.putString(USER_NAME, "", MainActivity.this);
                    SharedPrefUtil.putString(USER_TOKEN, "", MainActivity.this);
                    SharedPrefUtil.putString(ASSET_CODE, "", MainActivity.this);
                    SharedPrefUtil.putString(ASSET_NAME, "", MainActivity.this);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clearAppData();
                            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);*/

                }
                binding.progressBar.setVisibility(View.GONE);
                //        binding.gridView.setFitGridAdapter(new GridViewAdapter(MainActivity.this,dashboardReqModel.appmenus));
            }

            @Override
            public void onDataNotAvailable() {
                binding.progressBar.setVisibility(View.GONE);
                SharedPrefUtil.putString(USER_TOKEN,"", MainActivity.this);
                Log.e("", "onDataNotAvailable");
            }
        });
    }

    private void fetchMasterData(Map<String, String> req) {
        MasterDataRepo.getInstance().getMasterData(req, new IMasterDataRepo.MasterDataCallback() {
            @Override
            public void onDataLoaded(MasterData masterData) {
                try {
                    AppDatabase.getAppDatabase(MainActivity.this).masterDataDao().insertAll(masterData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                checkForLocalData();
                callDashboard();
                //    binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onDataNotAvailable() {
                binding.progressBar.setVisibility(View.GONE);
                SharedPrefUtil.putString(USER_TOKEN,"", MainActivity.this);
            }
        });
    }

    private void showFABMenu() {
        isFABOpen = true;
        binding.fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        binding.fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));

    }

    private void closeFABMenu() {
        isFABOpen = false;
        binding.fab1.animate().translationY(0);
        binding.fab2.animate().translationY(0);

    }

    private void clearAppData() {
        try {
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
      /*  try {
            // clearing app data
            if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData(); // note: it has a return value!
            } else {
                String packageName = getApplicationContext().getPackageName();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("pm clear " + packageName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void checkForLocalData() {
        try {
            List<PvModels> pVModels = AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData().PVModels;
            for (int i = 0; i < pVModels.size(); i++) {
                pvTotalCount = pvTotalCount + pVModels.get(i).PVItems.size();
                for (int j = 0; j < pVModels.get(i).PVItems.size(); j++) {
                    if (pVModels.get(i).PVItems.get(j).SaveFlag) {
                        unsavedRecord = true;
                        pvCompleteCount++;
                        unsavedCount++;
                        // break;
                    }
                   /* if (pVModels.get(i).PVItems.get(j).SaveFlag || pVModels.get(i).PVItems.get(j).PVDStatus.equalsIgnoreCase("Completed")) {
                        pvCompleteCount++;
                        // break;
                    }*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            List<TagModels> tagModels = AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData().TAGModels;
            for (int i = 0; i < tagModels.size(); i++) {
                tagTotalCount = tagTotalCount + tagModels.get(i).TAGItems.size();
                for (int j = 0; j < tagModels.get(i).TAGItems.size(); j++) {
                    if (tagModels.get(i).TAGItems.get(j).SaveFlag) {
                        tagCompleteCount++;
                        unsavedCount++;
                        unsavedRecord = true;
                        //   break;
                    }
                 /*   if (tagModels.get(i).TAGItems.get(j).SaveFlag || tagModels.get(i).TAGItems.get(j).TagDStatus.equalsIgnoreCase("Completed")) {
                        tagCompleteCount++;
                    }*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.e("#####", "#######pvExcessDao ######## " + AppDatabase.getAppDatabase(this).pvExcessDao().getItemCountLive());
            unsavedCount = unsavedCount + AppDatabase.getAppDatabase(this).pvExcessDao().getItemCountLive();
            if (unsavedCount > 0) {
                unsavedRecord = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (unsavedRecord) {
            binding.btnLocalData.setVisibility(View.VISIBLE);

            binding.btnLocalData.setText("Unsaved Records: " + unsavedCount);
            binding.btnLocalData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    syncPvLocalData();
                    syncTaggingLocalData();
                    syncExcessLocalData();
                }
            });
        } else {
            binding.btnLocalData.setVisibility(View.GONE);
        }

        binding.progressBar.setVisibility(View.GONE);
    }

    public void syncPvLocalData() {
        try {
            PvItems pvItems;
            List<PvModels> pVModels = AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData().PVModels;
            for (int i = 0; i < pVModels.size(); i++) {
                for (int j = 0; j < pVModels.get(i).PVItems.size(); j++) {
                    if (pVModels.get(i).PVItems.get(j).SaveFlag) {
                        pvItems = new PvItems();
                        pvItems = pVModels.get(i).PVItems.get(j);
                        if (pvItems != null) {
                            binding.progressBar.setVisibility(View.VISIBLE);
                            try {
                                if (pvItems.Image1.length() > 5) {
                                    pvItems.Image1 = convertUriToBase(pvItems.Image1);
                                }
                                if (pvItems.Image2.length() > 5) {
                                    pvItems.Image2 = convertUriToBase(pvItems.Image2);
                                }
                                if (pvItems.Image3.length() > 5) {
                                    pvItems.Image3 = convertUriToBase(pvItems.Image3);
                                }
                                if (pvItems.Image4.length() > 5) {
                                    pvItems.Image4 = convertUriToBase(pvItems.Image4);
                                }
                                if (pvItems.Image5.length() > 5) {
                                    pvItems.Image5 = convertUriToBase(pvItems.Image5);
                                }
                                PvAssetRepo.getInstance().postPvAsset(req, pvItems, new IPvAssetRepo.PvAssetCallback() {
                                    @Override
                                    public void onDataLoaded(PvAssetRes pvAssetRes) {
                                        if (unsavedCount != 0) {
                                            unsavedCount = unsavedCount - 1;
                                        }
                                        Log.e("####", "######## tagAssetRes ########## " + pvAssetRes.responseMessage);
                                        updatePvDetailData(pvAssetRes.PVID, pvAssetRes.PVDetailID, false);
                                        if (unsavedCount == 0) {
                                            binding.progressBar.setVisibility(View.GONE);
                                            binding.btnLocalData.setVisibility(View.GONE);
                                        }
                                        binding.btnLocalData.setText("Unsaved Records: " + unsavedCount);
                                    }

                                    @Override
                                    public void onDataNotAvailable() {
                                        binding.progressBar.setVisibility(View.GONE);
                                        Log.e("####", "######## tagAssetRes onDataNotAvailable ########## ");
                                    }
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void syncTaggingLocalData() {

        TagItems tagItems;
        try {
            List<TagModels> tagModels = AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData().TAGModels;
            for (int i = 0; i < tagModels.size(); i++) {
                for (int j = 0; j < tagModels.get(i).TAGItems.size(); j++) {
                    if (tagModels.get(i).TAGItems.get(j).SaveFlag) {
                        binding.progressBar.setVisibility(View.VISIBLE);
                        tagItems = new TagItems();
                        tagItems = tagModels.get(i).TAGItems.get(j);
                        if (tagItems != null) {
                            try {
                                if (tagItems.Image1.length() > 5) {
                                    tagItems.Image1 = convertUriToBase(tagItems.Image1);
                                }
                                if (tagItems.Image2.length() > 5) {
                                    tagItems.Image2 = convertUriToBase(tagItems.Image2);
                                }
                                if (tagItems.Image3.length() > 5) {
                                    tagItems.Image3 = convertUriToBase(tagItems.Image3);
                                }
                                if (tagItems.Image4.length() > 5) {
                                    tagItems.Image4 = convertUriToBase(tagItems.Image4);
                                }
                                if (tagItems.Image5.length() > 5) {
                                    tagItems.Image5 = convertUriToBase(tagItems.Image5);
                                }

                                TagAssetRepo.getInstance().postTagAsset(req, tagItems, new ITagAssetRepo.TagAssetCallback() {
                                    @Override
                                    public void onDataLoaded(TagAssetRes tagAssetRes) {
                                        if (unsavedCount != 0) {
                                            unsavedCount = unsavedCount - 1;
                                        }
                                        Log.e("####", "######## tagAssetRes ########## " + tagAssetRes.responseMessage);
                                        updateTagDetailData(tagAssetRes.TAGID, tagAssetRes.TAGDetailID, false);
                                        if (unsavedCount == 0) {
                                            binding.progressBar.setVisibility(View.GONE);
                                            binding.btnLocalData.setVisibility(View.GONE);
                                        }
                                        binding.btnLocalData.setText("Unsaved Records: " + unsavedCount);
                                    }
                                    @Override
                                    public void onDataNotAvailable() {
                                        binding.progressBar.setVisibility(View.GONE);
                                        Log.e("####", "######## tagAssetRes onDataNotAvailable ########## ");
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void syncExcessLocalData() {


        try {
            List<PvExcessReq> pvExcessReqs=new ArrayList<>();
            pvExcessReqs.addAll(AppDatabase.getAppDatabase(MainActivity.this).pvExcessDao().getPvExcessReq());
            Log.e("####","####### pvExcessReq size ######## "+pvExcessReqs.size());
            for (int i = 0; i < pvExcessReqs.size(); i++) {

                binding.progressBar.setVisibility(View.VISIBLE);
                PvExcessReq pvExcessReq = pvExcessReqs.get(i);
                Log.e("####","####### pvExcessReq ######## "+pvExcessReq.assetname);
                if (pvExcessReqs.get(i) != null) {

                    try {
                        if (pvExcessReq.image1.length() > 5) {
                            pvExcessReq.image1 = convertUriToBase(pvExcessReq.image1);
                        }
                        if (pvExcessReq.image2.length() > 5) {
                            pvExcessReq.image2 = convertUriToBase(pvExcessReq.image2);
                        }
                        if (pvExcessReq.image3.length() > 5) {
                            pvExcessReq.image3 = convertUriToBase(pvExcessReq.image3);
                        }
                        if (pvExcessReq.image4.length() > 5) {
                            pvExcessReq.image4 = convertUriToBase(pvExcessReq.image4);
                        }
                        if (pvExcessReq.image5.length() > 5) {
                            pvExcessReq.image5 = convertUriToBase(pvExcessReq.image5);
                        }

                        ExcessPvRepo.getInstance().postExcessPv(req, pvExcessReq, new IExcessPvRepo.ExcessCallback() {
                            @Override
                            public void onDataLoaded(ExcessRes excessRes) {
                                if (unsavedCount != 0) {
                                    unsavedCount = unsavedCount - 1;
                                }
                                updateExcessData(excessRes.assetnumber);
                                if (unsavedCount == 0) {
                                    binding.progressBar.setVisibility(View.GONE);
                                    binding.btnLocalData.setVisibility(View.GONE);
                                }
                                binding.btnLocalData.setText("Unsaved Records: " + unsavedCount);
                            }

                            @Override
                            public void onDataNotAvailable() {
                                binding.progressBar.setVisibility(View.GONE);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* public void populateData() {

        dataModelList = new ArrayList<>();
        dataModelList.add(new GridViewModel("TAGGING","TAGGING","4/10"));
        dataModelList.add(new GridViewModel("TAGGING REPORT","TAGGING_REPORT",""));
        dataModelList.add(new GridViewModel("PV","PV_","4/10"));
        dataModelList.add(new GridViewModel("PV REPORT","PV_REPORT",""));

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return false; /*NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();*/
    }


    private void RequestMultiplePermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]
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

                boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean READ_EXTERNAL_STORAGE = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean WRITE_EXTERNAL_STORAGE = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                boolean ACCESS_FINE_LOCATION = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                boolean ACCESS_COARSE_LOCATION = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                boolean ACCESS_NETWORK_STATE = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                if (CameraPermission && READ_EXTERNAL_STORAGE && WRITE_EXTERNAL_STORAGE && ACCESS_FINE_LOCATION && ACCESS_COARSE_LOCATION && ACCESS_NETWORK_STATE) {

                    //    Toast.makeText(SettingIpActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();


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


    public void getLocation() {
        try {

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            try {
                AppConstant.CURRENT_LATITUDE = "" + location.getLatitude();
                AppConstant.CURRENT_LONGITUDE = "" + location.getLongitude();
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());

                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                String address = addresses.get(0).getAddressLine(0);
              /*  String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();*/
                Log.e("####", "address : " + address);
              /*  Log.e("####", "city : " + city);
                Log.e("####", "state : " + state);
                Log.e("####", "country : " + country);
                Log.e("####", "postalCode : " + postalCode);
                Log.e("####", "knownName : " + knownName);*/


            } catch (Exception e) {
                e.printStackTrace();
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20000, 0, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 0, this);
            // locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            //.setText("Current Location: " + location.getLatitude() + ", " + location.getLongitude());
            //  viewModel.LocationStr.setValue();
            try {
                AppConstant.CURRENT_LATITUDE = "" + location.getLatitude();
                AppConstant.CURRENT_LONGITUDE = "" + location.getLongitude();
                Log.e("####", " CURRENT_LATITUDE ## " + AppConstant.CURRENT_LATITUDE);
                Log.e("####", " CURRENT_LONGITUDE ## " + AppConstant.CURRENT_LONGITUDE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    private boolean turnOnLocationService() {
        LocationManager lm = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
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
                            MainActivity.this.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
        return gps_enabled;
    }


    public void updatePvDetailData(int pvID, int pvDetailID, boolean saveFlag) {
        MasterData masterData = AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData();
        for (int i = 0; i < masterData.PVModels.size(); i++) {
            if (masterData.PVModels.get(i).PhysicalVerificationID == pvID) {
                for (int j = 0; j < masterData.PVModels.get(i).PVItems.size(); j++) {
                    if (masterData.PVModels.get(i).PVItems.get(j).PVDetailID == pvDetailID) {
                        //Log.e("####", "########## PVDetailID ######### " + masterData.TAGModels.get(i).TAGItems.get(j).TAGDetailID);
                        masterData.PVModels.get(i).PVItems.get(j).AssetID = masterData.PVModels.get(i).PVItems.get(j).AssetID;
                        masterData.PVModels.get(i).PVItems.get(j).AssetName = masterData.PVModels.get(i).PVItems.get(j).AssetName;
                        masterData.PVModels.get(i).PVItems.get(j).AssetNumber = masterData.PVModels.get(i).PVItems.get(j).AssetNumber;
                        masterData.PVModels.get(i).PVItems.get(j).Vendor = masterData.PVModels.get(i).PVItems.get(j).Vendor;
                        masterData.PVModels.get(i).PVItems.get(j).CapitalizationDate = masterData.PVModels.get(i).PVItems.get(j).CapitalizationDate;
                        masterData.PVModels.get(i).PVItems.get(j).LocationID = masterData.PVModels.get(i).PVItems.get(j).LocationID;
                        masterData.PVModels.get(i).PVItems.get(j).LocationName = masterData.PVModels.get(i).PVItems.get(j).LocationName;
                        masterData.PVModels.get(i).PVItems.get(j).SubLocationID = masterData.PVModels.get(i).PVItems.get(j).SubLocationID;
                        masterData.PVModels.get(i).PVItems.get(j).SubLocationName = masterData.PVModels.get(i).PVItems.get(j).SubLocationName;
                        masterData.PVModels.get(i).PVItems.get(j).SerialNumber = masterData.PVModels.get(i).PVItems.get(j).SerialNumber;
                        masterData.PVModels.get(i).PVItems.get(j).Make = masterData.PVModels.get(i).PVItems.get(j).Make;
                        masterData.PVModels.get(i).PVItems.get(j).Model = masterData.PVModels.get(i).PVItems.get(j).Model;
                        masterData.PVModels.get(i).PVItems.get(j).AssetTypeID = masterData.PVModels.get(i).PVItems.get(j).AssetTypeID;
                        masterData.PVModels.get(i).PVItems.get(j).AssetTypeName = masterData.PVModels.get(i).PVItems.get(j).AssetTypeName;
                        masterData.PVModels.get(i).PVItems.get(j).AssetSurfaceID = masterData.PVModels.get(i).PVItems.get(j).AssetSurfaceID;
                        masterData.PVModels.get(i).PVItems.get(j).AssetSurfaceName = masterData.PVModels.get(i).PVItems.get(j).AssetSurfaceName;
                        masterData.PVModels.get(i).PVItems.get(j).AssetConditionID = masterData.PVModels.get(i).PVItems.get(j).AssetConditionID;
                        masterData.PVModels.get(i).PVItems.get(j).AssetConditionName = masterData.PVModels.get(i).PVItems.get(j).AssetConditionName;
                        masterData.PVModels.get(i).PVItems.get(j).SpocName = masterData.PVModels.get(i).PVItems.get(j).SpocName;
                        masterData.PVModels.get(i).PVItems.get(j).PVName = masterData.PVModels.get(i).PVItems.get(j).PVName;
                        masterData.PVModels.get(i).PVItems.get(j).ClientRemarks = masterData.PVModels.get(i).PVItems.get(j).ClientRemarks;
                        masterData.PVModels.get(i).PVItems.get(j).PVID = masterData.PVModels.get(i).PVItems.get(j).PVID;
                        masterData.PVModels.get(i).PVItems.get(j).PVDetailID = masterData.PVModels.get(i).PVItems.get(j).PVDetailID;
                        masterData.PVModels.get(i).PVItems.get(j).ImageCount = masterData.PVModels.get(i).PVItems.get(j).ImageCount;
                        masterData.PVModels.get(i).PVItems.get(j).PVDStatus = masterData.PVModels.get(i).PVItems.get(j).PVDStatus;
                        masterData.PVModels.get(i).PVItems.get(j).PVDRemarks = masterData.PVModels.get(i).PVItems.get(j).PVDRemarks;
                        masterData.PVModels.get(i).PVItems.get(j).SaveFlag = saveFlag;
                        masterData.PVModels.get(i).PVItems.get(j).Image1 = masterData.PVModels.get(i).PVItems.get(j).Image1;
                        masterData.PVModels.get(i).PVItems.get(j).Image2 = masterData.PVModels.get(i).PVItems.get(j).Image2;
                        masterData.PVModels.get(i).PVItems.get(j).Image3 = masterData.PVModels.get(i).PVItems.get(j).Image3;
                        masterData.PVModels.get(i).PVItems.get(j).Image4 = masterData.PVModels.get(i).PVItems.get(j).Image4;
                        masterData.PVModels.get(i).PVItems.get(j).Image5 = masterData.PVModels.get(i).PVItems.get(j).Image5;
                        AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().updateAllMasterData(masterData);
                        Log.e("####", "########## tagItems ######### " + masterData.PVModels.get(i).PVItems.get(j).SaveFlag);

                    }
                }

            }
        }

    }

    public void updateTagDetailData(int tagID, int tagDetailID, boolean saveFlag) {
        MasterData masterData = AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData();
        for (int i = 0; i < masterData.TAGModels.size(); i++) {
            if (masterData.TAGModels.get(i).TAGID == tagID) {
                for (int j = 0; j < masterData.TAGModels.get(i).TAGItems.size(); j++) {
                    if (masterData.TAGModels.get(i).TAGItems.get(j).TAGDetailID == tagDetailID) {
                        //Log.e("####", "########## PVDetailID ######### " + masterData.TAGModels.get(i).TAGItems.get(j).TAGDetailID);
                        masterData.TAGModels.get(i).TAGItems.get(j).AssetID = masterData.TAGModels.get(i).TAGItems.get(j).AssetID;
                        masterData.TAGModels.get(i).TAGItems.get(j).AssetName = masterData.TAGModels.get(i).TAGItems.get(j).AssetName;
                        masterData.TAGModels.get(i).TAGItems.get(j).AssetNumber = masterData.TAGModels.get(i).TAGItems.get(j).AssetNumber;
                        masterData.TAGModels.get(i).TAGItems.get(j).Vendor = masterData.TAGModels.get(i).TAGItems.get(j).Vendor;
                        masterData.TAGModels.get(i).TAGItems.get(j).CapitalizationDate = masterData.TAGModels.get(i).TAGItems.get(j).CapitalizationDate;
                        masterData.TAGModels.get(i).TAGItems.get(j).PONumber = masterData.TAGModels.get(i).TAGItems.get(j).PONumber;
                        masterData.TAGModels.get(i).TAGItems.get(j).QRCode = masterData.TAGModels.get(i).TAGItems.get(j).QRCode;
                        masterData.TAGModels.get(i).TAGItems.get(j).Latitude = masterData.TAGModels.get(i).TAGItems.get(j).Latitude;
                        masterData.TAGModels.get(i).TAGItems.get(j).Longitude = masterData.TAGModels.get(i).TAGItems.get(j).Longitude;
                        masterData.TAGModels.get(i).TAGItems.get(j).LocationString = masterData.TAGModels.get(i).TAGItems.get(j).LocationString;
                        masterData.TAGModels.get(i).TAGItems.get(j).TAGID = masterData.TAGModels.get(i).TAGItems.get(j).TAGID;
                        masterData.TAGModels.get(i).TAGItems.get(j).TAGDetailID = masterData.TAGModels.get(i).TAGItems.get(j).TAGDetailID;
                        masterData.TAGModels.get(i).TAGItems.get(j).ImageCount = masterData.TAGModels.get(i).TAGItems.get(j).ImageCount;
                        masterData.TAGModels.get(i).TAGItems.get(j).TagDStatus = masterData.TAGModels.get(i).TAGItems.get(j).TagDStatus;
                        masterData.TAGModels.get(i).TAGItems.get(j).TagDRemarks = masterData.TAGModels.get(i).TAGItems.get(j).TagDRemarks;
                        masterData.TAGModels.get(i).TAGItems.get(j).SaveFlag = saveFlag;
                        masterData.TAGModels.get(i).TAGItems.get(j).Image1 = masterData.TAGModels.get(i).TAGItems.get(j).Image1;
                        masterData.TAGModels.get(i).TAGItems.get(j).Image2 = masterData.TAGModels.get(i).TAGItems.get(j).Image2;
                        masterData.TAGModels.get(i).TAGItems.get(j).Image3 = masterData.TAGModels.get(i).TAGItems.get(j).Image3;
                        masterData.TAGModels.get(i).TAGItems.get(j).Image4 = masterData.TAGModels.get(i).TAGItems.get(j).Image4;
                        masterData.TAGModels.get(i).TAGItems.get(j).Image5 = masterData.TAGModels.get(i).TAGItems.get(j).Image5;
                        AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().updateAllMasterData(masterData);

                    }
                }

            }
        }

    }

    private String convertUriToBase(String uriPath) {
        String baseStr = "";
        Uri uri = null;
        try {
            uri = Uri.parse(new File(uriPath).toString());

            File file = new File(uri.getPath());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap.createScaledBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()), 400, 400, false).compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            baseStr = Base64.encodeToString(byteArray, Base64.DEFAULT);
            // Log.e("####", "##### BASE 64 BASE-- " + baseStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseStr;
    }


    public void updateExcessData(String assetnumber) {
        //    PvAssetRes pvAssetRes= new PvAssetRes();

        AppDatabase.getAppDatabase(AMSApplication.instance).pvExcessDao().deleteWhere(assetnumber);

    }
}