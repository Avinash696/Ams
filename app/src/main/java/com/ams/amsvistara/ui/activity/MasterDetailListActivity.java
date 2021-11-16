package com.ams.amsvistara.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.ActivityMasterDetailListBinding;
import com.ams.amsvistara.model.MasterListModel;
import com.ams.amsvistara.model.updatedModels.NewAsset;
import com.ams.amsvistara.model.updatedModels.NewAssetResponce;
import com.ams.amsvistara.viewmodel.MasterDetailViewModel;
import com.ams.amsvistara.viewmodel.TransferReqViewModel;
import com.ams.amsvistara.ws.repo.AddNewAssetRepo;
import com.ams.amsvistara.ws.repo.IAddNewAssetRepo;

import java.util.Calendar;
import java.util.Objects;

public class MasterDetailListActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{
    ActivityMasterDetailListBinding binding;
    MasterDetailViewModel masterDetailViewModel;
    String[] country = {"2020", "2021", "2019", "2018", "2017"};
    String[] AdFinanceYear = {"2019-2020","2020-2021"};
    String[] AdCategory = {"Air Conditioner","Computer Equipment","CWIP","Electrical","ITE-Laptop/Desktop","ITE-Printer/Descktop","Other Equipment"};
    String[] AdSubCategory = {"Reader"};
    String[] AdLocation = {"Mumbai","Lipa","Nagpur"};
    String[] AdSubLocation = {"Davo"};
    String[] AdAssetCondition = {"Working","Not Working"};
    String[] SdAssetType = {"Countable","Non Verifiable","Verifiable","Taggable"};
    String[] SdAssetSurface = {"Metalic","Non Metalic"};
    String[] SdTagType = {"Form","Soft","Metal Level","Hard Metal","Metal","Hard","PCB","NA"};
    String[] SdParentChild = {"NA","Printer" ,"Data Pending"};
    String[] OdAssignId = {"Dashrat","user1 ","user2" ,"user3 "};
    String[] OdUtilizationLevel = {"Fully Utilized","Partically Utilized ","Not Utilized"};
    public static String TAG ="kkkk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_master_detail_list);
       setToolbar();
//        getSupportActionBar().setTitle("Asset Master");
        setOnClick();
        setSpinnerAdapter();
        //viewModel
        masterDetailViewModel = new ViewModelProvider(this).get(MasterDetailViewModel.class);
        binding.setLifecycleOwner(this);


        //btn save
//        masterDetailViewModel.setMasterDetailObj().observe(this, new Observer<MasterListModel>() {
//            @Override
//            public void onChanged(MasterListModel masterListModel) {
//                if (TextUtils.isEmpty(Objects.requireNonNull(masterListModel).getThirdPartyAsset())) {
//                    binding.etThirdPartyAsset.setError("Enter TR code");
//                    binding.etThirdPartyAsset.requestFocus();
//                }
//                else  if(TextUtils.isEmpty(Objects.requireNonNull(masterListModel).getFinancialYear())){
////                   activityTransferRequestBinding.spFromLocation.setError("Select From Location");
//                    TextView errorText =(TextView) binding.spAdFinanceYear.getSelectedView();
//                    errorText.setError("anything here, just to add the icon");
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText("Select From Location ");
//                    binding.spAdFinanceYear.requestFocus();
//                }
//                else if(TextUtils.isEmpty(Objects.requireNonNull(masterListModel).getPvStatus())){
//                    binding.etPvStatus.setError("Request Date");
//                    binding.etPvStatus.requestFocus();
//                }
//                else {
//                    AddNewAssetRepo.getInstance().postAsset(,);
//                }
//            }
//        });
        masterDetailViewModel.setMasterDetailObj().observe(this, new Observer<NewAsset>() {
            @Override
            public void onChanged(NewAsset newAsset) {
                AddNewAssetRepo.getInstance().postAsset(newAsset, new IAddNewAssetRepo.AddNewAssetCallback() {
                    @Override
                    public void onDataLoaded(NewAssetResponce responce) {
                        Toast.makeText(MasterDetailListActivity.this, ""+responce.getResponseMessage(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onDataLoaded: ");
                    }
                    @Override
                    public void onDataNotAvailable() {
                        Toast.makeText(MasterDetailListActivity.this, "Unable to hit service", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void setToolbar(){
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Master List");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
    }

    public void   setOnClick() {
        binding.spAdFinanceYear.setOnItemSelectedListener(this);
        binding.spAdCategory.setOnItemSelectedListener(this);
        binding.spAdSubCategory.setOnItemSelectedListener(this);
//        binding.spAdLocation.setOnItemSelectedListener(this);
        binding.spAdSubLocation.setOnItemSelectedListener(this);
        binding.spAdAssetCondition.setOnItemSelectedListener(this);
//        binding.spSdAssetType.setOnItemSelectedListener(this);
//        binding.spSdAssetSurface.setOnItemSelectedListener(this);
//        binding.spSdTagType.setOnItemSelectedListener(this);
//        binding.spSdParentChild.setOnItemSelectedListener(this);
//        binding.spOdAssignId.setOnItemSelectedListener(this);
//        binding.spOdUtilizationLevel.setOnItemSelectedListener(this);
    }

    private void setSpinnerAdapter() {
        ArrayAdapter spAdFinanceYear = new ArrayAdapter(this,R.layout.custom_spinner, AdFinanceYear);
        ArrayAdapter spAdCategory = new ArrayAdapter(this,R.layout.custom_spinner, AdCategory);
        ArrayAdapter spAdSubCategory = new ArrayAdapter(this,R.layout.custom_spinner, AdSubCategory);
        ArrayAdapter spAdLocation = new ArrayAdapter(this,R.layout.custom_spinner, AdLocation);
        ArrayAdapter spAdSubLocation = new ArrayAdapter(this,R.layout.custom_spinner, AdSubLocation);
        ArrayAdapter spAdAssetCondition = new ArrayAdapter(this, R.layout.custom_spinner, AdAssetCondition);
        ArrayAdapter spSdAssetType = new ArrayAdapter(this,R.layout.custom_spinner, SdAssetType);
        ArrayAdapter spSdAssetSurface = new ArrayAdapter(this,R.layout.custom_spinner, SdAssetSurface);
        ArrayAdapter spSdTagType = new ArrayAdapter(this,R.layout.custom_spinner, SdTagType);
        ArrayAdapter spSdParentChild = new ArrayAdapter(this,R.layout.custom_spinner, SdParentChild);
        ArrayAdapter spOdAssignId = new ArrayAdapter(this,R.layout.custom_spinner, OdAssignId);
        ArrayAdapter spOdUtilizationLevel = new ArrayAdapter(this, R.layout.custom_spinner, OdUtilizationLevel);

        //setDropdown
        spAdFinanceYear.setDropDownViewResource(R.layout.custom_spinner);
        spAdCategory.setDropDownViewResource(R.layout.custom_spinner);
        spAdSubCategory.setDropDownViewResource(R.layout.custom_spinner);
        spAdLocation.setDropDownViewResource(R.layout.custom_spinner);
        spAdSubLocation.setDropDownViewResource(R.layout.custom_spinner);
        spAdAssetCondition.setDropDownViewResource(R.layout.custom_spinner);
        spSdAssetType.setDropDownViewResource(R.layout.custom_spinner);
        spSdAssetSurface.setDropDownViewResource(R.layout.custom_spinner);
        spSdTagType.setDropDownViewResource(R.layout.custom_spinner);
        spSdParentChild.setDropDownViewResource(R.layout.custom_spinner);
        spOdAssignId.setDropDownViewResource(R.layout.custom_spinner);
        spOdUtilizationLevel.setDropDownViewResource(R.layout.custom_spinner);

        //Setting the ArrayAdapter data on the Spinner

        binding.spAdFinanceYear.setAdapter(spAdFinanceYear);
        binding.spAdCategory.setAdapter(spAdCategory);
        binding.spAdSubCategory.setAdapter(spAdSubCategory);
//        binding.spAdLocation.setAdapter(spAdLocation);
        binding.spAdSubLocation.setAdapter(spAdSubLocation);
        binding.spAdAssetCondition.setAdapter(spAdAssetCondition);
//        binding.spSdAssetType.setAdapter(spSdAssetType);
//        binding.spSdAssetSurface.setAdapter(spSdAssetSurface);
//        binding.spSdTagType.setAdapter(spSdTagType);
//        binding.spSdParentChild.setAdapter(spSdParentChild);
//        binding.spOdAssignId.setAdapter(spOdAssignId);
//        binding.spOdUtilizationLevel.setAdapter(spOdUtilizationLevel);
    }


    public void FnCaptalizeDate(View view) {
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        Integer year = calendar.get(Calendar.YEAR);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                activityTransferRequestBinding.etRequestDate.setText(dayOfMonth+"/"+month+"/"+year);
                //condition
                masterDetailViewModel.CapDate.setValue(dayOfMonth + "/" + month + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    public void FnAcquistionDate(View view) {
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        Integer year = calendar.get(Calendar.YEAR);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                activityTransferRequestBinding.etRequestDate.setText(dayOfMonth+"/"+month+"/"+year);
                //condition
                masterDetailViewModel.AcquisitionDate.setValue(dayOfMonth + "/" + month + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    public void FnDepreciatedStartDate(View view) {
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        Integer year = calendar.get(Calendar.YEAR);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                activityTransferRequestBinding.etRequestDate.setText(dayOfMonth+"/"+month+"/"+year);
                //condition
                masterDetailViewModel.AcquisitionDate.setValue(dayOfMonth + "/" + month + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    public void FnDecapDate(View view) {
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        Integer year = calendar.get(Calendar.YEAR);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                activityTransferRequestBinding.etRequestDate.setText(dayOfMonth+"/"+month+"/"+year);
                //condition
                masterDetailViewModel.DecapDate.setValue(dayOfMonth + "/" + month + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}