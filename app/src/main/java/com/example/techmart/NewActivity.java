package com.example.techmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewActivity extends AppCompatActivity {

    Button add;
    EditText edit,edName,edPrice,edDes;
    DatabaseReference dbRef;
    ProductModel pm1;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        txt = findViewById(R.id.displayProName);

        Intent intent = getIntent();

        String PName = intent.getStringExtra("ProductName");
        txt.setText(PName);


        edit =findViewById(R.id.edit8);
        edName = findViewById(R.id.EdName);
        edPrice = findViewById(R.id.EdPrice);
        edDes = findViewById(R.id.EdDes);
        add = findViewById(R.id.add8);

        pm1 = new ProductModel();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dbRef = FirebaseDatabase.getInstance().getReference().child("Product");
                pm1.setProductId(edit.getText().toString().trim());
                pm1.setProductName(edName.getText().toString().trim());
                pm1.setPrice(Float.parseFloat(edPrice.getText().toString().trim()));
                pm1.setDescription(edDes.getText().toString().trim());
                dbRef.child("Pr1").setValue(pm1);



            }
        });

    }
}