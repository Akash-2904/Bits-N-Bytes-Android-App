package com.akash.bitsnbytesapp.customer.workingActivities;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akash.bitsnbytesapp.R;
import com.akash.bitsnbytesapp.retailer.pojos.Retailer;
import com.akash.bitsnbytesapp.retailer.viewHolders.RetailerViewHolder;
import com.akash.bitsnbytesapp.utility.NetworkChangeListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://bitsnbyte-app-default-rtdb.firebaseio.com/");
        DatabaseReference databaseReference = firebaseDatabase.getReference("/BitsNBytes").child("/Retailers");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        FirebaseRecyclerAdapter<Retailer, RetailerViewHolder> adapter = new FirebaseRecyclerAdapter<Retailer, RetailerViewHolder>(
                Retailer.class,
                R.layout.display_shop_layout,
                RetailerViewHolder.class,
                databaseReference

        ) {

            @Override
            public void populateViewHolder(RetailerViewHolder viewHolder, Retailer model, int position) {
                viewHolder.shopName.setText(model.getShop());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(MainActivity.this, "You've clicked on "+model.getShop(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ProductList.class);
                        intent.putExtra("data", model);
                        startActivity(intent);
                    }
                });
            }
        };
        {
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }
    }

}