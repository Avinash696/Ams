package com.ams.amsvistara.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.LocDialogListAdapter;
import com.ams.amsvistara.db.table.master.LocationMasters;
import com.ams.amsvistara.interfaces.LocDialogClickListener;
import com.ams.amsvistara.viewmodel.PVDetailViewModel;

import java.util.ArrayList;

public class LocationListDialog extends Dialog implements LocDialogClickListener {

    private RecyclerView list;
    private TextView txtTitle;
    //private EditText filterText = null;
    private LocDialogListAdapter adapter = null;
    private static final String TAG = "PvModels";
    private Context mContext;
    private PVDetailViewModel viewModel;

    public LocationListDialog(Context context, ArrayList<LocationMasters> pvModels, PVDetailViewModel viewModel) {
        super(context);
        setContentView(R.layout.search_list_dialog);
        this.viewModel = viewModel;
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("LOCATION");
        mContext = context;
      /*  filterText = (EditText) findViewById(R.id.etxtSearch);
        filterText.addTextChangedListener(filterTextWatcher);*/
        list = (RecyclerView) findViewById(R.id.productList);
        //List<String> listData = Arrays.asList(productList);
        // ArrayList<String> listData = new ArrayList<>(Arrays.asList(productList));
        adapter = new LocDialogListAdapter(pvModels, context, this);
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
    public void cardClicked(LocationMasters f) {
        Log.d(TAG, "Selected Item is = " + f);
        viewModel.Location.setValue(f.LocationName);
        viewModel.LocationID.setValue(f.LocationID);
     /*   Intent intent = new Intent(mContext, PVListActivity.class);
        intent.putExtra(PV_MODEL_ID, f.PhysicalVerificationID);
        mContext.startActivity(intent);*/

        dismiss();
    }
}