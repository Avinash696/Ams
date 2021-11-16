package com.ams.amsvistara.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.ActivityAllAssetsDetailBinding;
import com.ams.amsvistara.viewmodel.AllAssetsListViewModel;

import java.util.Objects;

public class  AllAssetsDetail extends AppCompatActivity {
    ActivityAllAssetsDetailBinding binding;
    AllAssetsListViewModel allAssetsListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_assets_detail);

//        allAssetsListViewModel =new ViewModelProvider(this).get(AllAssetsListViewModel.class);
//        binding.setViewModel(allAssetsListViewModel);
//        binding.setLifecycleOwner(this);
//        getIntentData();
        initToolbar();
        Toast.makeText(this, "Works", Toast.LENGTH_SHORT).show();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("AssetsDetail");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
    }

//    void getIntentData() {
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            viewModel.assetsCode.setValue(bundle.getString(ASSET_CODE));
//            viewModel.assetsName.setValue(bundle.getString(ASSET_NAME));
//
//            //temp data set
////            binding.etName.setText(bundle.getString(ASSET_NAME));
////            binding.etCode.setText(bundle.getString(ASSET_CODE));
//        } else {
//            Toast.makeText(getApplicationContext(), "Bundle Null ", Toast.LENGTH_SHORT).show();
//        }
//    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}