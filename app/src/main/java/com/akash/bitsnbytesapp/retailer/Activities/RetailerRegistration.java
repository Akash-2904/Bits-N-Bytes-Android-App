package com.akash.bitsnbytesapp.retailer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.akash.bitsnbytesapp.R;
import com.akash.bitsnbytesapp.retailer.pojos.Retailer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RetailerRegistration extends AppCompatActivity {

    private Intent intent;
    private TextInputEditText retailerName, retailerPhone, retailerEmail, retailerPassword, confPassword, shopName;
    private AppCompatButton signUp;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_registration);
        retailerName = (findViewById(R.id.newRetailername));
        retailerPhone = (findViewById(R.id.newRetailerPhone));
        retailerEmail = (findViewById(R.id.retailerEmail));
        retailerPassword = (findViewById(R.id.newRetailerPassword));
        confPassword = (findViewById(R.id.RConfirmPassword));
        shopName = findViewById(R.id.shopName);
        signUp = findViewById(R.id.RsignUp);
        progressBar = findViewById(R.id.Rprogress_circular);

        firebaseDatabase = FirebaseDatabase.getInstance("https://bitsnbyte-app-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("/BitsNBytes").child("/Retailers");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRetailer();
            }
        });
    }


    private void createRetailer() {
        String Name, Phone, Email, Password, rePassword, Shop;
        Name = retailerName.getText().toString().trim();
        Shop = shopName.getText().toString().trim();
        Phone = retailerPhone.getText().toString().trim();
        Email = retailerEmail.getText().toString().trim();
        Password = retailerPassword.getText().toString().trim();
        rePassword = confPassword.getText().toString().trim();
        if (Name.isEmpty()) {
            retailerName.setError("Name can't be Empty");
            retailerName.requestFocus();
        } else if (Shop.isEmpty() || Shop.equals(null)) {
            shopName.setError("Enter Valid Shop Name");
            shopName.requestFocus();
        } else if (Phone.length() != 10) {
            retailerPhone.setError("Enter Valid Number");
            retailerPhone.requestFocus();
        } else if (Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            retailerEmail.setError("Enter Valid Email Id");
            retailerEmail.requestFocus();
        } else if (Password.isEmpty() || Password.length() <= 6) {
            retailerPassword.setError("Password too short");
            retailerPassword.requestFocus();
        } else if (rePassword.isEmpty() || !Password.equals(rePassword)) {
            confPassword.setError("Password Not Match");
            confPassword.requestFocus();
        } else {
            Retailer retailer = new Retailer(Name, Shop, Phone, Email, Password);
            registerRetailer(retailer);
        }
    }

    private void registerRetailer(Retailer retailer) {
        databaseReference.child(retailer.getPhone()).setValue(retailer);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signUp.setVisibility(View.INVISIBLE);
                Toast.makeText(RetailerRegistration.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.VISIBLE);
                intent = new Intent(RetailerRegistration.this, RetailerLogin.class);
                startActivity(intent);
                finishAffinity();
            }
        }, 2500);
        clearViews();
    }


    private void clearViews() {
        retailerName.setText("");
        retailerPhone.setText("");
        shopName.setText("");
        retailerEmail.setText("");
        retailerPassword.setText("");
        confPassword.setText("");
    }

    public void openLogin(View view) {
        intent = new Intent(RetailerRegistration.this, RetailerLogin.class);
        startActivity(intent);
        finishAffinity();
    }
}