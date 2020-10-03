package com.example.techmart.naveen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techmart.LoginActivity;
import com.example.techmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellerLogin extends AppCompatActivity {

    EditText seller_email, seller_password;
    Button sellerLoginBtn;
    TextView txt_seller_create_account;
   // DatabaseReference dref;
    FirebaseAuth fbAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        seller_email = findViewById(R.id.edit_txt_seller_login_email);
        seller_password = findViewById(R.id.edit_txt_seller_login_password);
        sellerLoginBtn = findViewById(R.id.seller_login_button);
        txt_seller_create_account = findViewById(R.id.seller_create_acc_txt);

        fbAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = fbAuth.getCurrentUser();
                if(user != null) {
                    Intent intent = new Intent(SellerLogin.this, AddProduct.class);
                    startActivity(intent);
                }
                else {

                }
            }
        };

        sellerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String sellerEmail = seller_email.getText().toString();
                String sellerPassword = seller_password.getText().toString();

                if(sellerEmail.isEmpty()){
                    seller_email.setError("Enter E-mail");
                    seller_email.requestFocus();
                }

                else if (sellerPassword.isEmpty()){
                    seller_password.setError("Enter Password");
                    seller_password.requestFocus();
                }

                else if(sellerEmail.isEmpty() && sellerPassword.isEmpty()){
                    Toast.makeText(SellerLogin.this, "Required fields should be filled", Toast.LENGTH_SHORT).show();
                }

                else if(!(sellerEmail.isEmpty() && sellerPassword.isEmpty())) {
                    fbAuth.signInWithEmailAndPassword(sellerEmail, sellerPassword).addOnCompleteListener(SellerLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SellerLogin.this, "Process Failed", Toast.LENGTH_SHORT).show();
                            } else {
                              //  dref = FirebaseDatabase.getInstance().getReference();
                                Intent intent2 = new Intent(SellerLogin.this, AddProduct.class);
                                startActivity(intent2);
                            }
                        }
                    });
                }

                else {
                    Toast.makeText(SellerLogin.this, "Problem Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_seller_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(SellerLogin.this, SignUpSeller.class);
                startActivity(intent3);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        fbAuth.addAuthStateListener(authStateListener);
    }
}