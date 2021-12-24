package com.akash.bitsnbytesapp.retailer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.akash.bitsnbytesapp.R;
import com.akash.bitsnbytesapp.retailer.manageActivities.RetailerHomeActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProduct extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private TextInputEditText name,price,category;
    private AppCompatButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        firebaseDatabase= FirebaseDatabase.getInstance("https://bitsnbyte-app-default-rtdb.firebaseio.com/");
        databaseReference=firebaseDatabase.getReference("/BitsNBytes").child("/Products");

    }

    public void back(View view) {
        startActivity(new Intent(AddProduct.this, RetailerHomeActivity.class));
    }
}