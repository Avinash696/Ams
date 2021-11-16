package com.ams.amsvistara.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.PvDialogListAdapter;
import com.ams.amsvistara.db.table.master.PvModels;
import com.ams.amsvistara.interfaces.PvDialogClickListener;
import com.ams.amsvistara.ui.activity.ExcessAssetActivity;
import com.ams.amsvistara.ui.activity.PVListActivity;

import java.util.ArrayList;

import static com.ams.amsvistara.constants.ConstantStr.PV_MODEL_ID;
import static com.ams.amsvistara.constants.ConstantStr.PV_TYPE_EXCESS;
import static com.ams.amsvistara.constants.ConstantStr.PV_TYPE_KNOWN;

public class PVListPvDialog extends Dialog implements PvDialogClickListener {

    private RecyclerView list;
    private TextView txtTitle;
    //private EditText filterText = null;
    private PvDialogListAdapter adapter = null;
    private static final String TAG = "PvModels";
    private Context mContext;
    private String reqType = "";

    public PVListPvDialog(Context context, ArrayList<PvModels> pvModels, String reqType) {
        super(context);
        setContentView(R.layout.search_list_dialog);
        this.reqType = reqType;
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("SELECT PV");
        mContext = context;
      /*  filterText = (EditText) findViewById(R.id.etxtSearch);
        filterText.addTextChangedListener(filterTextWatcher);*/
        list = (RecyclerView) findViewById(R.id.productList);
        //List<String> listData = Arrays.asList(productList);
        // ArrayList<String> listData = new ArrayList<>(Arrays.asList(productList));
        adapter = new PvDialogListAdapter(pvModels, context, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(adapter);
       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Log.d(TAG, "Selected Item is = "+list.getItemAtPosition(position));
                viewModel.productName.setValue(list.getItemAtPosition(position).toString());
                dismiss();
            }
        });*/
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
    public void cardClicked(PvModels f) {
        Log.d(TAG, "Selected Item is = " + f.PhysicalVerificationID);

        Intent intent = null;
        if (reqType.equalsIgnoreCase(PV_TYPE_KNOWN)) {
            intent = new Intent(mContext, PVListActivity.class);
            intent.putExtra(PV_MODEL_ID, f.PhysicalVerificationID);
            mContext.startActivity(intent);
        } else if (reqType.equalsIgnoreCase(PV_TYPE_EXCESS)) {
            intent = new Intent(mContext, ExcessAssetActivity.class);
            intent.putExtra(PV_MODEL_ID, f.PhysicalVerificationID);
            mContext.startActivity(intent);
        }

        dismiss();
    }
}