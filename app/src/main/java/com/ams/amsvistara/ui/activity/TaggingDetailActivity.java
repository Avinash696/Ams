package com.ams.amsvistara.ui.activity;

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
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ams.amsvistara.BuildConfig;
import com.ams.amsvistara.R;
import com.ams.amsvistara.constants.AppConstant;
import com.ams.amsvistara.databinding.ActivityTaggingDetailBinding;
import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.utils.CheckNetwork;
import com.ams.amsvistara.utils.RequestPermissions;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.viewmodel.TaggingDetailViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.rscja.deviceapi.RFIDWithUHF;
import com.rscja.utility.StringUtility;
import com.zebra.adc.decoder.Barcode2DWithSoft;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.ams.amsvistara.constants.AppConstant.TAG_CURRENT_VALUE;
import static com.ams.amsvistara.constants.ConstantStr.ASSET_CODE;
import static com.ams.amsvistara.constants.ConstantStr.ASSET_NAME;
import static com.ams.amsvistara.constants.ConstantStr.TAG_DETAIL_ID;
import static com.ams.amsvistara.constants.ConstantStr.TAG_MODEL_ID;
import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;


public class TaggingDetailActivity extends AppCompatActivity implements LocationListener {
    ActivityTaggingDetailBinding binding;
    private TaggingDetailViewModel viewModel;
    int CAMERA_PERMISSION = 4;
    int CAMERA_STORAGE_PERMISSION = 11;
    public final String APP_TAG = "Tagging Image";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo_.jpg";
    private File photoFile;
    private int imageCount = 0;
    private LocationManager locationManager;
    private StringBuffer stringBufferLoc = new StringBuffer();
    public RFIDWithUHF mReader;
    private boolean loopFlag = false;
    Handler handler;
    public Barcode2DWithSoft mReaderBar;
    String seldata = "ASCII";
    FusedLocationProviderClient mFusedLocationClient;
    Location mLastLocation;
    double getLatitude_ = 0.0;
    double getLongitude_ = 0.0;
    TagThread tagThread;

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (viewModel != null)
                viewModel.QRCode.setValue(TAG_CURRENT_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        setReaderParam();
        networkCheck();
        //    setContentView(R.layout.activity_tagging_detail);

        viewModel = new ViewModelProvider(this).get(TaggingDetailViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tagging_detail);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        Log.e("####", "####### AppConstant.DEVICE_MODEL ###### " + AppConstant.DEVICE_MODEL);
        if (AppConstant.DEVICE_MODEL.contains("C72") || AppConstant.DEVICE_MODEL.contains("c72")) {
            binding.lLScanTagRF.setVisibility(View.VISIBLE);
            binding.viewDivider.setVisibility(View.VISIBLE);
            binding.btnLayout.setWeightSum(3);
        } else {
            binding.lLScanTagRF.setVisibility(View.GONE);
            binding.viewDivider.setVisibility(View.GONE);
            binding.btnLayout.setWeightSum(2);
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria crit = new Criteria();
        crit.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(crit, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        getLocation();
        viewModel.LocationStr.setValue("" + stringBufferLoc.toString());
        getIntentData();

        viewModel.setUiWithTagDetail(viewModel.fetchTagDetailData(viewModel.tagID.getValue(), viewModel.tagDetailID.getValue()));

        viewModel.VendorName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e("#####", "###### VendorName ##### " + s);
            }
        });

        viewModel.tagID.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
        imageCount = 0;
        viewModel.ImageCount.setValue("" + imageCount);
        initToolbar();
        binding.lLScanTagRF.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View view) {
                                                       Log.e("####", "####AppConstant.DEVICE_MODEL lLScanTagRF#### " + AppConstant.DEVICE_MODEL);
                                                       if (AppConstant.DEVICE_MODEL.contains("C72") || AppConstant.DEVICE_MODEL.contains("c72")) {

                                                           if (mReader.startInventoryTag((byte) 0, (byte) 0)) {
                                                         /*BtInventory.setText(mContext
                                                                 .getString(R.string.title_stop_Inventory));*/
                                                               loopFlag = true;
                                                               //    setViewEnabled(false);
                                                               tagThread= new TagThread(9000);
                                                               tagThread.start();
                                                               //StringUtils.toInt(et_between.getText().toString().trim(), 0)
                                                           } else {
                                                               mReader.stopInventory();
                                                        /* UIHelper.ToastMessage(mContext,
                                                                 R.string.uhf_msg_inventory_open_fail);*/
                                                           }
                                                       } else {
                                                           TAG_CURRENT_VALUE = "";
                                                           Intent intent = new Intent(TaggingDetailActivity.this, BarcodeScannerActivity.class);
                                                           startActivity(intent);
                                                       }
                                                   }
                                               }
        );
        binding.lLScanTagQR.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View view) {
                                                       Log.e("####", "####AppConstant.DEVICE_MODELlLScanTagQR #### " + AppConstant.DEVICE_MODEL);
                                                       if (AppConstant.DEVICE_MODEL.contains("C72") || AppConstant.DEVICE_MODEL.contains("c72")) {
                                                           try {
                                                               Log.e("####", "################# lLScanTagQR ############");
                                                               viewModel.QRCode.setValue("");
                                                               mReaderBar.scan();
                                                               mReaderBar.setScanCallback(mScanCallback);
                                                           } catch (Exception ex) {

                                                               Toast.makeText(TaggingDetailActivity.this, ex.getMessage(),
                                                                       Toast.LENGTH_SHORT).show();

                                                               return;
                                                           }
                                                       } else {
                                                           TAG_CURRENT_VALUE = "";
                                                           Intent intent = new Intent(TaggingDetailActivity.this, BarcodeScannerActivity.class);
                                                           startActivity(intent);
                                                       }
                                                   }
                                               }
        );

        binding.lLSave.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  try {
                                                      if (viewModel.isValidForSave()) {
                                                          binding.progressBar.setVisibility(View.VISIBLE);
                                                          viewModel.callBackPress.setValue(false);

                                                          TagItems tagItems = viewModel.updateTagDetailData(viewModel.tagID.getValue(), viewModel.tagDetailID.getValue(), true);
                                                          if (tagItems != null) {
                                                              Map<String, String> req = new HashMap<String, String>();
                                                              req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", TaggingDetailActivity.this));
                                                              viewModel.saveTagAsset(tagItems, req, viewModel.tagID.getValue(), viewModel.tagDetailID.getValue());
                                                          }

                                                      }
                                                  } catch (Exception e) {
                                                      e.printStackTrace();
                                                  }
                                              }
                                          }
        );

        callObserver();
        binding.progressBar.setVisibility(View.GONE);
    }

    private void callObserver() {
        viewModel.callBackPress.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    finish();
                }
            }
        });
    }

    private void networkCheck() {
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
    }

    private void setReaderParam() {
        try {
            mReader = RFIDWithUHF.getInstance();
        } catch (Exception ex) {

            Toast.makeText(TaggingDetailActivity.this, ex.getMessage(),
                    Toast.LENGTH_SHORT).show();

            return;
        }
        try {
            mReaderBar = Barcode2DWithSoft.getInstance();
        } catch (Exception ex) {

            Toast.makeText(TaggingDetailActivity.this, ex.getMessage(),
                    Toast.LENGTH_SHORT).show();

            return;
        }
        if (mReader != null) {
            new Handler(Looper.myLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mReader.init();
                }
            });
        }

        if (mReaderBar != null) {
            new Handler(Looper.myLooper()).post(new Runnable() {
                @Override
                public void run() {
                    boolean result = false;

                    if (mReaderBar != null) {
                        result = mReaderBar.open(TaggingDetailActivity.this);

                        if (result) {
                            /*
                             * mReader.setParameter(324, 1); mReader.setParameter(300,
                             * 0); // Snapshot Aiming mReader.setParameter(361, 0); //
                             * Image Capture Illumination
                             */
                            mReaderBar.setScanCallback(mScanCallback);
                        }
                    }
                }
            });
        }


        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // Bundle bundle = msg.getData();
                // String tagEPC = bundle.getString("tagEPC");

                String result = msg.obj + "";
                String[] strs = result.split("@");
                Log.e("####", "############ RF " + strs[0] + "####" + strs[1]);
                //  addEPCToList(strs[0],strs[1]);
                // mContext.playSound(1);
            }
        };
    }

    public Barcode2DWithSoft.ScanCallback mScanCallback = new Barcode2DWithSoft.ScanCallback() {
        @Override
        public void onScanComplete(int i, int length, byte[] data) {

            Log.e("ErDSoftScanFragment", "onScanComplete() i=" + i);

            if (length < 1) {

                return;
            }

            String barCode = "";
            try {
                Toast.makeText(TaggingDetailActivity.this, "Scan Done.", Toast.LENGTH_SHORT).show();
                barCode = new String(data, 0, length, seldata);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           /* if (!barCode.isEmpty()) {
                SoundManage.PlaySound(MainActivity.this, SoundType.SUCCESS);
            }*/
            viewModel.QRCode.setValue(barCode);
            // tv.setText(barCode);

        }
    };

    @Override
    public void onPause() {

        super.onPause();
        if (mReaderBar != null) {
            mReaderBar.close();
        }
        stopInventory();

    }

    private void stopInventory() {

        if (loopFlag) {

            loopFlag = false;


            if (mReader.stopInventory()) {

            } else {

            }

        }
    }

    class TagThread extends Thread {

        private int mBetween = 80;
        HashMap<String, String> map;

        public TagThread(int iBetween) {
            mBetween = iBetween;
        }

        public void run() {
            String strTid;
            String strResult;

            String[] res = null;

            while (loopFlag) {

                // strUII = mContext.mReader.readUidFormBuffer();
                //
                // Log.i("UHFReadTagFragment", "TagThread uii=" + strUII);
                //
                // if (StringUtils.isNotEmpty(strUII)) {
                // strEPC = mContext.mReader.convertUiiToEPC(strUII);
                //
                // Message msg = handler.obtainMessage();
                // // Bundle bundle = new Bundle();
                // // bundle.putString("tagEPC", strEPC);
                //
                // // msg.setData(bundle);
                //
                // msg.obj = strEPC;
                // handler.sendMessage(msg);
                //
                // }

                res = mReader.readTagFromBuffer();//.readTagFormBuffer();

                if (res != null) {

                    strTid = res[0];
                    if (!strTid.equals("") && !strTid.equals("0000000000000000") && !strTid.equals("000000000000000000000000")) {
                        strResult = "TID:" + strTid + "\n";
                    } else {
                        strResult = "";
                    }
                    Message msg = handler.obtainMessage();
                    msg.obj = strResult + "EPC:"
                            + mReader.convertUiiToEPC(res[1]) + "@"
                            + res[2];
                    Log.i("msg", strResult + "EPC:"
                            + mReader.convertUiiToEPC(res[1]) + "@"
                            + res[2]);
                    handler.sendMessage(msg);
                }
                try {
                    Thread.currentThread().sleep(mBetween);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }

    public boolean vailHexInput(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        if (str.length() % 2 == 0) {
            return StringUtility.isHexNumberRex(str);
        }

        return false;
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString(ASSET_NAME).length() > 0) {
                viewModel.AssetName.setValue(bundle.getString(ASSET_NAME));
                viewModel.AssetNumber.setValue(bundle.getString(ASSET_CODE));
                viewModel.tagID.setValue(bundle.getInt(TAG_MODEL_ID));
                viewModel.tagDetailID.setValue(bundle.getInt(TAG_DETAIL_ID));
            }
        }


    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Tagging Detail");
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
                    Toast.makeText(TaggingDetailActivity.this, "Upload image limit is five only.", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == 139 || keyCode == 280) {
            if (AppConstant.DEVICE_MODEL.contains("C72") || AppConstant.DEVICE_MODEL.contains("c72")) {
                if (event.getRepeatCount() == 0) {
                    viewModel.QRCode.setValue("");
                    mReaderBar.scan();
                    mReaderBar.setScanCallback(mScanCallback);
                }
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            try {
                stopInventory();
                tagThread.stop();
                onBackPressed();
            }catch (Exception e){
                onBackPressed();
                e.printStackTrace();
            }
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
        Uri fileProvider = FileProvider.getUriForFile(TaggingDetailActivity.this, BuildConfig.APPLICATION_ID + ".provider", photoFile);
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
                //   Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                //Uri takenPhotoUri = Uri.fromFile(getPhotoFileUri(photoFileName));
                imageCount++;
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

            final ContentResolver resolver = TaggingDetailActivity.this.getContentResolver();

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
                                        geocoder = new Geocoder(TaggingDetailActivity.this, Locale.getDefault());

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
                                                viewModel.LocationStr.setValue("Lat: " + AppConstant.CURRENT_LATITUDE + "Lon: " + AppConstant.CURRENT_LONGITUDE);
                                            } else {
                                                viewModel.LocationStr.setValue("" + stringBufferLoc.toString());
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
            Log.e("####", "on CURRENT_LATITUDE ## " + AppConstant.CURRENT_LATITUDE);
            Log.e("####", "on CURRENT_LONGITUDE ## " + AppConstant.CURRENT_LONGITUDE);
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
                Log.e("####", "address : " + address);
                Log.e("####", "city : " + city);
                Log.e("####", "state : " + state);
                Log.e("####", "country : " + country);
                Log.e("####", "postalCode : " + postalCode);
                Log.e("####", "knownName : " + knownName);
                stringBufferLoc = new StringBuffer();
                stringBufferLoc.append(address);
                viewModel.LocationStr.setValue(stringBufferLoc.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(TaggingDetailActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    protected void onDestroy() {
        try {
            if (mReaderBar != null) {
                mReaderBar.close();
            }
            tagThread.stop();
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onDestroy();

    }
}