package com.akash.bitsnbytesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.akash.bitsnbytesapp.customer.customerActivities.LoginActivity;
import com.akash.bitsnbytesapp.retailer.Activities.RetailerLogin;

public class EntryActivity extends AppCompatActivity {

    private AppCompatButton User,Retailer;
//    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        User=findViewById(R.id.userButton);
        Retailer=findViewById(R.id.retailerButton);

        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntryActivity.this, LoginActivity.class));
                finishAffinity();
            }
        });

        Retailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntryActivity.this, RetailerLogin.class));
            }
        });

    }
//    @Override
//    protected void onStart() {
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(networkChangeListener, filter);
//        super.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        unregisterReceiver(networkChangeListener);
//        super.onStop();
//    }
}