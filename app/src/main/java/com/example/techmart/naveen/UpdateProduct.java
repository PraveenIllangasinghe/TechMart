package com.example.techmart.naveen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.techmart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProduct extends AppCompatActivity {

    TextView Name,Price,Desc;
    DatabaseReference DBref;
    FirebaseAuth fireAuth;
    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        Intent updIntent = getIntent();

        final int locat = updIntent.getIntExtra("loc",0);

        Name = findViewById(R.id.updProdNameSeller);
        Price = findViewById(R.id.updProdPriceSeller);
        Desc = findViewById(R.id.updProdDesSeller);
        updateBtn = findViewById(R.id.sellerUpdateProBtn);



        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String NameUpd = Name.getText().toString();
                final String PriceUpd = Price.getText().toString();
                final String DescUpd = Desc.getText().toString();
                final float uprice = Float.parseFloat(PriceUpd);

                fireAuth = fireAuth.getInstance();
                FirebaseUser fbUser = fireAuth.getCurrentUser();

                final String userid = fbUser.getUid();

                DBref = FirebaseDatabase.getInstance().getReference().child("Products").child(userid).child(String.valueOf(locat+1));


                DBref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DBref.child("productName").setValue(NameUpd);
                        DBref.child("price").setValue(uprice);
                        DBref.child("description").setValue(DescUpd);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}