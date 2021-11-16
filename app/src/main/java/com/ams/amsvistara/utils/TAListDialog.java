package com.ams.amsvistara.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.TADialogListAdapter;
import com.ams.amsvistara.interfaces.TADialogClickListener;
import com.ams.amsvistara.model.InboundTransferRequestMasters;
import com.ams.amsvistara.model.TAModel;
import com.ams.amsvistara.ui.activity.TAListActivity;
import com.ams.amsvistara.ws.repo.ITARepo;
import com.ams.amsvistara.ws.repo.TARepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.ams.amsvistara.constants.ConstantStr.TA_LIST_DATA;
import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class TAListDialog extends Dialog implements TADialogClickListener {

    private RecyclerView list;
    private TextView txtTitle;
    //private EditText filterText = null;
    private TADialogListAdapter adapter = null;
    private static final String TAG = "PvModels";
    private Context mContext;
    private ProgressBar progressBar;

    public TAListDialog(Context context) {
        super(context);
        setContentView(R.layout.ta_list_dialog);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtTitle.setText("SELECT TRANSFER");
        mContext = context;
      /*  filterText = (EditText) findViewById(R.id.etxtSearch);
        filterText.addTextChangedListener(filterTextWatcher);*/
        list = (RecyclerView) findViewById(R.id.productList);
        //List<String> listData = Arrays.asList(productList);
        // ArrayList<String> listData = new ArrayList<>(Arrays.asList(productList));
        Map<String, String> req = new HashMap<String, String>();
        req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", context));

        TARepo.getInstance().getTA(req, new ITARepo.TACallback() {
            @Override
            public void onDataLoaded(TAModel taModel) {
                progressBar.setVisibility(View.GONE);
                try {
                    callList(taModel.inboundtransferrequestmasters);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onDataNotAvailable() {
                progressBar.setVisibility(View.GONE);

            }
        });


       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Log.d(TAG, "Selected Item is = "+list.getItemAtPosition(position));
                viewModel.productName.setValue(list.getItemAtPosition(position).toString());
                dismiss();
            }
        });*/
    }
    public void callList(ArrayList<InboundTransferRequestMasters> inboundTransferRequestMasters){
        adapter = new TADialogListAdapter(inboundTransferRequestMasters, mContext, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(adapter);
    }
/*
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
    };*/

    @Override
    public void onStop() {
        //   filterText.removeTextChangedListener(filterTextWatcher);
    }

    @Override
    public void cardClicked(InboundTransferRequestMasters f) {
     //   Log.d(TAG, "Selected Item is = " + f.PhysicalVerificationID);

        Intent intent = new Intent(mContext, TAListActivity.class);
        intent.putExtra(TA_LIST_DATA, f.tritems);
        mContext.startActivity(intent);
        dismiss();
    }
}