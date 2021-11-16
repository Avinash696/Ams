package com.ams.amsvistara.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.multidex.BuildConfig;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
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
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.ams.amsvistara.R;
import com.ams.amsvistara.constants.AppConstant;
import com.ams.amsvistara.databinding.ActivityExcessAuditBinding;
import com.ams.amsvistara.model.req.AuditExcess;
import com.ams.amsvistara.model.res.AuditExcessRes;
import com.ams.amsvistara.model.res.AuditMasters;
import com.ams.amsvistara.utils.RequestPermissions;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.viewmodel.AuditViewModel;
import com.ams.amsvistara.viewmodel.ExcessAuditViewModel;
import com.ams.amsvistara.ws.repo.AuditExcessRepo;
import com.ams.amsvistara.ws.repo.IExcessAuditRepo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.ams.amsvistara.constants.ConstantStr.AUDIT_MASTER_ITEM;
import static com.ams.amsvistara.constants.ConstantStr.AUDIT_MODEL_ID;
import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class ExcessAuditActivity extends AppCompatActivity implements LocationListener {

    ActivityExcessAuditBinding binding;
    ExcessAuditViewModel viewModel;
    private int CAMERA_PERMISSION = 4;
    private int CAMERA_STORAGE_PERMISSION = 11;
    public final String APP_TAG = "PV Image";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo_.jpg";
    private File photoFile;
    private int imageCount = 0;
    FusedLocationProviderClient mFusedLocationClient;
    Location mLastLocation;
    double getLatitude_ = 0.0;
    double getLongitude_ = 0.0;
    private LocationManager locationManager;
    private StringBuffer stringBufferLoc = new StringBuffer();
    private int AuditId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_excess_audit);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        viewModel = new ViewModelProvider(this).get(ExcessAuditViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_excess_audit);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.init();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria crit = new Criteria();
        crit.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(crit, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        getLocation();
        getIntentData();
        initToolbar();
        imageCount = 0;
        viewModel.ImageCount.setValue("" + imageCount);
        binding.progressBar.setVisibility(View.GONE);
        binding.lLSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                /*    Log.e("###", "###### auditExcess.auditid ######## " + viewModel.AuditId.getValue().toString());
                    Log.e("###", "###### auditExcess.ExcessId ######## " + viewModel.ExcessId.getValue().toString());
                    Log.e("###", "###### auditExcess.AssetId ######## " + viewModel.AssetId.getValue().toString());*/
                    AuditExcess auditExcess = new AuditExcess();
                    auditExcess.auditid = viewModel.AuditId.getValue();
                    if(viewModel.ExcessId.getValue().toString().length()>0) {
                        auditExcess.excessid = Integer.valueOf(viewModel.ExcessId.getValue().toString());
                    }else {
                        auditExcess.excessid=0;
                    }
                    if(viewModel.AssetId.getValue().toString().length()>0) {


                        auditExcess.assetid = Integer.valueOf(viewModel.AssetId.getValue().toString());
                    }else {
                        auditExcess.assetid = 0;
                    }
                    auditExcess.assetname = viewModel.AssetName.getValue();
                    auditExcess.assetnumber = viewModel.AssetNumber.getValue();
                    auditExcess.latitude = String.valueOf(getLatitude_);
                    auditExcess.longitude = String.valueOf(getLongitude_);
                    auditExcess.locationstring = viewModel.LocationString.getValue();
                    auditExcess.imagecount = Integer.valueOf(viewModel.ImageCount.getValue());
                    auditExcess.auditestatus = "Completed";//viewModel.AuditEStatus.getValue();
                    auditExcess.auditeremarks = viewModel.AuditERemarks.getValue();

                    if (viewModel.imageFive.getValue().length() > 0) {
                        auditExcess.image5 = viewModel.imageFive.getValue();
                    } else {
                        auditExcess.image5 = "";
                    }
                    if (viewModel.imageFour.getValue().length() > 0) {
                        auditExcess.image4 = viewModel.imageFour.getValue();
                    } else {
                        auditExcess.image4 = "";
                    }
                    if (viewModel.imageThree.getValue().length() > 0) {
                        auditExcess.image3 = viewModel.imageThree.getValue();
                    } else {
                        auditExcess.image3 = "";
                    }
                    if (viewModel.imageTwo.getValue().length() > 0) {
                        auditExcess.image2 = viewModel.imageTwo.getValue();
                    } else {
                        auditExcess.image2 = "";
                    }
                    if (viewModel.imageOne.getValue().length() > 0) {
                        auditExcess.image1 = viewModel.imageOne.getValue();
                    } else {
                        auditExcess.image1 = "";
                    }

               /*   Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.auditid);
                    Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.excessid);
                    Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.assetid);
                    Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.assetname);
                    Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.assetnumber);
                    Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.latitude);
                    Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.longitude);
                    Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.locationstring);
                    Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.auditestatus);
                    Log.e("###", "###### auditExcess.auditid ######## " + auditExcess.auditeremarks);*/

                    Map<String, String> req = new HashMap<String, String>();
                    req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", ExcessAuditActivity.this));
                    /*
                     auditExcess.auditid = viewModel.AuditId.getValue();
                    auditExcess.excessid = Integer.valueOf(viewModel.ExcessId.getValue().toString());
                    auditExcess.assetid = Integer.valueOf(viewModel.AssetId.getValue().toString());
                    auditExcess.assetname = viewModel.AssetName.getValue();
                    auditExcess.assetnumber = viewModel.AssetNumber.getValue();
                    auditExcess.latitude = String.valueOf(getLatitude_);
                    auditExcess.longitude = String.valueOf(getLongitude_);
                    auditExcess.locationstring = viewModel.LocationString.getValue();
                    auditExcess.imagecount = Integer.valueOf(viewModel.ImageCount.getValue());
                    auditExcess.auditestatus = "Completed";//viewModel.AuditEStatus.getValue();
                    auditExcess.auditeremarks = viewModel.AuditERemarks.getValue();
                    * */

                     if (auditExcess.excessid == 0) {
                        Toast.makeText(ExcessAuditActivity.this, "Enter Excess Id.", Toast.LENGTH_SHORT).show();
                    }else  if (auditExcess.assetid == 0) {
                         Toast.makeText(ExcessAuditActivity.this, "Enter Asset Id.", Toast.LENGTH_SHORT).show();
                     }
                    else if (auditExcess.assetname.trim().length() == 0) {
                        Toast.makeText(ExcessAuditActivity.this, "Enter Excess Asset Name.", Toast.LENGTH_SHORT).show();
                    } else if (auditExcess.assetnumber.trim().length() == 0) {
                        Toast.makeText(ExcessAuditActivity.this, "Enter Excess Asset Number.", Toast.LENGTH_SHORT).show();
                    } else if (auditExcess.imagecount == 0) {
                        Toast.makeText(ExcessAuditActivity.this, "Add Image.", Toast.LENGTH_SHORT).show();
                    } else if (auditExcess.auditeremarks.trim().length() == 0) {
                        Toast.makeText(ExcessAuditActivity.this, "Enter Excess Asset Remarks.", Toast.LENGTH_SHORT).show();
                    } else {
                        binding.progressBar.setVisibility(View.VISIBLE);
                    AuditExcessRepo.getInstance().postExcessAudit(req, auditExcess, new IExcessAuditRepo.ExcessAuditCallback() {
                        @Override
                        public void onDataLoaded(AuditExcessRes auditExcessRes) {
                            binding.progressBar.setVisibility(View.GONE);
                            Log.e("###", "###### auditExcessRes ######## " + auditExcessRes.responsemessage);
                            Toast.makeText(ExcessAuditActivity.this, "Excess Audit Asset Saved.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onDataNotAvailable() {
                            binding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(ExcessAuditActivity.this, "ERROR.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                } catch (Exception e) {
                    binding.progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
        });
    }


    public void getLocation() {
        try {

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            try {
                try {
                    viewModel.Latitude.setValue("" + location.getLatitude());
                    viewModel.Longitude.setValue("" + location.getLongitude());
                    getLatitude_ = location.getLatitude();
                    getLongitude_ = location.getLongitude();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (getLatitude_ != 0.0) {
                    AppConstant.CURRENT_LATITUDE = "" + location.getLatitude();
                    AppConstant.CURRENT_LONGITUDE = "" + location.getLongitude();
                    Log.e("####", "IF CURRENT_LATITUDE ## " + AppConstant.CURRENT_LATITUDE);
                    Log.e("####", "IF CURRENT_LONGITUDE ## " + AppConstant.CURRENT_LONGITUDE);
                } else {
                    Log.e("####", "ELSE CURRENT_LATITUDE ## " + AppConstant.CURRENT_LATITUDE);
                    Log.e("####", "ELSE CURRENT_LONGITUDE ## " + AppConstant.CURRENT_LONGITUDE);
                    mFusedLocationClient.getLastLocation().addOnSuccessListener(
                            new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        mLastLocation = location;
                                        Log.e("####", "location Tag Lat " + location.getLatitude());
                                        Log.e("####", "location Tag Lon " + location.getLongitude());
                                        Log.e("####", "CURRENT_LONGITUDE " + AppConstant.CURRENT_LONGITUDE);
                                        if (AppConstant.CURRENT_LONGITUDE.length() < 4) {
                                            AppConstant.CURRENT_LONGITUDE = "" + location.getLatitude();
                                            AppConstant.CURRENT_LATITUDE = "" + location.getLatitude();
                                        }
                                        Geocoder geocoder;
                                        List<Address> addresses;
                                        geocoder = new Geocoder(ExcessAuditActivity.this, Locale.getDefault());

                                        Log.e("####", "Double Lat " + Double.parseDouble(AppConstant.CURRENT_LATITUDE));
                                        Log.e("####", "Double Lon " + Double.parseDouble(AppConstant.CURRENT_LONGITUDE));
                                        try {
                                            addresses = geocoder.getFromLocation(Double.parseDouble(AppConstant.CURRENT_LATITUDE), Double.parseDouble(AppConstant.CURRENT_LONGITUDE), 1);


                                            String address = addresses.get(0).getAddressLine(0);
                                            stringBufferLoc.append(address);
                                          /*  if(stringBufferLoc.length()<5){
                                                viewModel.LocationStr.setValue("Lat: "+AppConstant.CURRENT_LATITUDE+"Lon: "+AppConstant.CURRENT_LONGITUDE);
                                            }else {
                                                viewModel.LocationStr.setValue("" + stringBufferLoc.toString());
                                            }*/
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            if (stringBufferLoc.length() < 5) {
                                                viewModel.LocationString.setValue("Lat: " + AppConstant.CURRENT_LATITUDE + "Lon: " + AppConstant.CURRENT_LONGITUDE);
                                            } else {
                                                viewModel.LocationString.setValue("" + stringBufferLoc.toString());
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
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
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
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
           /* Log.e("####", "on CURRENT_LATITUDE ## " + AppConstant.CURRENT_LATITUDE);
            Log.e("####", "on CURRENT_LONGITUDE ## " + AppConstant.CURRENT_LONGITUDE);
           */
           viewModel.Latitude.setValue("" + location.getLatitude());
            viewModel.Longitude.setValue("" + location.getLongitude());
            // viewModel.LocationStr.setValue("Lat: " + AppConstant.CURRENT_LATITUDE + "Lon: " + AppConstant.CURRENT_LONGITUDE);
            //   Toast.makeText(AMSApplication.instance,"* "+"Lat: " + AppConstant.CURRENT_LATITUDE + "Lon: " + AppConstant.CURRENT_LONGITUDE+"",Toast.LENGTH_SHORT).show();
            AppConstant.CURRENT_LATITUDE = "" + location.getLatitude();
            AppConstant.CURRENT_LONGITUDE = "" + location.getLongitude();
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
               /* Log.e("####", "address : " + address);
                Log.e("####", "city : " + city);
                Log.e("####", "state : " + state);
                Log.e("####", "country : " + country);
                Log.e("####", "postalCode : " + postalCode);
                Log.e("####", "knownName : " + knownName);
               */
               stringBufferLoc = new StringBuffer();
                stringBufferLoc.append(address);
                viewModel.LocationString.setValue(stringBufferLoc.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(ExcessAuditActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            AuditId=bundle.getInt(AUDIT_MODEL_ID);
            viewModel.AuditId.setValue(AuditId);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Excess Audit");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image, menu);
        MenuItem action_camera = menu.findItem(R.id.action_camera);
        action_camera.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (imageCount < 6) {
                    onCameraClicked();
                } else {
                    Toast.makeText(ExcessAuditActivity.this, "Upload image limit is five only.", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onCameraClicked() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED)
                RequestPermissions.Instance.requestPermission(this, new String[]{Manifest.permission.CAMERA, WRITE_EXTERNAL_STORAGE}, CAMERA_STORAGE_PERMISSION);
            else
                RequestPermissions.Instance.requestPermission(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
            return;
        } else if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            RequestPermissions.Instance.requestPermission(this, new String[]{WRITE_EXTERNAL_STORAGE}, CAMERA_STORAGE_PERMISSION);

        }
        onLaunchCamera(binding.getRoot());
    }

    public void onLaunchCamera(View view) {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        Uri fileProvider = FileProvider.getUriForFile(ExcessAuditActivity.this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    // Returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(APP_TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //    Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                //Uri takenPhotoUri = Uri.fromFile(getPhotoFileUri(photoFileName));
                imageCount++;
                // ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                //  saveBitmapToFile(photoFile).compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                //byte[] byteArray = byteArrayOutputStream.toByteArray();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                Bitmap.createScaledBitmap(BitmapFactory.decodeFile(photoFile.getAbsolutePath()), 400, 400, false).compress(Bitmap.CompressFormat.PNG, 70, byteArrayOutputStream);
                //saveBitmapToFile(photoFile).compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                String imagePath = "";
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        imagePath = saveImageToExternal_Q("AMS_IMG" + System.currentTimeMillis(), saveBitmapToFile(photoFile));
                    } else {
                        imagePath = saveImageToExternal("AMS_IMG" + System.currentTimeMillis(), saveBitmapToFile(photoFile));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                if (imageCount == 1) {
                    viewModel.imageOne.setValue(base64);
                    viewModel.imageOneP.setValue(imagePath);
                }
                if (imageCount == 2) {
                    viewModel.imageTwo.setValue(base64);
                    viewModel.imageTwoP.setValue(imagePath);
                }
                if (imageCount == 3) {
                    viewModel.imageThree.setValue(base64);
                    viewModel.imageThreeP.setValue(imagePath);
                }
                if (imageCount == 4) {
                    viewModel.imageFour.setValue(base64);
                    viewModel.imageFourP.setValue(imagePath);
                }
                if (imageCount == 5) {
                    viewModel.imageFive.setValue(base64);
                    viewModel.imageFiveP.setValue(imagePath);
                }
                viewModel.ImageCount.setValue("" + imageCount);
                //    String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                //Log.e("encoded","encoded--- "+encoded);
            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Bitmap saveBitmapToFile(File file) {
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE = 75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            return selectedBitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public String saveImageToExternal(String imgName, Bitmap bm) throws IOException {
        Uri uri = null;
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            //  if (isStoragePermissionGranted()) { // check or ask permission
            File myDir = new File(root, "/AMS_IMG");
            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            String fname = "Img" + imgName + ".jpg";
            File file = new File(myDir, fname);
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile(); // if file already exists will do nothing
                FileOutputStream out = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            uri = Uri.fromFile(file);
            Log.e("####", "####### Uri ###### " + uri.toString());
            //    Log.e("####","####### Uri ###### "+uri.getPath());
            MediaScannerConnection.scanFile(this, new String[]{file.toString()}, new String[]{file.getName()}, null);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uri.toString();
    }

    public String saveImageToExternal_Q(String imgName, Bitmap bm) throws IOException {
        Uri uri = null;
        try {
            final String relativePath = Environment.DIRECTORY_PICTURES + File.separator + "AMS_IMAGE"; // save directory
            String fileName = imgName; // file name to save file with
            String mimeType = "image/*"; // Mime Types define here
            Bitmap bitmap = bm; // your bitmap file to save

            final ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath);

            final ContentResolver resolver = ExcessAuditActivity.this.getContentResolver();

            OutputStream stream = null;


            try {
                final Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                uri = resolver.insert(contentUri, contentValues);

                if (uri == null) {
                    Log.d("error", "Failed to create new  MediaStore record.");
                    return null;
                }

                stream = resolver.openOutputStream(uri);

                if (stream == null) {
                    Log.d("error", "Failed to get output stream.");
                }

                boolean saved = bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
                if (!saved) {
                    Log.d("error", "Failed to save bitmap.");
                }
            } catch (IOException e) {
                if (uri != null) {
                    resolver.delete(uri, null, null);
                }

            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uri.toString();
    }

}