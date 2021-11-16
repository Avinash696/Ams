package com.ams.amsvistara.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.AuditSelectDialogListAdapter;
import com.ams.amsvistara.adapter.PvDialogListAdapter;
import com.ams.amsvistara.db.table.master.PvModels;
import com.ams.amsvistara.interfaces.AuditSelectDialogClickListener;
import com.ams.amsvistara.interfaces.PvDialogClickListener;
import com.ams.amsvistara.model.AuditIdModel;
import com.ams.amsvistara.model.res.AuditMasterRes;
import com.ams.amsvistara.model.res.AuditMasters;
import com.ams.amsvistara.model.updatedModels.NewAuditMaster;
import com.ams.amsvistara.ui.activity.AuditActivity;
import com.ams.amsvistara.ui.activity.ExcessAssetActivity;
import com.ams.amsvistara.ui.activity.ExcessAuditActivity;
import com.ams.amsvistara.ui.activity.PVListActivity;
import com.ams.amsvistara.ws.repo.AuditMasterRepo;
import com.ams.amsvistara.ws.repo.IAuditMasterRepo;
import com.ams.amsvistara.ws.retrofit.RestService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.ConstantStr.AUDIT_MASTER_ITEM;
import static com.ams.amsvistara.constants.ConstantStr.AUDIT_MODEL_ID;
import static com.ams.amsvistara.constants.ConstantStr.AUDIT_TYPE_EXCESS;
import static com.ams.amsvistara.constants.ConstantStr.AUDIT_TYPE_KNOWN;
import static com.ams.amsvistara.constants.ConstantStr.PV_MODEL_ID;
import static com.ams.amsvistara.constants.ConstantStr.PV_TYPE_EXCESS;
import static com.ams.amsvistara.constants.ConstantStr.PV_TYPE_KNOWN;
import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class AuditSelectDialog extends Dialog implements AuditSelectDialogClickListener {

    private RecyclerView list;
    private TextView txtTitle;
    //private EditText filterText = null;
    private AuditSelectDialogListAdapter adapter = null;
    private static final String TAG = "PvModels";
    private Context mContext;
    private String reqType = "";
//    private ArrayList<AuditMasters> auditMastersArrayList;
    private ArrayList<NewAuditMaster> auditMastersArrayList;
    ProgressBar progressBar;

    public AuditSelectDialog(Context context, String reqType) {
        super(context);
        setContentView(R.layout.search_list_dialog);
        this.reqType = reqType;
        auditMastersArrayList = new ArrayList<>();
        txtTitle = (TextView) findViewById(R.id.txtTitle);
         progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        txtTitle.setText("SELECT AUDIT");
        mContext = context;
      /*  filterText = (EditText) findViewById(R.id.etxtSearch);
        filterText.addTextChangedListener(filterTextWatcher);*/
        list = (RecyclerView) findViewById(R.id.productList);
        //List<String> listData = Arrays.asList(productList);
        // ArrayList<String> listData = new ArrayList<>(Arrays.asList(productList));
        Map<String, String> req = new HashMap<String, String>();
        req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", context));


        AuditMasterRepo.getInstance().getAuditMaster(req, new IAuditMasterRepo.AuditMasterCallback() {
            @Override
            public void onDataLoaded(NewAuditMaster newAuditMaster) {
                Log.d(TAG, "onDataLoaded: "+newAuditMaster);
                auditMastersArrayList.addAll(Collections.singleton(newAuditMaster));
                callAdapter();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onDataNotAvailable() {
                Log.d(TAG, "onDataNotAvailable: ");
            }
        });

//        AuditMasterRepo.getInstance().getAuditMaster(req, new IAuditMasterRepo.AuditMasterCallback() {
//            @Override
//            public void onDataLoaded(AuditMasterRes auditMasters) {
//                Log.d("auditData", "onDataLoaded: "+auditMasters);
//                auditMastersArrayList.addAll(auditMasters.auditmasters);
//                callAdapter();
//
//                progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                Log.d("auditData", "onDataNotAvailable: ");
//            }
//        });


       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Log.d(TAG, "Selected Item is = "+list.getItemAtPosition(position));
                viewModel.productName.setValue(list.getItemAtPosition(position).toString());
                dismiss();
            }
        });*/
    }

    public void callAdapter() {
        Log.e("####","##### auditMastersArrayList ###### "+auditMastersArrayList.size());
//        adapter = new AuditSelectDialogListAdapter(auditMastersArrayList, mContext, this);
        adapter = new AuditSelectDialogListAdapter(auditMastersArrayList, mContext, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(adapter);
    }

    private TextWatcher filterTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {

        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            adapter.getFilter().filter(s);


        }
    };

    @Override
    public void onStop() {
        //   filterText.removeTextChangedListener(filterTextWatcher);
    }

    @Override
    public void cardClicked(NewAuditMaster f) {
                Intent intent = null;
        if (reqType.equalsIgnoreCase(AUDIT_TYPE_KNOWN)) {
            intent = new Intent(mContext, AuditActivity.class);
            intent.putExtra(AUDIT_MODEL_ID, f.getAuditID());
//            intent.putExtra(AUDIT_MASTER_ITEM,  f.getAuditItems().get(0).getAssetID());
            intent.putExtra(AUDIT_MASTER_ITEM, (Serializable) f.getAuditItems());
            mContext.startActivity(intent);
        } else if (reqType.equalsIgnoreCase(AUDIT_TYPE_EXCESS)) {
            intent = new Intent(mContext, ExcessAuditActivity.class);
            intent.putExtra(AUDIT_MODEL_ID, f.getAuditID());
            mContext.startActivity(intent);
        }
        dismiss();
    }
    }

//    @Override
//    public void cardClicked(AuditMasters f) {
//        Log.d(TAG, "Selected Item is = " + f.auditid);
//
//        Intent intent = null;
//        if (reqType.equalsIgnoreCase(AUDIT_TYPE_KNOWN)) {
//            intent = new Intent(mContext, AuditActivity.class);
//            intent.putExtra(AUDIT_MODEL_ID, f.auditid);
//            intent.putExtra(AUDIT_MASTER_ITEM, f);
//
//            mContext.startActivity(intent);
//        } else if (reqType.equalsIgnoreCase(AUDIT_TYPE_EXCESS)) {
//            intent = new Intent(mContext, ExcessAuditActivity.class);
//            intent.putExtra(AUDIT_MODEL_ID, f.auditid);
//            mContext.startActivity(intent);
//        }
//        dismiss();
//    }
