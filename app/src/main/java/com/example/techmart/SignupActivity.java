package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText email, password, username, phone, address;
    Button SignUpBtn;
    TextView haveAccountTxt;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        username = findViewById(R.id.cusUNSignup);
        phone = findViewById(R.id.cusContactSignup);
        address = findViewById(R.id.cusAddSignup);
        SignUpBtn = findViewById(R.id.signupBtn);
        haveAccountTxt = findViewById(R.id.haveAccountTxt);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Pass = password.getText().toString();
                final String UN = username.getText().toString();
                final String PhoneNo = phone.getText().toString();
                final String addr = address.getText().toString();

                if (Email.isEmpty()) {
                    email.setError("Please Enter Your Email Address");
                    email.requestFocus();
                }
                else if (Pass.isEmpty()) {
                    password.setError("Please Enter Your Password");
                    password.requestFocus();
                }
                else if(Email.isEmpty() && Pass.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill required fields", Toast.LENGTH_SHORT).show();
                }
                else if(!(Email.isEmpty() && Pass.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Failed to Create User, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mFirebaseAuth = FirebaseAuth.getInstance();
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                String uid = user.getUid();
                                dbRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(uid);
                                float ct = 1;
                                dbRef.child("Count").setValue(ct);
                                dbRef.child("username").setValue(UN);
                                dbRef.child("address").setValue(addr);
                                dbRef.child("phoneNo").setValue(PhoneNo);
                                startActivity(new Intent(SignupActivity.this,demo.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignupActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }

            }
        });

        haveAccountTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }
}