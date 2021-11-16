package com.ams.amsvistara.ui.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.multidex.BuildConfig;

//import com.ams.amsvistara.BuildConfig;
import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.ActivityExcessAssetBinding;
import com.ams.amsvistara.db.AppDatabase;
import com.ams.amsvistara.db.table.master.LocationMasters;
import com.ams.amsvistara.db.table.master.SubLocationMasters;
import com.ams.amsvistara.utils.LocationListExcessDialog;
import com.ams.amsvistara.utils.RequestPermissions;
import com.ams.amsvistara.utils.SubLocationListExcessDialog;
import com.ams.amsvistara.viewmodel.ExcessAssetViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.ams.amsvistara.constants.ConstantStr.ASSET_CODE;
import static com.ams.amsvistara.constants.ConstantStr.ASSET_NAME;
import static com.ams.amsvistara.constants.ConstantStr.PV_DETAIL_ID;
import static com.ams.amsvistara.constants.ConstantStr.PV_MODEL_ID;

public class ExcessAssetActivity extends AppCompatActivity {
    ActivityExcessAssetBinding binding;
    ExcessAssetViewModel viewModel;
    private int CAMERA_PERMISSION = 4;
    private int CAMERA_STORAGE_PERMISSION = 11;
    public final String APP_TAG = "PV Image";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo_.jpg";
    private File photoFile;
    private int imageCount = 0;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_excess_asset);
        viewModel = new ViewModelProvider(this).get(ExcessAssetViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_excess_asset);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getIntentData();
        imageCount = 0;
        viewModel.ImageCount.setValue("" + imageCount);
        getIntentData();

        initToolbar();
        callObserver();

        binding.rLCapDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                Log.d("rawat", "onClick:excess ");
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(ExcessAssetActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                if ((monthOfYear + 1) < 10) {
                                    if (dayOfMonth < 10) {
                                        viewModel.CapDate.setValue(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                    } else {
                                        viewModel.CapDate.setValue(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                                    }
                                } else {
                                    if (dayOfMonth < 10) {
                                        viewModel.CapDate.setValue(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                    } else {
                                        viewModel.CapDate.setValue(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                    }
                                }

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        binding.rLLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<LocationMasters> locationMastersList = new ArrayList<>();
                LocationMasters locationMasters = new LocationMasters();
                locationMasters.LocationName = "Select Location";
                locationMasters.LocationCode = "Select Location";
                locationMasters.LocationID = 00;
                locationMastersList.add(locationMasters);
                locationMastersList.addAll(AppDatabase.getAppDatabase(ExcessAssetActivity.this).masterDataDao().getMasterData().LocationMasters);
                LocationListExcessDialog locationListDialog = new LocationListExcessDialog(ExcessAssetActivity.this, locationMastersList, viewModel);
                locationListDialog.show();

            }
        });
        binding.rLSubLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewModel.LocationID.getValue() != null) {
                    try {
                        binding.progressBar.setVisibility(View.VISIBLE);
                        ArrayList<SubLocationMasters> subLocationMasters = new ArrayList<SubLocationMasters>();
                        ArrayList<SubLocationMasters> subLocationMastersTemp = new ArrayList<SubLocationMasters>();
                        SubLocationMasters subLocationMastersM = new SubLocationMasters();
                        subLocationMastersM.SubLocationName = "Select Sub Location";
                        subLocationMastersM.SubLocationCode = "Select Sub Location";
                        subLocationMastersM.SubLocationID = 00;
                        subLocationMastersTemp.addAll(AppDatabase.getAppDatabase(ExcessAssetActivity.this).masterDataDao().getMasterData().SubLocationMasters);
                        for (int i = 0; i < subLocationMastersTemp.size(); i++) {
                            if (subLocationMastersTemp.get(i).SubLocationID == viewModel.LocationID.getValue()) {
                                subLocationMasters.add(subLocationMastersM);
                                subLocationMasters.add(subLocationMastersTemp.get(i));
                            }
                        }
                        binding.progressBar.setVisibility(View.GONE);
                        SubLocationListExcessDialog subLocationListDialog = new SubLocationListExcessDialog(ExcessAssetActivity.this, subLocationMasters, viewModel);
                        subLocationListDialog.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
      /*  if (viewModel.AssetTypeCode.getValue() == 3) {
            binding.toggleAT.check(R.id.radioATCountable);
        } else {
            binding.toggleAT.check(R.id.radioATTaggable);
        }

        if (viewModel.AssetSurfaceCode.getValue() == 2) {
            binding.toggleAS.check(R.id.toggleASMetallic);
        } else {
            binding.toggleAS.check(R.id.toggleASNonMetallic);
        }
               *//* if (viewModel.HeatedAsset.getValue().equalsIgnoreCase("yes")) {
                    binding.toggleHA.check(R.id.toggleHAYes);
                } else {
                    binding.toggleHA.check(R.id.toggleHANo);
                }*//*

        if (viewModel.AssetConditionCode.getValue() == 2) {
            binding.toggleAC.check(R.id.toggleACWorking);
        } else {
            binding.toggleAC.check(R.id.toggleACNotWorking);
        }*/
      /*  new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.progressBar.setVisibility(View.GONE);
            }
        }, 1000);*/


    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imageCount = Integer.parseInt(viewModel.ImageCount.getValue());
            viewModel.AssetName.setValue(bundle.getString(ASSET_NAME));
            viewModel.AssetNumber.setValue(bundle.getString(ASSET_CODE));
            viewModel.pVID.setValue(bundle.getInt(PV_MODEL_ID));
            viewModel.pVDetailID.setValue(bundle.getInt(PV_DETAIL_ID));

            // viewModel.checkRecordAvail(viewModel.AssetCode.getValue());

            //   if (AppDatabase.getAppDatabase(AMSApplication.instance).pVItemTableDao().getSaveFlagPVItemTable(viewModel.AssetCode.getValue())) {

            // }
        }


    }

    private void callObserver() {
        viewModel.showProgress.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!aBoolean){
                 //   finish();
                }
            }
        });
        viewModel.pVID.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
        viewModel.isRadioSelection.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                int toggleATSelected = binding.toggleAT.getCheckedRadioButtonId();
                int toggleASSelected = binding.toggleAS.getCheckedRadioButtonId();
                int toggleACSelected = binding.toggleAC.getCheckedRadioButtonId();
                //    int toggleHASelected = binding.toggleHA.getCheckedRadioButtonId();

                if (toggleATSelected < 1) {
                    viewModel.AssetType.setValue("");

                } else {
                    RadioButton radioButton = (RadioButton) findViewById(toggleATSelected);
                    viewModel.AssetType.setValue(radioButton.getText().toString());
                    if(toggleATSelected==R.id.radioATCountable) {
                        viewModel.AssetTypeCode.setValue(3);
                    }else {
                        viewModel.AssetTypeCode.setValue(2);
                    }
                }
                if (toggleASSelected < 1) {

                    viewModel.AssetSurface.setValue("");
                } else {
                    RadioButton radioButton = (RadioButton) findViewById(toggleASSelected);
                    viewModel.AssetSurface.setValue(radioButton.getText().toString());
                    if(toggleASSelected==R.id.toggleASMetallic) {
                        viewModel.AssetSurfaceCode.setValue(2);
                    }else {
                        viewModel.AssetSurfaceCode.setValue(3);
                    }
                }
                if (toggleACSelected < 1) {

                    viewModel.AssetCondition.setValue("");
                } else {
                    RadioButton radioButton = (RadioButton) findViewById(toggleACSelected);
                    viewModel.AssetCondition.setValue(radioButton.getText().toString());
                    if(toggleACSelected==R.id.toggleACWorking) {
                        viewModel.AssetConditionCode.setValue(2);
                    }else {
                        viewModel.AssetConditionCode.setValue(3);
                    }
                }
               /* if (toggleHASelected < 1) {
                    viewModel.HeatedAsset.setValue("");

                } else {
                    RadioButton radioButton = (RadioButton) findViewById(toggleHASelected);
                    viewModel.HeatedAsset.setValue(radioButton.getText().toString());
                }*/
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Excess Asset");
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
                    Toast.makeText(ExcessAssetActivity.this, "Upload image limit is five only.", Toast.LENGTH_SHORT).show();
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
        Uri fileProvider = FileProvider.getUriForFile(ExcessAssetActivity.this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
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

            final ContentResolver resolver = ExcessAssetActivity.this.getContentResolver();

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