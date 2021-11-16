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
import com.ams.amsvistara.adapter.TaggingReportsAdapter;
import com.ams.amsvistara.databinding.ActivityTaggingReportsBinding;
import com.ams.amsvistara.interfaces.TaggingReportsClickListener;
import com.ams.amsvistara.model.PVReports;
import com.ams.amsvistara.model.ReportItems;
import com.ams.amsvistara.model.TaggingReports;
import com.ams.amsvistara.model.TaggingReportsItem;
import com.ams.amsvistara.viewmodel.TaggingReportsViewModel;

import java.util.List;
import java.util.Objects;

public class TaggingReportsActivity extends AppCompatActivity implements TaggingReportsClickListener {

    ActivityTaggingReportsBinding binding;
    TaggingReportsViewModel viewModel;
    TaggingReportsAdapter taggingReportsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_tagging_reports);
        viewModel = new ViewModelProvider(this).get(TaggingReportsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tagging_reports);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.fetchTaggingReportsData(this);
        viewModel.taggingReportsMutableLiveData.observe(this, new Observer<TaggingReports>() {
            @Override
            public void onChanged(TaggingReports taggingReportsItem) {

                setAdapter(taggingReportsItem.reportitems);
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
        getSupportActionBar().setTitle("Tagging Report");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


    }

    private void setAdapter(List<ReportItems> reportItems) {
        try{
            binding.progressBar.setVisibility(View.VISIBLE);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.recyclerView.setLayoutManager(linearLayoutManager);
            //   Log.e("####","########## tagModelId ######### "+tagModelId);
            taggingReportsAdapter = new TaggingReportsAdapter(reportItems, this, this);
            binding.recyclerView.setAdapter(taggingReportsAdapter);
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
    public void cardClicked(TaggingReportsItem f) {

    }
}