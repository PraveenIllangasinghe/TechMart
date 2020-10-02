package com.example.techmart.naveen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.techmart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DelProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button deleteBtn,updateBtn;
    FirebaseAuth fireAuth;
    DatabaseReference DBref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_product);

        Intent pIntent = getIntent();


        deleteBtn = findViewById(R.id.delBtn);
        updateBtn = findViewById(R.id.updBtn);

        final int location = pIntent.getIntExtra("Location",0);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireAuth = FirebaseAuth.getInstance();
                FirebaseUser prUser = fireAuth.getCurrentUser();

                final String prUserID = prUser.getUid();

                DBref = FirebaseDatabase.getInstance().getReference().child("Products").child(prUserID).child(String.valueOf(location+1));

                Toast.makeText(DelProduct.this,"Product deleted",Toast.LENGTH_SHORT).show();

                DBref.removeValue();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updIntent = new Intent(DelProduct.this, UpdateProduct.class);
                updIntent.putExtra("loc", location);
                startActivity(updIntent);


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String tag = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),tag,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}