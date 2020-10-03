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

import com.example.techmart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpSeller extends AppCompatActivity {

    EditText email, password;
    Button sellerSignUpBtn;
    FirebaseAuth fireAuth;
    DatabaseReference DBref;
    TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_seller);

        fireAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.signUpSellerEmail);
        password = findViewById(R.id.signUpSellerPassword);
        sellerSignUpBtn = findViewById(R.id.signUpSellerBtn);
        txt_login = findViewById(R.id.seller_txt_has_account);

        sellerSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if(Email.isEmpty()){
                    email.setError("Enter E-mail");
                    email.requestFocus();
                }

                else if (Password.isEmpty()){
                    password.setError("Enter Password");
                    password.requestFocus();
                }

                else if(Email.isEmpty() && Password.isEmpty()){
                    Toast.makeText(SignUpSeller.this, "Required fields should be filled", Toast.LENGTH_SHORT).show();
                }

                else if(!(Email.isEmpty() && Password.isEmpty())) {
                    fireAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(SignUpSeller.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUpSeller.this, "Process Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                fireAuth = FirebaseAuth.getInstance();
                                FirebaseUser seller = fireAuth.getCurrentUser();
                                String Sid = seller.getUid();
                              //  DBref = FirebaseDatabase.getInstance().getReference().child("Seller").child(Sid);
                              //  DBref.child("Status").setValue("True");
                                startActivity(new Intent(SignUpSeller.this, AddProduct.class));
                            }
                        }
                    });
                }

                else {
                    Toast.makeText(SignUpSeller.this, "Problem Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sellerLoginIntent = new Intent(SignUpSeller.this, SellerLogin.class);
                startActivity(sellerLoginIntent);
            }
        });

    }
}