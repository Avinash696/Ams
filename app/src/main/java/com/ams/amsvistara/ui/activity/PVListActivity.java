package com.ams.amsvistara.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.PVListAdapter;
import com.ams.amsvistara.databinding.ActivityPvListBinding;
import com.ams.amsvistara.db.AppDatabase;
import com.ams.amsvistara.db.table.master.MasterData;
import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.interfaces.CustomClickListener;
import com.ams.amsvistara.model.PVListModel;
import com.ams.amsvistara.viewmodel.PVListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ams.amsvistara.constants.ConstantStr.ASSET_CODE;
import static com.ams.amsvistara.constants.ConstantStr.ASSET_NAME;
import static com.ams.amsvistara.constants.ConstantStr.PV_DETAIL_ID;
import static com.ams.amsvistara.constants.ConstantStr.PV_MODEL_ID;

public class PVListActivity extends AppCompatActivity implements CustomClickListener {
    ActivityPvListBinding binding;
    PVListViewModel viewModel;
    private PVListAdapter pVListAdapter;
    private int pVModelId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_pv_list);

        viewModel = new ViewModelProvider(this).get(PVListViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pv_list);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getIntentData();
        initToolbar();
        //setAdapter();
        binding.progressBar.setVisibility(View.GONE);
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pVModelId = bundle.getInt(PV_MODEL_ID);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Physical Verification List");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
    }

    private void setAdapter() {
        try {
            binding.progressBar.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.recyclerView.setLayoutManager(linearLayoutManager);
         //   Log.e("####","viewModel.fetchPVData(pVModelId) "+viewModel.fetchPVData(pVModelId).size());
            pVListAdapter = new PVListAdapter(viewModel.fetchPVData(pVModelId), this, this);
            binding.recyclerView.setAdapter(pVListAdapter);
            binding.progressBar.setVisibility(View.GONE);
        }catch (Exception e){
            e.printStackTrace();
        }
     //   populateData();
    }

    private void populateDataFromDB() {
        MasterData masterDataList = new MasterData();
        masterDataList = AppDatabase.getAppDatabase(this).masterDataDao().getMasterData();
        pVListAdapter = new PVListAdapter(masterDataList.PVModels.get(0).PVItems, this, this);
        binding.recyclerView.setAdapter(pVListAdapter);
    }

    private void populateData() {
        List<PVListModel> dataModelList = new ArrayList<>();

        dataModelList.add(new PVListModel("104000024", "PROCUREMENT OF TRESTLE - AMD"));
        dataModelList.add(new PVListModel("112000025", "NOTE COUNTING MACHINE CRUSADER LIGHT"));
        dataModelList.add(new PVListModel("112000104", "CO2 SQUEEZE FIRE EXT- 4.5KG 1 AMD"));
        dataModelList.add(new PVListModel("112000884", "CP PLUS 04 CH. DVR"));
        dataModelList.add(new PVListModel("112000885", "SEAGATE 6 TB SURVEILLANCE HDD"));
        dataModelList.add(new PVListModel("112000962", "HIKVISION IP IR DOME CAMERA"));
        dataModelList.add(new PVListModel("112000963", "HIKVISION IP IR DOME CAMERA"));
        dataModelList.add(new PVListModel("112000964", "HIKVISION IP IR DOME CAMERA"));

       /* PVListAdapter = new PVListAdapter(dataModelList, this, this);
        binding.recyclerView.setAdapter(PVListAdapter);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem action_search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) action_search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pVListAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {

                    pVListAdapter.getFilter().filter(newText);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
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

    @Override
    protected void onResume() {
        super.onResume();
        try {
            binding.progressBar.setVisibility(View.GONE);
            if(pVModelId!=0) {
                setAdapter();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void cardClicked(PvItems f) {
        binding.progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(PVListActivity.this, PVDetailActivity.class);
        intent.putExtra(ASSET_CODE, "" + f.AssetNumber);
        intent.putExtra(ASSET_NAME, "" + f.AssetName);
        intent.putExtra(PV_MODEL_ID, f.PVID);
        intent.putExtra(PV_DETAIL_ID, f.PVDetailID);
        startActivity(intent);

    }


}