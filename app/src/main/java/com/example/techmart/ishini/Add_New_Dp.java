
package com.example.techmart.ishini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.techmart.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class Add_New_Dp extends AppCompatActivity {
    EditText txtRiderName,txtRiderContactNo,txtRiderEmail,txtRiderAddress;
    Button butAddTonew;
    DatabaseReference dbRef;
    DeliveryPerson newRider;
    long maxId = 0;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__dp);

        txtRiderName = findViewById(R.id.inputRiderName);
        txtRiderEmail = findViewById(R.id.inputRiderEmail);
        txtRiderContactNo = findViewById(R.id.inputContactNumber);
        txtRiderAddress=findViewById(R.id.inputRiderAddress);

        butAddTonew  = findViewById(R.id.butAddNew);
        newRider = new DeliveryPerson();

        butAddTonew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("DeliveryPerson");

                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            maxId = dataSnapshot.getChildrenCount();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                try {
                    if (TextUtils.isEmpty(txtRiderName.getText().toString()))
                        Toast.makeText(Add_New_Dp.this.getApplicationContext(), "Empty RiderName", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtRiderContactNo.getText().toString()))
                        Toast.makeText(Add_New_Dp.this.getApplicationContext(), "Empty RiderContactNo", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtRiderEmail.getText().toString()))
                        Toast.makeText(Add_New_Dp.this.getApplicationContext(), "Empty RiderEmail", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtRiderAddress.getText().toString()))
                        Toast.makeText(Add_New_Dp.this.getApplicationContext(), "Empty RiderAddress", Toast.LENGTH_SHORT).show();
                    else {
                        newRider.setRiderName(txtRiderName.getText().toString().trim());
                        newRider.setRiderEmail(txtRiderEmail.getText().toString().trim());
                        newRider.setRiderContactNum(txtRiderContactNo.getText().toString().trim());
                        newRider.setRiderAddress(txtRiderAddress.getText().toString().trim());
                        newRider.setRiderID(Integer.parseInt(String.valueOf(maxId + 1)));

                        dbRef.child(String.valueOf(maxId + 1)).setValue(newRider);
                        Toast.makeText(Add_New_Dp.this.getApplicationContext(), "successfully inserted", Toast.LENGTH_SHORT).show();

                        Add_New_Dp.this.clearConsoles();

                    }


                }

                catch (NumberFormatException nfe) {
                    Toast.makeText(Add_New_Dp.this.getApplicationContext(), "invalid ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

    };

    private void clearConsoles() {
        txtRiderName.setText("");
        txtRiderEmail.setText("");
        txtRiderContactNo.setText("");
        txtRiderAddress.setText("");
    }
}

