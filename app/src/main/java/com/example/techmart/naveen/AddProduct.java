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
    long max = 0;
    Product Prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


     id = findViewById(R.id.addProdIdSeller);
     name = findViewById(R.id.addProdNameSeller);
     price = findViewById(R.id.addProdPriceSeller);
     des = findViewById(R.id.addProdDesSeller);

     addProd = findViewById(R.id.sellerAddProBtn);

     Prod = new Product();

     ref = FirebaseDatabase.getInstance().getReference().child("Product");

     ref.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             if(dataSnapshot.exists())
                 max = dataSnapshot.getChildrenCount();

         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });


     addProd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             Prod.setDescription(des.getText().toString().trim());
             Prod.setProductName(name.getText().toString().trim());
             Prod.setPrice(Float.parseFloat(price.getText().toString().trim()));
             Prod.setProductId(id.getText().toString().trim());

             ref.child(String.valueOf(max+1)).setValue(Prod);

         }
     });


    }
}