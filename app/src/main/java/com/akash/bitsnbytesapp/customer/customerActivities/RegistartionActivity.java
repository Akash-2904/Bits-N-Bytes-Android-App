package com.akash.bitsnbytesapp.customer.customerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.akash.bitsnbytesapp.R;
import com.akash.bitsnbytesapp.customer.pojos.User;
import com.akash.bitsnbytesapp.utility.NetworkChangeListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistartionActivity extends AppCompatActivity {

    private Intent intent;
    private TextInputEditText userName, userPhone, userEmail, userPassword, confirmPassword;
    private AppCompatButton signUp;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ProgressBar progressBar;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);
        userName = (findViewById(R.id.newUsername));
        userPhone = (findViewById(R.id.newUserPhone));
        userEmail = (findViewById(R.id.userEmail));
        userPassword = (findViewById(R.id.newUserPassword));
        confirmPassword = (findViewById(R.id.ConfirmPassword));
        signUp = findViewById(R.id.signUp);
        progressBar=findViewById(R.id.progress_circular);


        firebaseDatabase = FirebaseDatabase.getInstance("https://bitsnbyte-app-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("/BitsNBytes").child("/Users");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        String Name, Phone, Email, Password, rePassword;
        Name = userName.getText().toString().trim();
        Phone = userPhone.getText().toString().trim();
        Email = userEmail.getText().toString().trim();
        Password = userPassword.getText().toString().trim();
        rePassword = confirmPassword.getText().toString().trim();
        if (Name.isEmpty()) {
            userName.setError("Name can't be Empty");
            userName.requestFocus();
        } else if (Phone.length() != 10) {
            userPhone.setError("Enter Valid Number");
            userPhone.requestFocus();
        } else if (Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            userEmail.setError("Enter Valid Email Id");
            userEmail.requestFocus();
        } else if (Password.isEmpty() || Password.length() <= 6) {
            userPassword.setError("Password too short");
            userPassword.requestFocus();
        } else if (rePassword.isEmpty() || !Password.equals(rePassword)) {
            confirmPassword.setError("Password Not Match");
            confirmPassword.requestFocus();
        } else {
            User user=new User(Name,Phone,Email,Password);
            registerUser(user);
        }
    }

    private void registerUser(User user) {
        databaseReference.child(user.getPhone()).setValue(user);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signUp.setVisibility(View.INVISIBLE);

                Toast.makeText(RegistartionActivity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.VISIBLE);
                intent=new Intent(RegistartionActivity.this,LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        },2500);
        clearViews();
    }

    private void clearViews() {
        userName.setText("");
        userPhone.setText("");
        userEmail.setText("");
        userPassword.setText("");
        confirmPassword.setText("");
    }


    public void openLogin(View view) {
        intent = new Intent(RegistartionActivity.this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }
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