package com.example.techmart.ishini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.techmart.OrderHistoryAdapter;
import com.example.techmart.OrderItem;
import com.example.techmart.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class DM_AssignOrder extends AppCompatActivity {
    private Activity context;
    DatabaseReference dbRef;

    EditText txtOrderId, txtRiderId, txtRiderName, txtAddress, txtSubtotal, txtDeliveryFee, txtTotal, txtStatus;
    Button btnAssignOrder;
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_m__assign_order);
        final OrderModel opx = (OrderModel) getIntent().getSerializableExtra("current_order");
        txtOrderId = findViewById(R.id.txtOrderId);
        txtRiderId = findViewById(R.id.txtRiderId);
        txtAddress = findViewById(R.id.txtAddress);
        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtDeliveryFee = findViewById(R.id.txtDeliveryFee);
        txtTotal = findViewById(R.id.txtTotal);
        txtStatus = findViewById(R.id.txtStatus);
        btnAssignOrder = findViewById(R.id.btnAssignOrder);

        txtOrderId.setEnabled(false);
        txtOrderId.setFocusable(false);
        txtOrderId.setCursorVisible(false);
        txtOrderId.setKeyListener(null);
        txtOrderId.setBackgroundColor(Color.TRANSPARENT);

        txtAddress.setEnabled(false);
        txtAddress.setFocusable(false);
        txtAddress.setCursorVisible(false);
        txtAddress.setKeyListener(null);
        txtAddress.setBackgroundColor(Color.TRANSPARENT);

        txtSubtotal.setEnabled(false);
        txtSubtotal.setFocusable(false);
        txtSubtotal.setCursorVisible(false);
        txtSubtotal.setKeyListener(null);
        txtSubtotal.setBackgroundColor(Color.TRANSPARENT);

        txtStatus.setEnabled(false);
        txtStatus.setFocusable(false);
        txtStatus.setCursorVisible(false);
        txtStatus.setKeyListener(null);
        txtStatus.setBackgroundColor(Color.TRANSPARENT);


        txtTotal.setEnabled(false);
        txtTotal.setFocusable(false);
        txtTotal.setCursorVisible(false);
        txtTotal.setKeyListener(null);
        txtTotal.setBackgroundColor(Color.TRANSPARENT);

        txtOrderId.setText(String.valueOf(opx.getOrderId()));
        txtRiderId.setText(String.valueOf(opx.getRiderID()));
        txtAddress.setText(opx.getDeliveryAddress());
        txtSubtotal.setText(opx.getTotalAmount().toString());
        txtStatus.setText(opx.getStatus());

        btnAssignOrder.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtDeliveryFee.getText().toString().trim().equals("")){
                    new AlertDialog.Builder(DM_AssignOrder.this)
                            .setTitle("Error")
                            .setMessage("Delivery fee cannot be empty.")
                            .create().show();
                } else if(txtRiderId.getText().toString().trim().equals("")){
                    new AlertDialog.Builder(DM_AssignOrder.this)
                            .setTitle("Error")
                            .setMessage("Rider Id cannot be empty.")
                            .create().show();
                } else if(!isNumeric(txtDeliveryFee.getText().toString().trim())) {
                    new AlertDialog.Builder(DM_AssignOrder.this)
                            .setTitle("Error")
                            .setMessage("Delivery should be a number.")
                            .create().show();
                }
                else{

                    dbRef = FirebaseDatabase.getInstance().getReference();
                    dbRef.child("Orders/" + opx.getOrderId() +"/orderId").setValue(Integer.parseInt(txtOrderId.getText().toString().trim()));
                    dbRef.child("Orders/" + opx.getOrderId() +"/riderID").setValue(Integer.parseInt(txtRiderId.getText().toString().trim()));
                    dbRef.child("Orders/" + opx.getOrderId() +"/totalAmount").setValue(Float.parseFloat(txtSubtotal.getText().toString().trim()));
                    dbRef.child("Orders/" + opx.getOrderId() +"/deliveryFee").setValue(Float.parseFloat(txtDeliveryFee.getText().toString().trim()));
                    dbRef.child("Orders/" + opx.getOrderId() +"/finalAmount").setValue(opx.calculateTotal());
                    dbRef.child("Orders/" + opx.getOrderId() +"/DeliveryAddress").setValue(txtAddress.getText().toString().trim());
                    opx.setStatus("Shipped");
                    dbRef.child("Orders/" + opx.getOrderId() +"/status").setValue(opx.getStatus());

                    txtStatus.setText("Shipped");
                    txtTotal.setText(opx.getFinalAmount().toString());

                    Toast.makeText(getApplicationContext(), "Successfully assigned the order", Toast.LENGTH_SHORT).show();
                }


            }
        }));
    }


}