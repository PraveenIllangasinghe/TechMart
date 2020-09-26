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

public class AddToCart extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView TVProdName, TVProdId, TVProdPrice, TVProdDes;
    Button addToCartBtn;
    Spinner spinner;
    DatabaseReference dbRef;
    DatabaseReference dbRef2;
    FirebaseAuth mAuth;
    long maxId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        mAuth = FirebaseAuth.getInstance();

        TVProdName = findViewById(R.id.tvProdName);
        TVProdId = findViewById(R.id.tvProdId);
        TVProdPrice = findViewById(R.id.tvProdPrice);
        TVProdDes = findViewById(R.id.tvProdDes);
        addToCartBtn = findViewById(R.id.AddToCartBtn);
        spinner = findViewById(R.id.spinnerQuantity);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.quantities, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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

        spinner.setOnItemSelectedListener(this);

        FirebaseUser user = mAuth.getCurrentUser();

        final String uid = user.getUid();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Cart").child(uid);
        dbRef2 = FirebaseDatabase.getInstance().getReference().child("OrderItems").child(uid);

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



        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_ProName = TVProdName.getText().toString();
                String txt_ProID = TVProdId.getText().toString();
                String txt_ProDes = TVProdDes.getText().toString();
                Float txt_ProPrice = Float.parseFloat(TVProdPrice.getText().toString());
                int txt_quantity = Integer.parseInt(spinner.getSelectedItem().toString());

                Float txt_net_amount = (Float) txt_ProPrice*txt_quantity;

                dbRef.child(String.valueOf(maxId+1)).child("productName").setValue(txt_ProName);
                dbRef.child(String.valueOf(maxId+1)).child("productId").setValue(txt_ProID);
                dbRef.child(String.valueOf(maxId+1)).child("productDescription").setValue(txt_ProDes);
                dbRef.child(String.valueOf(maxId+1)).child("unitPrice").setValue(txt_ProPrice);
                dbRef.child(String.valueOf(maxId+1)).child("quantity").setValue(txt_quantity);
                dbRef.child(String.valueOf(maxId+1)).child("netAmount").setValue(txt_net_amount);

                String itemNo = String.valueOf(maxId+1);

                dbRef2.child("1").child("item"+itemNo).setValue(txt_ProName);
                dbRef2.child("1").child("itemQuantity"+itemNo).setValue(txt_quantity);
                dbRef2.child("1").child("itemDescription"+itemNo).setValue(txt_ProDes);
                dbRef2.child("1").child("status").setValue("NotConfirmed");

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