package com.ams.amsvistara.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.AuditListAdapter;
import com.ams.amsvistara.constants.AppConstant;
import com.ams.amsvistara.databinding.ActivityAuditBinding;
import com.ams.amsvistara.interfaces.AuditClickListener;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.AuditExcessItem;
import com.ams.amsvistara.model.res.AuditMasters;
import com.ams.amsvistara.model.updatedModels.NewAuditMaster;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.viewmodel.AuditViewModel;
import com.ams.amsvistara.ws.repo.AuditAssetRepo;
import com.ams.amsvistara.ws.repo.IAuditAssetRepo;
import com.rscja.deviceapi.RFIDWithUHF;
import com.rscja.utility.StringUtility;
import com.zebra.adc.decoder.Barcode2DWithSoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.ResponseBody;

import static com.ams.amsvistara.constants.AppConstant.TAG_CURRENT_VALUE;
import static com.ams.amsvistara.constants.ConstantStr.AUDIT_MASTER_ITEM;
import static com.ams.amsvistara.constants.ConstantStr.TA_ITEM_DATA;
import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class AuditActivity extends AppCompatActivity implements AuditClickListener {

    ActivityAuditBinding binding;
    AuditViewModel viewModel;
    AuditListAdapter auditListAdapter;
    List<AuditExcessItem> dataModelList = new ArrayList<>();
    public RFIDWithUHF mReader;
    private boolean loopFlag = false;
    Handler handler;
    TagThread tagThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ta_list);

        setReaderParam();
        viewModel = new ViewModelProvider(this).get(AuditViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_audit);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getIntentData();
        initToolbar();
        setAdapter();
        binding.progressBar.setVisibility(View.GONE);

        binding.lLScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("####", "####AppConstant.DEVICE_MODEL lLScanTagRF#### " + AppConstant.DEVICE_MODEL);
                if (AppConstant.DEVICE_MODEL.contains("C72") || AppConstant.DEVICE_MODEL.contains("c72")) {

                    if (mReader.startInventoryTag((byte) 0, (byte) 0)) {
                                                         /*BtInventory.setText(mContext
                                                                 .getString(R.string.title_stop_Inventory));*/
                        loopFlag = true;
                        //    setViewEnabled(false);
                        tagThread = new TagThread(9000);
                        tagThread.start();
                        //StringUtils.toInt(et_between.getText().toString().trim(), 0)
                    } else {
                        mReader.stopInventory();
                                                        /* UIHelper.ToastMessage(mContext,
                                                                 R.string.uhf_msg_inventory_open_fail);*/
                    }
                } else {
                    TAG_CURRENT_VALUE = "";
                    Intent intent = new Intent(AuditActivity.this, BarcodeScannerActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.lLSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> req = new HashMap<String, String>();
                req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", AuditActivity.this));

                List<AuditExcessItem> dataModelListSave = new ArrayList<>();
                for (int i = 0; i < dataModelList.size(); i++) {
                    //   if(dataModelList.get(i).saveflag){

                    dataModelList.get(i).saveflag = true;
                    dataModelListSave.add(dataModelList.get(i));

                    // }
                }
                AuditAssetRepo.getInstance().postAuditAsset(req, dataModelListSave, new IAuditAssetRepo.AuditAssetCallback() {
                    @Override
                    public void onDataLoaded(ResponseBody responseBody) {
                        Log.d("testaudi", "onDataLoaded: ");
                        Toast.makeText(AuditActivity.this, "Audit Assets Saved.",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    @Override
                    public void onDataNotAvailable() {
                        Log.d("testaudi", "onDataNotAvailable: ");
                        Toast.makeText(AuditActivity.this, "ERROR.",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
//            AuditMasters auditMasters = (AuditMasters) getIntent().getSerializableExtra(AUDIT_MASTER_ITEM);
//            dataModelList.addAll(auditMasters.audititems);
            NewAuditMaster auditMaster = (NewAuditMaster) getIntent().getSerializableExtra(AUDIT_MASTER_ITEM);
            dataModelList.add(auditMaster.getAuditItems());

            int sum=0;
            for*(int i=0;i<tools.lenght;i++){
                if(tools.get(i).owned==true){
                    sum=sum+tools.get(i).getFight();//here you can chnage as per your requirment

                }
            }
            //   dataModelList.addAll((List<TRItems>)getIntent().getSerializableExtra(TA_LIST_DATA));

            //    tagModelId = bundle.getInt(TAG_MODEL_ID);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Audit Asset");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


    }


    private void setAdapter() {
        try {
            binding.progressBar.setVisibility(View.VISIBLE);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.recyclerView.setLayoutManager(linearLayoutManager);
            //   Log.e("####","########## tagModelId ######### "+tagModelId);
            auditListAdapter = new AuditListAdapter(dataModelList, this, this);
            binding.recyclerView.setAdapter(auditListAdapter);
            binding.progressBar.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //       populateData();
    }


    @Override
    protected void onResume() {
        super.onResume();
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void cardClicked(AuditExcessItem f) {
        TAG_CURRENT_VALUE = "";
        // binding.progressBar.setVisibility(View.VISIBLE);
      /*  Intent intent = new Intent(AuditActivity.this, TADetailActivity.class);
        intent.putExtra(TA_ITEM_DATA, f);
        startActivity(intent);*/

    }

    private void setReaderParam() {
        try {
            mReader = RFIDWithUHF.getInstance();
        } catch (Exception ex) {

            Toast.makeText(AuditActivity.this, ex.getMessage(),
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

        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // Bundle bundle = msg.getData();
                // String tagEPC = bundle.getString("tagEPC");

                String result = msg.obj + "";
                String[] strs = result.split("@");
                Log.e("####", "############ RF " + strs[0] + "####" + strs[1]);
                Log.e("####", "############result " + result);

                for (int i = 0; i < dataModelList.size(); i++) {
                    if (!dataModelList.get(i).auditdstatus.equalsIgnoreCase("completed")) {
                        if (dataModelList.get(i).qrcode.equalsIgnoreCase(strs[0])) {
                            dataModelList.get(i).saveflag = true;
                        }
                    }
                }
                auditListAdapter.notifyDataSetChanged();


                //  addEPCToList(strs[0],strs[1]);
                // mContext.playSound(1);
            }
        };
    }


    @Override
    public void onPause() {
        super.onPause();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            stopInventory();

            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}