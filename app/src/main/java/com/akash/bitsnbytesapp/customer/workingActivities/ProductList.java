package com.akash.bitsnbytesapp.customer.workingActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.akash.bitsnbytesapp.R;
import com.akash.bitsnbytesapp.retailer.pojos.Retailer;
import com.akash.bitsnbytesapp.utility.NetworkChangeListener;

public class ProductList extends AppCompatActivity {
    private TextView shopName;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        shopName=findViewById(R.id.fetchShopName);
        final Retailer data = (Retailer) getIntent().getSerializableExtra("data");
        shopName.setText(data.getShop());
    }

    public void back(View view) {
        startActivity(new Intent(ProductList.this, MainActivity.class));
    }
}