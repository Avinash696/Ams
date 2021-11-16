package com.ams.amsvistara.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ams.amsvistara.R;
import com.ams.amsvistara.adapter.TicketListAdapter;
import com.ams.amsvistara.databinding.ActivityTicketListBinding;
import com.ams.amsvistara.interfaces.TicketClickListener;
import com.ams.amsvistara.model.TicketListModel;

import java.util.ArrayList;
import java.util.List;

public class TicketListActivity extends AppCompatActivity implements TicketClickListener {
    ActivityTicketListBinding binding;
    TicketListAdapter ticketListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ticket_list);
        getSupportActionBar().setTitle("Ticket List ");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ticket_list);
        setAdapter();

    }

    private void populateData() {
        Log.d("rawat", "populateData: ");
        List<TicketListModel> dataModelList = new ArrayList<>();

        dataModelList.add(new TicketListModel("Assets", "AsignTo", "Status", "Title", "Image", "Description"));
        dataModelList.add(new TicketListModel("Assets", "AsignTo", "Status", "Title", "Image", "Descrption"));
        dataModelList.add(new TicketListModel("Assets", "AsignTo", "Status", "Title", "Image", "Descroption"));
        dataModelList.add(new TicketListModel("Assets", "AsignTo", "Status", "Title", "Image", "Descption"));
        dataModelList.add(new TicketListModel("Assets", "AsignTo", "Status", "Title", "Image", "Descrtion"));
        dataModelList.add(new TicketListModel("Assets", "AsignTo", "Status", "Title", "Image", "Deson"));

        //call adapter
        ticketListAdapter = new TicketListAdapter(dataModelList, this, this);
        binding.rvTicketList.setAdapter(ticketListAdapter);
        ticketListAdapter.notifyDataSetChanged();
    }

    private void setAdapter() {
        Log.d("rawat", "setAdapter: ");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvTicketList.setLayoutManager(linearLayoutManager);
        populateData();
    }

    @Override
    public void cardClicked(TicketListModel f) {
        Log.d("rawat", "cardClicked: ");
        Intent intent = new Intent(TicketListActivity.this, TicketDetailActivity.class);
//        intent.putExtra(ASSET_CODE,""+f.tagCode);
//        intent.putExtra(ASSET_NAME,""+f.tagName);
//        startActivity(intent);
    }

    public void FnNewTicket(View view) {
        startActivity(new Intent(this,TicketDetailActivity.class));
    }
}