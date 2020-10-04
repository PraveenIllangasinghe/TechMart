package com.example.techmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.techmart.ishini.DManger_home;
import com.example.techmart.naveen.SellerLogin;

public class SelectUserActivity extends AppCompatActivity {

    Button selBtn, ManagerBtn;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);


        selBtn = findViewById(R.id.SellerBtn);
        ManagerBtn = findViewById(R.id.DeliveryManagerBtn);
        back = findViewById(R.id.btl);

        selBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sellerLog = new Intent(SelectUserActivity.this, SellerLogin.class);
                startActivity(sellerLog);
            }
        });

        ManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent managerLog = new Intent(SelectUserActivity.this, DManger_home.class);
                startActivity(managerLog);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToIntent = new Intent(SelectUserActivity.this, LoginActivity.class);
                startActivity(backToIntent);
            }
        });


    }
}