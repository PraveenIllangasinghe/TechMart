package com.example.techmart.ishini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.techmart.OrderHistoryAdapter;
import com.example.techmart.OrderItem;
import com.example.techmart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DM_Order_Search extends AppCompatActivity {
    DatabaseReference dbRef;
    ArrayList<OrderModel> orders = new ArrayList<>();
    private ListView lv;
    private SearchView sv;
    OrderListX ordersAdapter;
    private ProgressBar spinner;
    FirebaseAuth fbAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_m__order__search);
        spinner=(ProgressBar)findViewById(R.id.progressBar1);
        sv = (SearchView) findViewById(R.id.searchView);
        lv = (ListView) findViewById(R.id.rider_list);
        fbAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fbAuth.getCurrentUser();

        String uid = null;
        if(user != null){
            uid = user.getUid();
        }

        dbRef = FirebaseDatabase.getInstance().getReference().child("Orders");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
//                    riders.clear();

                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                        String uxid = postSnapshot.getKey();
                        OrderModel order = postSnapshot.getValue(OrderModel.class);
                        orders.add(order);
                    }
                    ordersAdapter = new OrderListX(DM_Order_Search.this, orders);

                    lv.setAdapter(ordersAdapter);
                    spinner.setVisibility(View.GONE);
                } else
                    Toast.makeText(getApplicationContext(), "cannot find riders", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                spinner.setVisibility(View.GONE);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderModel dpx = orders.get(position);
                Toast.makeText(DM_Order_Search.this, String.valueOf(orders.get(position).getOrderId()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), DM_AssignOrder.class);
                intent.putExtra("current_order", dpx);
                startActivity(intent);
            }
        });
    }
}