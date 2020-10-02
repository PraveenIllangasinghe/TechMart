package com.example.techmart.ishini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class DP_UpdateDelete extends AppCompatActivity {
    EditText txtRiderName, txtRiderContactNo, txtRiderEmail, txtRiderAddress;
    Button butUpdateTonew, butDeleteTonew, butViewNew;
    DatabaseReference dbRef;
    DeliveryPerson newRider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_p__update_delete);
        final DeliveryPerson dpx = (DeliveryPerson) getIntent().getSerializableExtra("current_rider");
        txtRiderName = findViewById(R.id.inputRiderName);
        txtRiderEmail = findViewById(R.id.inputRiderEmail);
        txtRiderContactNo = findViewById(R.id.inputContactNumber);
        txtRiderAddress = findViewById(R.id.inputRiderAddress);

        butUpdateTonew = findViewById(R.id.butUpdateNew);
        butDeleteTonew = findViewById(R.id.butDeleteNew);
        butViewNew = findViewById(R.id.butViewNew);
        newRider = new DeliveryPerson();

        txtRiderName.setText(dpx.getRiderName());
        txtRiderAddress.setText(dpx.getRiderAddress());
        txtRiderEmail.setText(dpx.getRiderEmail());
        txtRiderContactNo.setText(dpx.getRiderContactNum());
        butViewNew.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    dbRef = FirebaseDatabase.getInstance().getReference().child("DeliveryPerson/"+dpx.getRiderID());
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                txtRiderName.setText(dataSnapshot.child("riderName").getValue().toString());
                                txtRiderAddress.setText(dataSnapshot.child("riderAddress").getValue().toString());
                                txtRiderEmail.setText(dataSnapshot.child("riderEmail").getValue().toString());
                                txtRiderContactNo.setText(dataSnapshot.child("riderContactNum").getValue().toString());

                            } else
                                Toast.makeText(getApplicationContext(), "cannot find newRider1", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    butUpdateTonew.setOnClickListener((new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dbRef = FirebaseDatabase.getInstance().getReference();
                            dbRef.child("DeliveryPerson/" + dpx.getRiderID() +"/riderName").setValue(txtRiderName.getText().toString().trim());
                            dbRef.child("DeliveryPerson/" + dpx.getRiderID() +"/riderEmail").setValue(txtRiderEmail.getText().toString().trim());
                            dbRef.child("DeliveryPerson/" + dpx.getRiderID() +"/riderAddress").setValue(txtRiderAddress.getText().toString().trim());
                            dbRef.child("DeliveryPerson/" + dpx.getRiderID() +"/riderContactNum").setValue(txtRiderContactNo.getText().toString().trim());
//                            DP_UpdateDelete.this.clearConsoles();
                            Toast.makeText(getApplicationContext(), "successfully updated", Toast.LENGTH_SHORT).show();
                        }
                    }));
                    butDeleteTonew.setOnClickListener((new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("DeliveryPerson/"+dpx.getRiderID());
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "successfully deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getBaseContext(), DM_SearchDp.class);
//                            intent.putExtra("current_rider", dpx);
                            startActivity(intent);
                        }
                    }));
                }

            }


        }));



    }

    private void clearConsoles() {
        txtRiderName.setText("");
        txtRiderEmail.setText("");
        txtRiderContactNo.setText("");
        txtRiderAddress.setText("");
    }

}








