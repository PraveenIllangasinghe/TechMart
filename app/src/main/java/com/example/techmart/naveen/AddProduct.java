package com.example.techmart.naveen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.techmart.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddProduct extends AppCompatActivity {


    EditText id,name,price,des;
    Button addProd;
    DatabaseReference ref;
    long max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


     id = findViewById(R.id.addProdIdSeller);
     name = findViewById(R.id.addProdNameSeller);
     price = findViewById(R.id.addProdPriceSeller);
     des = findViewById(R.id.addProdDesSeller);

     addProd = findViewById(R.id.sellerAddProBtn);

     ref = FirebaseDatabase.getInstance().getReference().child("Product");

     ref.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });


     addProd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

         }
     });


    }
}