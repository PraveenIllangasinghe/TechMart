package com.example.techmart.naveen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.techmart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddProduct extends AppCompatActivity {


    EditText id,name,price,des;
    Button addProd, logOutSellerBtn;
    DatabaseReference ref;
    long max = 0;
    Product Prod;
    FirebaseAuth sellerAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

    sellerAuth = FirebaseAuth.getInstance();
    FirebaseUser seller_user = sellerAuth.getCurrentUser();
    String seller_user_id = seller_user.getUid();

     id = findViewById(R.id.addProdIdSeller);
     name = findViewById(R.id.addProdNameSeller);
     price = findViewById(R.id.addProdPriceSeller);
     des = findViewById(R.id.addProdDesSeller);

     addProd = findViewById(R.id.sellerAddProBtn);

     Prod = new Product();

     ref = FirebaseDatabase.getInstance().getReference().child("Products").child(seller_user_id);

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


     logOutSellerBtn = findViewById(R.id.sellerSignUpOutbtn);
     logOutSellerBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             FirebaseAuth.getInstance().signOut();
             Intent intentsignout = new Intent(AddProduct.this, SignUpSeller.class);
             startActivity(intentsignout);
         }
     });


    }
}