package com.example.techmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditCartItemActivity extends AppCompatActivity {

    TextView txtCartId, txtCartName, txtCartDesc, txtCartUnitPrice, txtCartQuantity, txtCartNetAmount;
    Button cart_item_update_btn, cart_item_delete_btn;
    DatabaseReference dbReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart_item);



        txtCartId = findViewById(R.id.ECT_pid);
        txtCartName = findViewById(R.id.ECT_pname);
        txtCartDesc = findViewById(R.id.ECT_pdescription);
        txtCartUnitPrice = findViewById(R.id.ECT_unitprice);
        txtCartQuantity = findViewById(R.id.ECT_quantity);
        txtCartNetAmount = findViewById(R.id.ECT_netamount);

        cart_item_delete_btn = findViewById(R.id.cart_del_btn);
        cart_item_update_btn = findViewById(R.id.cart_upd_btn);

        Intent intent = getIntent();


        String CPID = intent.getStringExtra("CartProductID");
        String CPName = intent.getStringExtra("CartProductName");
        String CPDesc = intent.getStringExtra("CartProductDescription");
        Float CPUP = intent.getFloatExtra("CartUnitPrice",0);
        Float CPQun = intent.getFloatExtra("CartQuantity",0);
        Float CPNA = intent.getFloatExtra("CartNetAmount",0);
        final int POS = intent.getIntExtra("Position",0);


        String cPUP = CPUP.toString();
        String cPQun = CPQun.toString();
        String cPNA = CPNA.toString();

        txtCartId.setText(CPID);
        txtCartName.setText(CPName);
        txtCartDesc.setText(CPDesc);
        txtCartUnitPrice.setText(String.valueOf(CPUP));
        txtCartQuantity.setText(cPQun);
        txtCartNetAmount.setText(cPNA);

        cart_item_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();

                final String uid = user.getUid();

                dbReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(uid).child(String.valueOf(POS+1));
                Toast.makeText(EditCartItemActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                dbReference.removeValue();
            }
        });


        cart_item_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditCartItemActivity.this, "Update Item", Toast.LENGTH_SHORT).show();
            }
        });

    }
}