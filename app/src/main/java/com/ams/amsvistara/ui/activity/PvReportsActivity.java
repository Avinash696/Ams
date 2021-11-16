package com.ams.amsvistara.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.PvReportsAdapter;
import com.ams.amsvistara.databinding.ActivityPvReportsBinding;
import com.ams.amsvistara.interfaces.PvReportsClickListener;
import com.ams.amsvistara.model.PVReports;
import com.ams.amsvistara.model.PvReportsItem;
import com.ams.amsvistara.model.ReportItems;
import com.ams.amsvistara.viewmodel.PvReportsViewModel;

import java.util.List;
import java.util.Objects;

public class PvReportsActivity extends AppCompatActivity implements PvReportsClickListener {
    ActivityPvReportsBinding binding;
    PvReportsViewModel viewModel;
    PvReportsAdapter pvReportsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_pv_reports);
        viewModel = new ViewModelProvider(this).get(PvReportsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pv_reports);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.fetchPVReportsData(this);
        viewModel.pvReportsMutableLiveData.observe(this, new Observer<PVReports>() {
            @Override
            public void onChanged(PVReports pvReports) {

                setAdapter(pvReports.reportitems);
            }
        });
        getIntentData();
        initToolbar();
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("PV Report");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


    }



    private void setAdapter(List<ReportItems> reportitems) {
        try{
            binding.progressBar.setVisibility(View.VISIBLE);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.recyclerView.setLayoutManager(linearLayoutManager);
            //   Log.e("####","########## tagModelId ######### "+tagModelId);
            pvReportsAdapter = new PvReportsAdapter( reportitems, this, this);
            binding.recyclerView.setAdapter(pvReportsAdapter);
            binding.progressBar.setVisibility(View.GONE);
        }catch (Exception e){
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void cardClicked(PvReportsItem f) {

    }
}