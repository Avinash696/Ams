package com.ams.amsvistara.ui.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.AllAssetsListAdapter;
import com.ams.amsvistara.databinding.ActivityAllAssetsListBinding;
import com.ams.amsvistara.databinding.ActivityDisposalRequestBinding;
import com.ams.amsvistara.databinding.ActivityTicketDetailBinding;
import com.ams.amsvistara.databinding.ActivityTransferRequestBinding;
import com.ams.amsvistara.model.AssetMasterDemoModel;
import com.ams.amsvistara.model.DisposeReqModel;
import com.ams.amsvistara.model.TicketListModel;
import com.ams.amsvistara.model.TransferReqModel;

import com.ams.amsvistara.model.updatedModels.AssetMaster;
import com.ams.amsvistara.model.updatedModels.NewAssetResponce;
import com.ams.amsvistara.model.updatedModels.NewDisposalModel;
import com.ams.amsvistara.model.updatedModels.NewTransferReq;
import com.ams.amsvistara.model.updatedModels.RestApiModel;
import com.ams.amsvistara.viewmodel.AllAssetsListViewModel;
import com.ams.amsvistara.viewmodel.DisposeReqViewModel;
import com.ams.amsvistara.viewmodel.TicketViewModel;
import com.ams.amsvistara.viewmodel.TransferReqViewModel;
import com.ams.amsvistara.ws.repo.TransferReqRepo;
import com.ams.amsvistara.ws.retrofit.RetrofitHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllAssetsList extends AppCompatActivity {
    public static String TAG = "rawat";
    boolean isSelectMode = false ;
    ArrayList<AssetMaster> selectedItems = new ArrayList<>();
    ActivityAllAssetsListBinding binding;
    AllAssetsListAdapter allAssetsListAdapter;
    AllAssetsListViewModel viewModel;
    private String[] cou = {"Dashrat","user1", "user2", "user3"};
    private String[] fromLocation = {"Mumbai", "Nagpur", "Lipa"};
    //dispose data
    String[] disposeTypeArr = {"SCRAPPED", "SOLDOFF"};
    String[] disposeLocationArr = {"Mumbai", "Nagpur", "Lipa"};
    String[] disposeUserArr = {"222", "202", "201", "208", "207"};
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AllAssetsListViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_assets_list);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        initToolbar();
//        getAssetsList();
        setAdapter();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllAssetsList.this, "Add Asset!!", Toast.LENGTH_SHORT).show();
                try {
                    Intent intent = new Intent(AllAssetsList.this, MasterDetailListActivity.class);
                    startActivity(intent);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.simple_spinner_item, getResources().getStringArray(R.array.actionMenu));
//        binding.spinner.setPrompt(getResources().getString(R.string.action_str));
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                redirectToLayout(i);
                binding.spinner.setSelection(0);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void dialogTransferReq() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setTitle("Transfer Request");
        ActivityTransferRequestBinding activityTransferRequestBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this), R.layout.activity_transfer_request, (ViewGroup) binding.getRoot(), false);
        dialog.setContentView(activityTransferRequestBinding.getRoot());
        activityTransferRequestBinding.setLifecycleOwner(this);
        TransferReqViewModel transferReqViewModel = new ViewModelProvider(this).get(TransferReqViewModel.class);

        ArrayAdapter spFromLocation = new ArrayAdapter(this, R.layout.custom_spinner, fromLocation);
        ArrayAdapter spFromUser = new ArrayAdapter(this, R.layout.custom_spinner, cou);
        ArrayAdapter spToLocation = new ArrayAdapter(this, R.layout.custom_spinner, fromLocation);
        ArrayAdapter spUser = new ArrayAdapter(this, R.layout.custom_spinner, cou);
        //dropdown
        spFromLocation.setDropDownViewResource(R.layout.custom_spinner);
        spFromUser.setDropDownViewResource(R.layout.custom_spinner);
        spToLocation.setDropDownViewResource(R.layout.custom_spinner);
        spUser.setDropDownViewResource(R.layout.custom_spinner);

        //set adapter
        activityTransferRequestBinding.spFromLocation.setAdapter(spFromLocation);
        activityTransferRequestBinding.spFromUser.setAdapter(spFromUser);
        activityTransferRequestBinding.spToLocation.setAdapter(spToLocation);
        activityTransferRequestBinding.spUser.setAdapter(spUser);

        activityTransferRequestBinding.spFromLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                transferReqViewModel.spFromLocation.setValue(cou[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        activityTransferRequestBinding.spFromUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                transferReqViewModel.spFromUser.setValue(cou[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        activityTransferRequestBinding.spToLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                transferReqViewModel.spToLocation.setValue(cou[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        activityTransferRequestBinding.spUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                transferReqViewModel.spUser.setValue(cou[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//viewmodel set error show in field
        activityTransferRequestBinding.setViewModel(transferReqViewModel);

//        transferReqViewModel.responceMutableLiveData.observe(this, new Observer<NewAssetResponce>() {
//            @Override
//            public void onChanged(NewAssetResponce responce) {
//                Toast.makeText(AllAssetsList.this, ""+responce.getResponseMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        transferReqViewModel.SetTransferReqObj().observe(this, new Observer<NewTransferReq>() {
            @Override
            public void onChanged(NewTransferReq transferReqModel) {
//                if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTrId())) {
//                    activityTransferRequestBinding.etTrId.setError("Enter TR Id");
//                    activityTransferRequestBinding.etTrId.requestFocus();
//                }
                if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTRCode())) {
                    activityTransferRequestBinding.etTrCode.setError("Enter TR code");
                    activityTransferRequestBinding.etTrCode.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getFromLocation())) {
//                   activityTransferRequestBinding.spFromLocation.setError("Select From Location");
                    TextView errorText = (TextView) activityTransferRequestBinding.spFromLocation.getSelectedView();
                    errorText.setError("anything here, just to add the icon");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("Select From Location ");
                    activityTransferRequestBinding.spFromLocation.requestFocus();
                }
//                else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getToLocation())) {
////                   activityTransferRequestBinding.spFromLocation.setError("Select From Location");
//                    TextView errorText = (TextView) activityTransferRequestBinding.spToLocation.getSelectedView();
//                    errorText.setError("anything here, just to add the icon");
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText("Select From Location ");
//                    activityTransferRequestBinding.spFromLocation.requestFocus();
//                }
                else
                    if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTRUser())) {
                    TextView errorText = (TextView) activityTransferRequestBinding.spFromUser.getSelectedView();
                    errorText.setError("anything here, just to add the icon");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("Select From User ");
                    activityTransferRequestBinding.spFromUser.requestFocus();
                }
//                   else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getToLocation())) {
//                        TextView errorText = (TextView) activityTransferRequestBinding.spToLocation.getSelectedView();
//                        errorText.setError("anything here, just to add the icon");
//                        errorText.setTextColor(Color.RED);
//                        errorText.setText("Select To Location ");
//                        activityTransferRequestBinding.spToLocation.requestFocus();
//                    }
                   else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTRUser())) {
                        TextView errorText = (TextView) activityTransferRequestBinding.spUser.getSelectedView();
                        errorText.setError("anything here, just to add the icon");
                        errorText.setTextColor(Color.RED);
                        errorText.setText("Select User ");
                        activityTransferRequestBinding.spUser.requestFocus();
                    } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTRRemarks())) {
                        activityTransferRequestBinding.etRemarks.setError("Enter Remark");
                        activityTransferRequestBinding.etRemarks.requestFocus();
                    } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getRequestDate())) {
                        activityTransferRequestBinding.etRequestDate.setError("Select Request Date");
                        activityTransferRequestBinding.etRequestDate.requestFocus();
                    } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTransferDate())) {
                        activityTransferRequestBinding.etTransferDate.setError("Select Transfer Date");
                        activityTransferRequestBinding.etTransferDate.requestFocus();
                    } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTRStatus())) {
                        activityTransferRequestBinding.etStatus.setError("Enter Status");
                        activityTransferRequestBinding.etStatus.requestFocus();
                    } else {


                        RetrofitHelper.getClient().postTransferReq(transferReqModel).enqueue(new Callback<NewAssetResponce>() {
                            @Override
                            public void onResponse(Call<NewAssetResponce> call, Response<NewAssetResponce> response) {
                                Log.d(TAG, "onResponse:  " + response);
                                if (response.code() == 200) {
                                    Log.d(TAG, "onResponse: "+response.body().getResponseMessage());
                                    Log.d(TAG, "onResponse: "+response.body().getErrorMessage());
                                    dialog.dismiss();
                                    Toast.makeText(AllAssetsList.this, ""+response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<NewAssetResponce> call, Throwable t) {
                                Log.d(TAG, "onFailure: ");
                            }
                        });
                    }
            }
        });
//        transferReqViewModel.SetTransferReqObj().observe(this, new Observer<TransferReqModel>() {
//            @Override
//            public void onChanged(TransferReqModel transferReqModel) {
//                if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTrId())) {
//                    activityTransferRequestBinding.etTrId.setError("Enter TR Id");
//                    activityTransferRequestBinding.etTrId.requestFocus();
//                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTrCode())) {
//                    activityTransferRequestBinding.etTrCode.setError("Enter TR code");
//                    activityTransferRequestBinding.etTrCode.requestFocus();
//                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getSpFromLocation())) {
////                   activityTransferRequestBinding.spFromLocation.setError("Select From Location");
//                    TextView errorText = (TextView) activityTransferRequestBinding.spFromLocation.getSelectedView();
//                    errorText.setError("anything here, just to add the icon");
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText("Select From Location ");
//                    activityTransferRequestBinding.spFromLocation.requestFocus();
//                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getSpFromUser())) {
//                    TextView errorText = (TextView) activityTransferRequestBinding.spFromUser.getSelectedView();
//                    errorText.setError("anything here, just to add the icon");
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText("Select From User ");
//                    activityTransferRequestBinding.spFromUser.requestFocus();
//                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getSpToLocation())) {
//                    TextView errorText = (TextView) activityTransferRequestBinding.spToLocation.getSelectedView();
//                    errorText.setError("anything here, just to add the icon");
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText("Select To Location ");
//                    activityTransferRequestBinding.spToLocation.requestFocus();
//                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getSpUser())) {
//                    TextView errorText = (TextView) activityTransferRequestBinding.spUser.getSelectedView();
//                    errorText.setError("anything here, just to add the icon");
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText("Select User ");
//                    activityTransferRequestBinding.spUser.requestFocus();
//                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getRemark())) {
//                    activityTransferRequestBinding.etRemarks.setError("Enter Remark");
//                    activityTransferRequestBinding.etRemarks.requestFocus();
//                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getRequestDate())) {
//                    activityTransferRequestBinding.etRequestDate.setError("Select Request Date");
//                    activityTransferRequestBinding.etRequestDate.requestFocus();
//                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getTransferDate())) {
//                    activityTransferRequestBinding.etTransferDate.setError("Select Transfer Date");
//                    activityTransferRequestBinding.etTransferDate.requestFocus();
//                } else if (TextUtils.isEmpty(Objects.requireNonNull(transferReqModel).getStatus())) {
//                    activityTransferRequestBinding.etStatus.setError("Enter Status");
//                    activityTransferRequestBinding.etStatus.requestFocus();
//                } else {
//                    dialog.dismiss();
//                }
//            }
//        });


        //setting date picker
        activityTransferRequestBinding.rlReqDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTransferReqDate(transferReqViewModel);
            }
        });
        activityTransferRequestBinding.rlTransferDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTransferDate(transferReqViewModel);
            }
        });
        dialog.show();
    }

    private void dialogDisposeReq() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setTitle("Dispose Request");
        ActivityDisposalRequestBinding activityDisposalRequestBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.activity_disposal_request, (ViewGroup) binding.getRoot(), false);
        dialog.setContentView(activityDisposalRequestBinding.getRoot());
        activityDisposalRequestBinding.setLifecycleOwner(this);
        //view model
        DisposeReqViewModel disposeReqViewModel = new ViewModelProvider(this).get(DisposeReqViewModel.class);
        activityDisposalRequestBinding.setViewModel(disposeReqViewModel);
        //error check
        disposeReqViewModel.getDisposeReq().observe(this, new Observer<NewDisposalModel>() {
            @Override
            public void onChanged(NewDisposalModel disposeReqModel) {
//                if (TextUtils.isEmpty(Objects.requireNonNull(disposeReqModel).DRUserID)) {
//                    activityDisposalRequestBinding.etDrId.setError("Enter DR Id");
//                    activityDisposalRequestBinding.etDrId.requestFocus();
//                } else
                    if (TextUtils.isEmpty(Objects.requireNonNull(disposeReqModel).DRCode)) {
                    activityDisposalRequestBinding.drCode.setError("Enter DR Code");
                    activityDisposalRequestBinding.drCode.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(disposeReqModel).DisposalType)) {
                    TextView errorText = (TextView) activityDisposalRequestBinding.spDisposalType.getSelectedView();
                    errorText.setError("anything here, just to add the icon");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("Select Dispose Type ");
                    activityDisposalRequestBinding.spDisposalType.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(disposeReqModel).getFromLocation())) {
                    TextView errorText = (TextView) activityDisposalRequestBinding.spLocation.getSelectedView();
                    errorText.setError("anything here, just to add the icon");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("Select To Location ");
                    activityDisposalRequestBinding.spLocation.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(disposeReqModel).DRUser)) {
                    TextView errorText = (TextView) activityDisposalRequestBinding.spUser.getSelectedView();
                    errorText.setError("anything here, just to add the icon");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("Select User");
                    activityDisposalRequestBinding.spUser.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(disposeReqModel).DRRemarks)) {
                    activityDisposalRequestBinding.etRemarks.setError("Remarks");
                    activityDisposalRequestBinding.etRemarks.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(disposeReqModel).getRequestDate())) {
                    activityDisposalRequestBinding.etRequestDate.setError("Request Date");
                    activityDisposalRequestBinding.etRequestDate.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(disposeReqModel).DisposalDate)) {
                    activityDisposalRequestBinding.etDisposeDate.setError("Dispose Date");
                    activityDisposalRequestBinding.etDisposeDate.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(disposeReqModel).DRStatus)) {
                    activityDisposalRequestBinding.etStatus.setError("status");
                    activityDisposalRequestBinding.etStatus.requestFocus();
                } else {
                    RetrofitHelper.getClient().postDisposeReq(disposeReqModel).enqueue(new Callback<NewAssetResponce>() {
                        @Override
                        public void onResponse(Call<NewAssetResponce> call, Response<NewAssetResponce> response) {
                            Log.d(TAG, "onResponse: "+response.body().getResponseMessage());
                            Log.d(TAG, "onResponse: "+response.body().getErrorMessage());
                            if(response.code() == 200){
                                dialog.dismiss();
                                Toast.makeText(AllAssetsList.this, ""+response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<NewAssetResponce> call, Throwable t) {
                            Log.d(TAG, "onFailure: ");
                        }
                    });

                }
            }
        });

        //set adapter
        ArrayAdapter disposalType = new ArrayAdapter(this, R.layout.custom_spinner, disposeTypeArr);
        ArrayAdapter location = new ArrayAdapter(this, R.layout.custom_spinner, disposeLocationArr);
        ArrayAdapter user = new ArrayAdapter(this, R.layout.custom_spinner, disposeUserArr);

        disposalType.setDropDownViewResource(R.layout.custom_spinner);
        location.setDropDownViewResource(R.layout.custom_spinner);
        user.setDropDownViewResource(R.layout.custom_spinner);

        activityDisposalRequestBinding.spDisposalType.setAdapter(disposalType);
        activityDisposalRequestBinding.spLocation.setAdapter(location);
        activityDisposalRequestBinding.spUser.setAdapter(user);

        //spinner selected value set in view Model
        activityDisposalRequestBinding.spDisposalType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disposeReqViewModel.disposalType.setValue(disposeTypeArr[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        activityDisposalRequestBinding.spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disposeReqViewModel.location.setValue(disposeLocationArr[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        activityDisposalRequestBinding.spUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                disposeReqViewModel.user.setValue(disposeUserArr[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //setting date
        activityDisposalRequestBinding.rlDisposeReqDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDisposeReqDate(disposeReqViewModel);
            }
        });
        activityDisposalRequestBinding.rlDisposeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDisposeDate(disposeReqViewModel);
            }
        });

        dialog.show();
    }

    private void dialogTicket() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
//        dialog.setTitle("Ticket");
        ActivityTicketDetailBinding activityTicketDetailBinding = DataBindingUtil.inflate(
                LayoutInflater.from(this), R.layout.activity_ticket_detail, (ViewGroup) binding.getRoot(), false);
        dialog.setContentView(activityTicketDetailBinding.getRoot());
        activityTicketDetailBinding.setLifecycleOwner(this);

        //ticket view Model set
        TicketViewModel ticketViewModel;
        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        activityTicketDetailBinding.setViewModel(ticketViewModel);
        ticketViewModel.SaveTicket().observe(this, new Observer<TicketListModel>() {
            @Override
            public void onChanged(TicketListModel ticketListModel) {
                //ticket id
                if (TextUtils.isEmpty(Objects.requireNonNull(ticketListModel).getStatus())) {
                    activityTicketDetailBinding.etTicketId.setError("Enter Ticket Id");
                    activityTicketDetailBinding.etTicketId.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(ticketListModel).getAsset())) {
                    activityTicketDetailBinding.etAsset.setError("Enter Assets");
                    activityTicketDetailBinding.etAsset.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(ticketListModel).getAssignTo())) {
                    activityTicketDetailBinding.etAssignTo.setError("Assign To");
                    activityTicketDetailBinding.etAssignTo.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(ticketListModel).getTitle())) {
                    activityTicketDetailBinding.etTitle.setError("Title");
                    activityTicketDetailBinding.etTitle.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(ticketListModel).getDescription())) {
                    activityTicketDetailBinding.etDescription.setError("Enter Ticket Id");
                    activityTicketDetailBinding.etDescription.requestFocus();
                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void setTransferReqDate(TransferReqViewModel transferReqViewModel) {
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        Integer year = calendar.get(Calendar.YEAR);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                activityTransferRequestBinding.etRequestDate.setText(dayOfMonth+"/"+month+"/"+year);
                //condition
                transferReqViewModel.requestDate.setValue(month + "/" + dayOfMonth+ "/" + year +" "+"11:01:01 AM");
                transferReqViewModel.requestDate.setValue(month + "/" + dayOfMonth + "/" + year +" "+"11:01:01 AM");

            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void setTransferDate(TransferReqViewModel transferReqViewModel) {
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        Integer year = calendar.get(Calendar.YEAR);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                activityTransferRequestBinding.etRequestDate.setText(dayOfMonth+"/"+month+"/"+year);
                //condition
                transferReqViewModel.transferDate.setValue(month + "/" + dayOfMonth + "/" + year +" "+"11:01:01 AM");
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void setDisposeReqDate(DisposeReqViewModel disposeReqViewModel) {
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        Integer year = calendar.get(Calendar.YEAR);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                activityTransferRequestBinding.etRequestDate.setText(dayOfMonth+"/"+month+"/"+year);
                //condition
                disposeReqViewModel.requestDate.setValue(month + "/" + dayOfMonth + "/" + year +" "+"11:01:01 AM");
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void setDisposeDate(DisposeReqViewModel disposeReqViewModel) {
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        Integer year = calendar.get(Calendar.YEAR);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                activityTransferRequestBinding.etRequestDate.setText(dayOfMonth+"/"+month+"/"+year);
                //condition
                disposeReqViewModel.disposeDate.setValue(month + "/" + dayOfMonth + "/" + year +" "+"11:01:01 AM");
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void redirectToLayout(int i) {

        switch (i) {
            case 1:
//                ShowDialog(R.layout.activity_transfer_request);
                dialogTransferReq();
                break;
            case 2:
                dialogDisposeReq();
//                ShowDialog(R.layout.activity_disposal_request);
                break;
            case 3:
                dialogTicket();
//                ShowDialog(R.layout.activity_ticket_detail);
                break;
            default:
//                binding.spinner.setSelection(0);

        }
    }

    public void getAssetsList() {
        List<AssetMaster> assetMasters = new ArrayList<>();
        Log.d(TAG, "getAssetsList: called");
        binding.progressBar.setVisibility(View.VISIBLE);
        RetrofitHelper.getClient().getAllAssets().enqueue(new Callback<RestApiModel>() {
            @Override
            public void onResponse(Call<RestApiModel> call, Response<RestApiModel> response) {
                viewModel.restApiModelMutableLiveData.setValue(response.body().AssetMasters);
                allAssetsListAdapter = new AllAssetsListAdapter(response.body().AssetMasters);
                binding.recyclerView.setAdapter(allAssetsListAdapter);
                allAssetsListAdapter.notifyDataSetChanged();
                binding.progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<RestApiModel> call, Throwable t) {
                Log.d(TAG, "onFailure: Unable to hit " + t.getMessage());
                binding.progressBar.setVisibility(View.GONE);
            }
        });
//        RetrofitHelper.getClient().getAllAssets().enqueue(new Callback<List<updated>>() {
//            @Override
//            public void onResponse(Call<List<updated>> call, Response<List<updated>> response) {
//                Log.d(TAG, "onResponse: "+response.body());
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<updated>> call, Throwable t) {
//                Log.d(TAG, "onFailure: ");
//            }
//        });

    }

    private void populateData() {
        getAssetsList();
//        Log.d("rawat", "populateData: " + viewModel.masterMutableLiveData.getValue());
//        viewModel.masterMutableLiveData.getValue().AssetName
//        AssetMaster assetMasterList = viewModel.masterMutableLiveData;
//        allAssetsListAdapter = new AllAssetsListAdapter(assetMasterList,this);

//        List<AssetMasterDemoModel> dataModelList =new ArrayList<>();
//          dataModelList.add(new AssetMasterDemoModel("104000024", "PROCUREMENT OF TRESTLE - AMD"));
//        dataModelList.add(new AllAssetsListModel("112000025", "NOTE COUNTING MACHINE CRUSADER LIGHT", false));
//        dataModelList.add(new AllAssetsListModel("112000104", "CO2 SQUEEZE FIRE EXT- 4.5KG 1 AMD", false));
//        dataModelList.add(new AllAssetsListModel("112000962", "HIKVISION IP IR DOME CAMERA", false));
//        dataModelList.add(new AllAssetsListModel("112000963", "HIKVISION IP IR DOME CAMERA", false));
//        dataModelList.add(new AllAssetsListModel("112000964", "HIKVISION IP IR DOME CAMERA", false));
//        viewModel.listMutableLiveDataTagList.setValue(dataModelList);
//        viewModel.masterMutableLiveData.setValue(dataModelList);
//        allAssetsListAdapter = new AllAssetsListAdapter(viewModel.listMutableLiveDataTagList.getValue(), this);
//
//        allAssetsListAdapter = new AllAssetsListAdapter(dataModelList,this);
//        binding.recyclerView.setAdapter(allAssetsListAdapter);
//        allAssetsListAdapter.notifyDataSetChanged();
    }

    private void setAdapter() {
//        Log.d("rawat", "setAdapter: 2 ");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        populateData();
        binding.recyclerView.addOnItemTouchListener(new AllAssetsList.RecyclerTouchListener(this,
                binding.recyclerView, new TestActivity.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
//                viewModel.AssetName.setValue(viewModel.listMutableLiveDataTagList.getValue().get(position).tagName);
//                viewModel.AssetCode.setValue(viewModel.listMutableLiveDataTagList.getValue().get(position).tagCode);
//                Toast.makeText(AllAssetsList.this, "Single Click on position :" + viewModel.restApiModelMutableLiveData.getValue().AssetCode,
//                        Toast.LENGTH_SHORT).show();
//                Log.d("rawatPos", "onClick: "+viewModel.AssetName+"/"+viewModel.AssetCode);
                Log.d("masterListData", "onClick: "+position);
//                int value = position;
//                Intent i = new Intent(AllAssetsList.this, MasterListActivity.class);
//                i.putExtra("positionKey",value);
//                startActivity(i);

                String value = String.valueOf(position);
                Intent i = new Intent(AllAssetsList.this, MasterListActivity.class);
                Log.d("rawat", "SendData: "+value);
                i.putExtra("key", value);
                startActivity(i);





//                Intent intent = new Intent();
//                intent.putExtra("position",position);
////                startActivity(intent,MasterListActivity.class);
//                startActivity(new Intent(AllAssetsList.this, MasterListActivity.class));
//                if (viewModel.listMutableLiveDataTagList.getValue().get(position).selectFlag) {
//                    viewModel.listMutableLiveDataTagList.getValue().get(position).selectFlag = false;
//                    allAssetsListAdapter.notifyDataSetChanged();
//                } else {
//                    Intent intent = new Intent(view.getContext(), AllAssetsDetail.class);
////                    intent.putExtra(ASSET_CODE, "" + viewModel.AssetCode);
////                    intent.putExtra(ASSET_NAME, "" + viewModel.AssetName);
//                    AllAssetsList.this.startActivity(intent);
//                }
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(AllAssetsList.this, "Long press on position"+position,Toast.LENGTH_SHORT).show();
                isSelectMode =true;
                List<AssetMaster> data = viewModel.restApiModelMutableLiveData.getValue();
                if(selectedItems.contains(data.get(position))){
                    view.setBackgroundColor(Color.TRANSPARENT);
                    selectedItems.remove(data.get(position));
                }
                else{
                    view.setBackgroundColor(Color.RED);
                    selectedItems.add(data.get(position));
                }
                if(selectedItems.size() == 0){
                    isSelectMode =false;
                }
//                return true;
//                Toast.makeText(AllAssetsList.this, "Long press on position :" + viewModel.listMutableLiveDataTagList.getValue().get(position).tagCode,
//                        Toast.LENGTH_LONG).show();
//                if (viewModel.listMutableLiveDataTagList.getValue().get(position).selectFlag) {
//
//                } else {
//                    viewModel.listMutableLiveDataTagList.getValue().get(position).selectFlag = true;
//                    allAssetsListAdapter.notifyDataSetChanged();
//                }
                 
            }
        }));
    }
/*
    @Override
    public void cardClicked(AllAssetsListModel f) {
        //not working has set through adapter
        Log.d("rawat", "cardClicked: ");
        Intent intent = new Intent(AllAssetsList.this, AllAssetsDetail.class);
        intent.putExtra(ASSET_CODE,""+f.tagCode);
        intent.putExtra(ASSET_NAME,""+f.tagName);
        startActivity(intent);
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void FnBarCodeScaner(View view) {
        startActivity(new Intent(AllAssetsList.this, BarcodeScannerActivity.class));
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private TestActivity.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final TestActivity.ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}