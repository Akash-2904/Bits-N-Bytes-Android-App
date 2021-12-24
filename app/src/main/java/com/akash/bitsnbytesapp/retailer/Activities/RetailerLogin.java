package com.akash.bitsnbytesapp.retailer.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.akash.bitsnbytesapp.R;
import com.akash.bitsnbytesapp.retailer.manageActivities.RetailerHomeActivity;
import com.akash.bitsnbytesapp.retailer.pojos.Retailer;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetailerLogin extends AppCompatActivity {
    private Intent intent;
    private TextInputEditText phone, password;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);
        phone = (findViewById(R.id.RPhone));
        password = findViewById(R.id.RPassword);
        firebaseDatabase = FirebaseDatabase.getInstance("https://bitsnbyte-app-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("/BitsNBytes").child("/Retailers");
        findViewById(R.id.RloginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Phone, Password;
                Phone = phone.getText().toString().trim();
                Password = password.getText().toString().trim();
                if (Phone.length() != 10) {
                    phone.setError("Enter Valid Phone Number");
                    phone.requestFocus();
                } else if (Password.isEmpty() || Password.length() < 6) {
                    password.setError("Enter Valid Password");
                    password.requestFocus();
                }else{
                    retailer(Phone,Password);
                }
            }

        });
    }

    private void retailer(final String phone, final String password) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(phone).exists()) {
                    Retailer retailer = dataSnapshot.child(phone).getValue(Retailer.class);
                    if (password.equals(retailer.getPassword()) ){
                        Toast.makeText(RetailerLogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        /*SharedPreferences sharedPreferences = RetailerLogin.this.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logged_in", true);
                        editor.apply();
                        SharedPreferences sharedPreferencesPhoneNumber = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editorPhone = sharedPreferencesPhoneNumber.edit();
                        editorPhone.putString("phone_number", retailer.getPhone());
                        editorPhone.apply();*/
                        startActivity(new Intent(RetailerLogin.this, RetailerHomeActivity.class));
                        finishAffinity();
                    } else {
                        Toast.makeText(RetailerLogin.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(findViewById(R.id.RloginButton), "User Not Found", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError databaseError) {
                Toast.makeText(RetailerLogin.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void newRetailer(View view) {
        startActivity(new Intent(RetailerLogin.this, RetailerRegistration.class));
        finishAffinity();
    }
}