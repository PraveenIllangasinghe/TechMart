package com.example.techmart.ishini;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.techmart.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class DM_Add_DP extends AppCompatActivity {

    EditText txtOrderNo,txtRiderName,txtRiderId,txtVehicleId;
    Button butAddToOrder;
    DatabaseReference dbRef;
    DeliveryPerson dmAdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_m__add__d_p);

        txtOrderNo = findViewById(R.id.inputOrderNo);
        txtRiderName = findViewById(R.id.inputRiderName);
        txtRiderId = findViewById(R.id.inputRiderId);
        txtVehicleId = findViewById(R.id.inputVehicleId);

        butAddToOrder = findViewById(R.id.button7);
        dmAdd = new DeliveryPerson();

        butAddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("DeliveryPerson");
                try {
                    //String temp = txtOrderNo.getText().toString();
                    //String xxx = txtRiderName.getText().toString();
                    if (TextUtils.isEmpty(txtOrderNo.getText().toString()))
                        Toast.makeText(DM_Add_DP.this.getApplicationContext(), "Empty OrderNumber", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtRiderName.getText().toString()))
                        Toast.makeText(DM_Add_DP.this.getApplicationContext(), "Empty RiderName", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtRiderId.getText().toString()))
                        Toast.makeText(DM_Add_DP.this.getApplicationContext(), "Empty RiderID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtRiderId.getText().toString()))
                        Toast.makeText(DM_Add_DP.this.getApplicationContext(), "Empty VehicleID", Toast.LENGTH_SHORT).show();
                    else {
                        dmAdd.setOrderNo(txtOrderNo.getText().toString().trim());
                        dmAdd.setRiderName(txtRiderName.getText().toString().trim());
                        dmAdd.setRiderID(Integer.parseInt(txtRiderId.getText().toString().trim()));
                        dmAdd.setVehicalID(Integer.parseInt(txtVehicleId.getText().toString().trim()));
                        UUID uuid = UUID.randomUUID();

                        dbRef.child(uuid.toString()).setValue(dmAdd);
                        Toast.makeText(DM_Add_DP.this.getApplicationContext(), "successfully inserted", Toast.LENGTH_SHORT).show();
                        DM_Add_DP.this.clearConsoles();

                    }
                }

                catch(NumberFormatException nfe){
                    Toast.makeText(DM_Add_DP.this.getApplicationContext(), "invalid ID", Toast.LENGTH_SHORT).show();
                }


            }
        });
    };




    private void clearConsoles() {
        txtOrderNo.setText("");
        txtRiderName.setText("");
        txtRiderId.setText("");
        txtVehicleId.setText("");

    }


}