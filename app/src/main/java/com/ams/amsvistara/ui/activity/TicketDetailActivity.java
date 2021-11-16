package com.ams.amsvistara.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.ActivityTicketDetailBinding;

public class TicketDetailActivity extends AppCompatActivity {
    Intent galleryIntent; private static final int PICK_FROM_GALLARY = 1;
    ActivityTicketDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Ticket Detail ");
       binding= DataBindingUtil. setContentView(this, R.layout.activity_ticket_detail);
    }

    public void ImagePicker(View view) {
        galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, PICK_FROM_GALLARY);
    }

}