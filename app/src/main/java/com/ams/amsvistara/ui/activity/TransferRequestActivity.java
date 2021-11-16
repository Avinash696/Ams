package com.ams.amsvistara.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.ActivityTransferRequestBinding;
import com.ams.amsvistara.viewmodel.TransferReqViewModel;

import java.util.ArrayList;

public class TransferRequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    ActivityTransferRequestBinding binding;
    private String[] cou = new String[]{"asset1", "Asset2", "Asset3"};
    String[] arr = {"2020", "2021", "2019", "2018", "2017"};
    TransferReqViewModel transferReqViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_transfer_request);
       getSupportActionBar().setTitle("Raise New Asset Transfer Request");
       transferReqViewModel = new ViewModelProvider(this).get(TransferReqViewModel.class);
       binding.setLifecycleOwner(this);

        setOnClick();
        setSpinnerAdapter();
    }

    private void setSpinnerAdapter() {
        ArrayAdapter spFromLocation =new ArrayAdapter(this, android.R.layout.simple_list_item_1,arr);
        ArrayAdapter spFromUser =new ArrayAdapter(this, android.R.layout.simple_list_item_1,arr);
        ArrayAdapter spToLocation =new ArrayAdapter(this, android.R.layout.simple_list_item_1,arr);
        ArrayAdapter spUser =new ArrayAdapter(this, android.R.layout.simple_list_item_1,arr);

        //dropdown
        spFromLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFromUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spToLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set adapter
        binding.spFromLocation.setAdapter(spFromLocation);
        binding.spFromUser.setAdapter(spFromUser);
        binding.spToLocation.setAdapter(spToLocation);
        binding.spUser.setAdapter(spUser);

    }
   private void setOnClick(){
            binding.spFromLocation.setOnItemSelectedListener(this);
            binding.spFromUser.setOnItemSelectedListener(this);
            binding.spToLocation.setOnItemSelectedListener(this);
            binding.spUser.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),arr[i] , Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        transferReqViewModel.spFromLocation.setValue(arr[i]);
        transferReqViewModel.spFromUser.setValue(arr[i]);
        transferReqViewModel.spToLocation.setValue(arr[i]);
        transferReqViewModel.spUser.setValue(arr[i]);

    }
}