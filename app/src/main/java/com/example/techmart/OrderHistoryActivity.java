package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    List<OrderItem> orderItem;
    RecyclerView orderHistoryRecyclerView;
    DatabaseReference fbDbRef;
    FirebaseAuth fbAuth;
    OrderHistoryAdapter orderHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        fbAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fbAuth.getCurrentUser();

        final String uid = user.getUid();

        orderHistoryRecyclerView = (RecyclerView) findViewById(R.id.order_history_recycler_view);
        orderHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderItem = new ArrayList<>();

        fbDbRef = FirebaseDatabase.getInstance().getReference("OrderItems").child(uid);

        fbDbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    OrderItem ordITem = ds.getValue(OrderItem.class);
                    orderItem.add(ordITem);
                }
                orderHistoryAdapter = new OrderHistoryAdapter(orderItem);
                orderHistoryRecyclerView.setAdapter(orderHistoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}