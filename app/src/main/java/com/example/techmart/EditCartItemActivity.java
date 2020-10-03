package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditCartItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView txtCartId, txtCartName, txtCartDesc, txtCartUnitPrice, txtCartQuantity, txtCartNetAmount;
    Button cart_item_update_btn, cart_item_delete_btn;
    DatabaseReference dbReference;
    DatabaseReference dbReference2;
    FirebaseAuth firebaseAuth;
    Spinner spn;

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
        spn = findViewById(R.id.spinner_upd_quntity);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.quantities, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);

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
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user1 = firebaseAuth.getCurrentUser();

                final String uId = user1.getUid();
                final String ordItemUpd = String.valueOf(POS+1);

                dbReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(uId).child(String.valueOf(POS+1));
                dbReference2 = FirebaseDatabase.getInstance().getReference().child("OrderItems").child(uId).child("1");
             //   Intent updIntent = new Intent(EditCartItemActivity.this, UpdateCartItem.class);
               final int txt_quantity = Integer.parseInt(spn.getSelectedItem().toString());
                Toast.makeText(EditCartItemActivity.this, "Update Item", Toast.LENGTH_SHORT).show();

                dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       Float up = snapshot.child("unitPrice").getValue(Float.class);
                        dbReference.child("quantity").setValue(txt_quantity);
                        dbReference.child("netAmount").setValue(up*txt_quantity);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                dbReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbReference2.child("itemQuantity"+ordItemUpd).setValue(txt_quantity);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


             //   startActivity(updIntent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}