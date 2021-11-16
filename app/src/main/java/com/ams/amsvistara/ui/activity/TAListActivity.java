package com.ams.amsvistara.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.TAListAdapter;
import com.ams.amsvistara.databinding.ActivityTaListBinding;
import com.ams.amsvistara.interfaces.TAClickListener;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.viewmodel.TAListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ams.amsvistara.constants.AppConstant.TAG_CURRENT_VALUE;
import static com.ams.amsvistara.constants.ConstantStr.TA_ITEM_DATA;
import static com.ams.amsvistara.constants.ConstantStr.TA_LIST_DATA;

public class TAListActivity extends AppCompatActivity implements TAClickListener {

    ActivityTaListBinding binding;
    TAListViewModel viewModel;
    TAListAdapter taListAdapter;
    List<TRItems> dataModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ta_list);
        viewModel = new ViewModelProvider(this).get(TAListViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ta_list);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getIntentData();
        initToolbar();
          setAdapter();
        binding.progressBar.setVisibility(View.GONE);


    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dataModelList.addAll((List<TRItems>)getIntent().getSerializableExtra(TA_LIST_DATA));

        //    tagModelId = bundle.getInt(TAG_MODEL_ID);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Transfer Asset List");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


    }


    private void setAdapter() {
        try{
            binding.progressBar.setVisibility(View.VISIBLE);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.recyclerView.setLayoutManager(linearLayoutManager);
         //   Log.e("####","########## tagModelId ######### "+tagModelId);
            taListAdapter = new TAListAdapter(dataModelList, this, this);
            binding.recyclerView.setAdapter(taListAdapter);
            binding.progressBar.setVisibility(View.GONE);
        }catch (Exception e){
            e.printStackTrace();
        }
        //       populateData();
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
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void cardClicked(TRItems f) {
        TAG_CURRENT_VALUE="";
        binding.progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(TAListActivity.this, TADetailActivity.class);
        intent.putExtra(TA_ITEM_DATA, f);
        startActivity(intent);

    }
}