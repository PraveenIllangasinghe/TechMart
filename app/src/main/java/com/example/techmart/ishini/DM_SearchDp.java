package com.example.techmart.ishini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.techmart.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DM_SearchDp extends AppCompatActivity {
    DatabaseReference dbRef;
    ArrayList<DeliveryPerson> riders = new ArrayList<>();
    private ListView lv;
    private SearchView sv;
    RidersList ridersAdapter;
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_m__search_dp);
        spinner=(ProgressBar)findViewById(R.id.progressBar1);
        sv = (SearchView) findViewById(R.id.searchView);
        lv = (ListView) findViewById(R.id.rider_list);
        dbRef = FirebaseDatabase.getInstance().getReference().child("DeliveryPerson");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
//                    riders.clear();

                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        DeliveryPerson rider = postSnapshot.getValue(DeliveryPerson.class);
                        riders.add(rider);
                    }
                        ridersAdapter = new RidersList(DM_SearchDp.this, riders);

                    lv.setAdapter(ridersAdapter);
                    spinner.setVisibility(View.GONE);
                } else
                    Toast.makeText(getApplicationContext(), "cannot find riders", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                spinner.setVisibility(View.GONE);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeliveryPerson dpx = riders.get(position);
                Toast.makeText(DM_SearchDp.this, String.valueOf(riders.get(position).getOrderNo()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), DP_UpdateDelete.class);
                intent.putExtra("current_rider", dpx);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<DeliveryPerson> results = new ArrayList<>();
                for(DeliveryPerson x: riders){
                    if(x.getRiderName().toLowerCase().contains(query.toLowerCase())){
                        results.add(x);
                    }
                }
                ((RidersList) lv.getAdapter()).update(results);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<DeliveryPerson> results = new ArrayList<>();
                for(DeliveryPerson x: riders){
                    if(x.getRiderName().toLowerCase().contains(newText.toLowerCase())){
                        results.add(x);
                    }
                }
                ((RidersList) lv.getAdapter()).update(results);

                return false;
            }
        });
        return true;
    }

}
