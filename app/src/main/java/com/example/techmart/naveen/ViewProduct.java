package com.example.techmart.naveen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.techmart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewProduct extends AppCompatActivity {

    List<Product> prod;
    RecyclerView viewProdRec;
    DatabaseReference viewDBref;
    FirebaseAuth fireAuth;
    ViewProductAdapter viewproductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);


        fireAuth = FirebaseAuth.getInstance();
        FirebaseUser fireUser = fireAuth.getCurrentUser();

        final String UserId = fireUser.getUid();

        viewProdRec = (RecyclerView) findViewById(R.id.viewProdRecView);
        viewProdRec.setLayoutManager(new LinearLayoutManager(this));

        prod = new ArrayList<>();

        viewDBref = FirebaseDatabase.getInstance().getReference("Products").child(UserId);

        viewDBref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    Product prodItem = data.getValue(Product.class);
                    prod.add(prodItem);
                }
                viewproductAdapter = new ViewProductAdapter(prod);
                viewProdRec.setAdapter(viewproductAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}