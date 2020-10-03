package com.example.techmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlaceOrderActivity extends AppCompatActivity {

    EditText PlaceOrderAddress, PlaceOrderDate;
    Button ConfirmOrderBtn;
    DatabaseReference dbRef;
    DatabaseReference dbRef2;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        PlaceOrderAddress = findViewById(R.id.et_place_ord_del_add);
        PlaceOrderDate = findViewById(R.id.et_place_ord_date);
        ConfirmOrderBtn = findViewById(R.id.confirmOrderBtn);

        Intent intent = getIntent();
        final Float tot = intent.getFloatExtra("TotalAmount",0);
        final float count = intent.getFloatExtra("Counter",0);
        final int i = (int)count;

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        final String uid = user.getUid();

        dbRef = FirebaseDatabase.getInstance().getReference().child("OrderItems").child(uid).child(String.valueOf(i));

        ConfirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String DeliveryAddress = PlaceOrderAddress.getText().toString();
                String OrderDate = PlaceOrderDate.getText().toString();
                String I = String.valueOf(i);

                dbRef.child("DeliveryAddress").setValue(DeliveryAddress);
                dbRef.child("OrderDate").setValue(OrderDate);
                dbRef.child("totalAmount").setValue(tot);
                dbRef.child("status").setValue("Confirmed");
                dbRef.child("orderId").setValue(uid+I);

                float updCount = i+1;

                dbRef2 = FirebaseDatabase.getInstance().getReference().child("Customer").child(uid);
                dbRef2.child("Count").setValue(updCount);

            }
        });



    }
}