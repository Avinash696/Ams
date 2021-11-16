package com.ams.amsvistara.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.ams.amsvistara.adapter.TaggingListAdapter;
import com.ams.amsvistara.databinding.ActivityTaggingListBinding;
import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.interfaces.TaggingClickListener;
import com.ams.amsvistara.model.TaggingListModel;
import com.ams.amsvistara.viewmodel.TaggingListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ams.amsvistara.constants.AppConstant.TAG_CURRENT_VALUE;
import static com.ams.amsvistara.constants.ConstantStr.ASSET_CODE;
import static com.ams.amsvistara.constants.ConstantStr.ASSET_NAME;
import static com.ams.amsvistara.constants.ConstantStr.TAG_DETAIL_ID;
import static com.ams.amsvistara.constants.ConstantStr.TAG_MODEL_ID;

public class TaggingListActivity extends AppCompatActivity implements TaggingClickListener {
    ActivityTaggingListBinding binding;
    TaggingListViewModel viewModel;
    private TaggingListAdapter taggingListAdapter;
    private int tagModelId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_pv_list);

        viewModel = new ViewModelProvider(this).get(TaggingListViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tagging_list);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getIntentData();
        initToolbar();
      //  setAdapter();
        binding.progressBar.setVisibility(View.GONE);
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tagModelId = bundle.getInt(TAG_MODEL_ID);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Tagging List");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


    }

    private void setAdapter() {
        try{
            binding.progressBar.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        Log.e("####","########## tagModelId ######### "+tagModelId);
        taggingListAdapter = new TaggingListAdapter(viewModel.fetchTaggingData(tagModelId), this, this);
        binding.recyclerView.setAdapter(taggingListAdapter);
            binding.progressBar.setVisibility(View.GONE);
        }catch (Exception e){
            e.printStackTrace();
        }
 //       populateData();
    }

    private void populateData() {
        List<TaggingListModel> dataModelList = new ArrayList<>();

        dataModelList.add(new TaggingListModel("104000024", "PROCUREMENT OF TRESTLE - AMD"));
        dataModelList.add(new TaggingListModel("112000025", "NOTE COUNTING MACHINE CRUSADER LIGHT"));
        dataModelList.add(new TaggingListModel("112000104", "CO2 SQUEEZE FIRE EXT- 4.5KG 1 AMD"));
        dataModelList.add(new TaggingListModel("112000962", "HIKVISION IP IR DOME CAMERA"));
        dataModelList.add(new TaggingListModel("112000963", "HIKVISION IP IR DOME CAMERA"));
        dataModelList.add(new TaggingListModel("112000964", "HIKVISION IP IR DOME CAMERA"));
/*
        taggingListAdapter = new TaggingListAdapter(dataModelList, this, this);
        binding.recyclerView.setAdapter(taggingListAdapter);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem action_search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) action_search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                taggingListAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {

                    taggingListAdapter.getFilter().filter(newText);

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
            if(tagModelId !=0){
                setAdapter();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void cardClicked(TagItems f) {
        TAG_CURRENT_VALUE="";
        binding.progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(TaggingListActivity.this, TaggingDetailActivity.class);
        intent.putExtra(ASSET_CODE, "" + f.AssetNumber);
        intent.putExtra(ASSET_NAME, "" + f.AssetName);
        intent.putExtra(TAG_MODEL_ID, f.TAGID);
        intent.putExtra(TAG_DETAIL_ID, f.TAGDetailID);
        startActivity(intent);

    }


}