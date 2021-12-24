package com.akash.bitsnbytesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    private Intent intent;
//    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

//    @Override
//    protected void onStop() {
//        unregisterReceiver(networkChangeListener);
//        super.onStop();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, EntryActivity.class));
                finishAffinity();
            }
        }, 3000);

    }
}