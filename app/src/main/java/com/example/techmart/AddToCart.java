package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddToCart extends AppCompatActivity {

    TextView TVProdName, TVProdId, TVProdPrice, TVProdDes;
    Button addToCartBtn;
    DatabaseReference dbRef;
 //   DatabaseReference DBRef;
    long maxId=0;
 //   long count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        TVProdName = findViewById(R.id.tvProdName);
        TVProdId = findViewById(R.id.tvProdId);
        TVProdPrice = findViewById(R.id.tvProdPrice);
        TVProdDes = findViewById(R.id.tvProdDes);
        addToCartBtn = findViewById(R.id.AddToCartBtn);

        Intent intent = getIntent();


        String PName = intent.getStringExtra("ProductName");
        String PID = intent.getStringExtra("ProductID");
        Float PPrice = intent.getFloatExtra("ProductPrice",0);
        String PDes = intent.getStringExtra("ProductDescription");

        String pPrice = PPrice.toString();

        TVProdName.setText(PName);
        TVProdId.setText(PID);
        TVProdPrice.setText(pPrice);
        TVProdDes.setText(PDes);


        dbRef = FirebaseDatabase.getInstance().getReference().child("Cart").child("Customer001");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxId=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

  /*      DBRef = FirebaseDatabase.getInstance().getReference().child("Cart").child(String.valueOf(maxId+1));

        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists())
                    count=(datasnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });     */


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_ProName = TVProdName.getText().toString();
                String txt_ProID = TVProdId.getText().toString();
                String txt_ProDes = TVProdDes.getText().toString();
                Float txt_ProPrice = Float.parseFloat(TVProdPrice.getText().toString());

                dbRef.child(String.valueOf(maxId+1)).child("productName").setValue(txt_ProName);
                dbRef.child(String.valueOf(maxId+1)).child("productId").setValue(txt_ProID);
                dbRef.child(String.valueOf(maxId+1)).child("productDescription").setValue(txt_ProDes);
                dbRef.child(String.valueOf(maxId+1)).child("unit price").setValue(txt_ProPrice);
            }
        });


    }
}