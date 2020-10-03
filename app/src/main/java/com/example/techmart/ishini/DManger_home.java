package com.example.techmart.ishini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.techmart.R;

public class DManger_home extends AppCompatActivity {
    Button button6;
    Button button12;
    Button button13;
    Button button14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_manger_home);
        OnclickButtonLister();
    }

    private void OnclickButtonLister() {
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( DManger_home.this,DM_My_Account.class);
                startActivity(intent);
            }
        });
        button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DManger_home.this,Add_New_Dp.class);
                startActivity(intent);
            }
        });
        button13 = (Button) findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DManger_home.this, DM_SearchDp.class);
//                Intent intent = new Intent(DManger_home.this, DP_UpdateDelete.class);
                startActivity(intent);
            }
        });
        button14 = (Button) findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(DManger_home.this, DM_AssignOrder.class);
                Intent intent = new Intent(DManger_home.this, DM_Order_Search.class);
                startActivity(intent);
            }
        });


    }

}