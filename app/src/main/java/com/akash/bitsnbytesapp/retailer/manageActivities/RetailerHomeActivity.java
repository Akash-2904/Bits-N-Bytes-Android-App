package com.akash.bitsnbytesapp.retailer.manageActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.akash.bitsnbytesapp.R;
import com.akash.bitsnbytesapp.retailer.Activities.AddProduct;

public class RetailerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_home);
        findViewById(R.id.addProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetailerHomeActivity.this, AddProduct.class));

            }
        });
    }
}