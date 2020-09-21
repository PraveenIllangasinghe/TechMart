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

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button LoginBtn;
    TextView createAccount;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.customer_username_login);
        password = findViewById(R.id.customer_Password);
        LoginBtn = findViewById(R.id.login_btn);
        createAccount = findViewById(R.id.notRegTxt);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, demo.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Email = email.getText().toString();
                String Pass = password.getText().toString();

                if (Email.isEmpty()) {
                    email.setError("Please Enter Your Email Address");
                    email.requestFocus();
                }
                else if (Pass.isEmpty()) {
                    password.setError("Please Enter Your Password");
                    password.requestFocus();
                }
                else if(Email.isEmpty() && Pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill required fields", Toast.LENGTH_SHORT).show();
                }
                else if(!(Email.isEmpty() && Pass.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Please Check Your Email and Password", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent gotoDemo = new Intent(LoginActivity.this,demo.class);
                                startActivity(gotoDemo);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }
        });


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignUp = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intentSignUp);
            }
        });

     /*   Button Login_btn = (Button)findViewById(R.id.login_btn);
        Login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,
                        demo.class);
                startActivity(intent);


            }
        });     */


    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}