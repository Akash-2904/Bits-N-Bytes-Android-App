package com.akash.bitsnbytesapp.customer.customerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.akash.bitsnbytesapp.R;
import com.akash.bitsnbytesapp.customer.pojos.User;
import com.akash.bitsnbytesapp.customer.workingActivities.MainActivity;
import com.akash.bitsnbytesapp.utility.NetworkChangeListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private Intent intent;
    private TextInputEditText phone, password;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (findViewById(R.id.userPhone));
        password = findViewById(R.id.userPassword);
        firebaseDatabase = FirebaseDatabase.getInstance("https://bitsnbyte-app-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("/BitsNBytes").child("/Users");
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
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
                } else {
                    login(Phone, Password);
                }
            }
        });
    }

    private void login(final String phone, final String password) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(phone).exists()) {
                    User user = dataSnapshot.child(phone).getValue(User.class);
                    if (password.equals(user.getPassword()) ){
                        Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logged_in", true);
                        editor.apply();
                        SharedPreferences sharedPreferencesPhoneNumber = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editorPhone = sharedPreferencesPhoneNumber.edit();
                        editorPhone.putString("phone_number", user.getPhone());
                        editorPhone.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finishAffinity();
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(findViewById(R.id.loginButton), "User Not Found", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void createNewUser(View view) {
        intent = new Intent(LoginActivity.this, RegistartionActivity.class);
        startActivity(intent);
    }

    /*public void openFP(View view) {
        intent = new Intent(LoginActivity.this, FPwdActivity.class);
        startActivity(intent);
    }*/
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
}