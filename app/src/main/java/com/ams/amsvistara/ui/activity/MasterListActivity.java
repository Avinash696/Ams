package com.ams.amsvistara.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.TicketListAdapter;
import com.ams.amsvistara.databinding.ActivityMasterListBinding;
import com.ams.amsvistara.interfaces.TicketClickListener;
import com.ams.amsvistara.model.MasterListModel;
import com.ams.amsvistara.model.TicketListModel;
import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.RestApiModel;
import com.ams.amsvistara.viewmodel.MasterDetailViewModel;
import com.ams.amsvistara.ws.retrofit.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterListActivity extends AppCompatActivity implements TicketClickListener {
    TicketListAdapter ticketListAdapter;

    ActivityMasterListBinding binding;
    MasterDetailViewModel masterDetailViewModel;
    public static String TAG = "masterListData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_master_list);
        masterDetailViewModel = new ViewModelProvider(this).get(MasterDetailViewModel.class);
        binding.setViewModel(masterDetailViewModel);
        setToolbar();
        populateData();

    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Master List");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
    }

    private void populateData() {
//        Log.d("rawat", "populateData: ");
//        RetrofitHelper.getClient().getAssetMaster().enqueue(new Callback<RestApiModel>() {
//            @Override
//            public void onResponse(Call<RestApiModel> call, Response<RestApiModel> response) {
//                Log.d("mastertt", "onResponse: "+response.body().AssetMasters.get(0));
//                AssetMaster master = response.body().AssetMasters.get(0);                                    //click listner
//                masterDetailViewModel.assetMasterMutableLiveData.setValue(master);
//            }
//
//            @Override
//            public void onFailure(Call<RestApiModel> call, Throwable t) {
//
//            }
//        });
//        String value="";
//        Intent intent = getIntent();
////        Integer positionId = intent.getStringExtra("positionKey");
//        String positionId = intent.getStringExtra("positionKey");
////        String positionId = getIntent().getStringExtra("positionKey");
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//             value = extras.getString("positionKey");
//            //The key argument here must match that used in the other activity
//            Log.d(TAG, "value populating data: "+value);
//        }
        String value = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             value = extras.getString("key");
            //The key argument here must match that used in the other activity
            Log.d("rawat", "onCreate: "+value);
        }

//        Log.d(TAG, "populateData: "+positionId+"/"+value);

        Integer finalValue = Integer.parseInt(value);
        RetrofitHelper.getClient().getAllAssets().enqueue(new Callback<RestApiModel>() {
            @Override
            public void onResponse(Call<RestApiModel> call, Response<RestApiModel> response) {
                binding.progressBar.setVisibility(View.VISIBLE);
                Log.d("mastertt", "onResponse: " + response.body().AssetMasters.get(finalValue).AssetName);
                AssetMaster master = response.body().AssetMasters.get(finalValue);                                    //click listner
                masterDetailViewModel.assetMasterMutableLiveData.setValue(master);
                updateListMaster(master);
                binding.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<RestApiModel> call, Throwable t) {
                Toast.makeText(MasterListActivity.this, "service issue ", Toast.LENGTH_SHORT).show();
            }
        });
//        List<MasterListModel> dataModelList = new ArrayList<>();

//        dataModelList.add(new MasterListModel("2021","Gurgaun","Tia","Desktop",
//                "2",""));
        //call adapter
//        ticketListAdapter = new TicketListAdapter(dataModelList, this, this);
//        binding.rvMasterList.setAdapter(ticketListAdapter);
//        ticketListAdapter.notifyDataSetChanged();
    }

//    private void setAdapter() {
//        Log.d("rawat", "setAdapter: ");
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        binding.rvMasterList.setLayoutManager(linearLayoutManager);
//        populateData();
//    }

    @Override
    public void cardClicked(TicketListModel f) {

    }

    public void FnAddAseetsToDetail(View view) {
        startActivity(new Intent(this, MasterDetailListActivity.class));
    }
   public void  updateListMaster(AssetMaster master){
        binding.spAdFinanceYear.setText(master.FinancialYear);
        binding.location.setText(master.LocationName);
        binding.sublocationName.setText(master.SubLocationName);
        binding.categoryName.setText(master.AssetCategoryName);
        binding.subCategoryName.setText(master.AssetSubcategoryName);
        binding.locationCodeErp.setText(master.LocationCodeERP);
        binding.locationNameERp.setText(master.LocationNameERP);
        binding.subLocationCodeErp.setText(master.SubLocationCodeERP);
        binding.sublocationName.setText(master.SubLocationNameERP);
        binding.department.setText(master.Department);
        binding.custodian.setText(master.Custodian);
        binding.assetCode.setText(master.AssetCode);
        binding.serialNo.setText(master.SerialNumber);
        binding.assetNumber.setText(master.AssetNumber);
        binding.assetNumberOld.setText(master.AssetNumberOld);
        binding.assetName.setText(master.AssetName);
        binding.make.setText(master.AssetMake);
        binding.model.setText(master.AssetModel);
        binding.assetDescription.setText(master.AssetDescription);
        binding.depricationKey.setText(master.DepreciationKey);
        binding.plant.setText(master.Plant);
        binding.vender.setText(master.Vendor);
        binding.evaluationGroup.setText(master.EvaluationGroup);
        binding.bussinessArea.setText(master.BusinessArea);
        binding.tagNo.setText(master.SanitizationTempTagNo);
        binding.tagType.setText(master.SanitizationTagTypeName);
        binding.spocName.setText(master.SpocName);
        binding.remarkOne.setText(master.Remarks1);
        binding.remarktwo.setText(master.Remarks2);
        binding.spOdAssignId.setText(master.AssignId);
        binding.thirdPartyAssetValue.setText(master.ThirdPartyAsset);
        binding.spOdUtilizationLevel.setText(master.UtilizationType);


    }
}