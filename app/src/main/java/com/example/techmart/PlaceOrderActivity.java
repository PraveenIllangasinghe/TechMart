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
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        PlaceOrderAddress = findViewById(R.id.et_place_ord_del_add);
        PlaceOrderDate = findViewById(R.id.et_place_ord_date);
        ConfirmOrderBtn = findViewById(R.id.confirmOrderBtn);

        Intent intent = getIntent();
        final Double tot = intent.getDoubleExtra("TotalAmount",0);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        String uid = user.getUid();

        dbRef = FirebaseDatabase.getInstance().getReference().child("OrderItems").child(uid).child("1");

        ConfirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String DeliveryAddress = PlaceOrderAddress.getText().toString();
                String OrderDate = PlaceOrderDate.getText().toString();

                dbRef.child("DeliveryAddress").setValue(DeliveryAddress);
                dbRef.child("OrderDate").setValue(OrderDate);
                dbRef.child("totalAmount").setValue(String.valueOf(tot));
                dbRef.child("status").setValue("Confirmed");
            }
        });



    }
}