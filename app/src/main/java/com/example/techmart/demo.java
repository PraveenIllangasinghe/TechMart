package com.example.techmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class demo extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Button LogOutBtn;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //recycler view for order history
    //private RecyclerView recyclerView;
    //RecyclerView.LayoutManager layoutManager;
   // RecyclerViewAdapter recyclerViewAdapter;

    int []arr={R.drawable.casing, R.drawable.cpu, R.drawable.graphiccard, R.drawable.keyboard, R.drawable.monitor, R.drawable.mouse, R.drawable.power, R.drawable.ram};

    //end here

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        //recycler view for order history
        //recyclerView=findViewById(R.id.recyclerview);
       // layoutManager=new GridLayoutManager(this,2);
        //recyclerView.setLayoutManager(layoutManager);
        //recyclerViewAdapter=new RecyclerViewAdapter(arr);

        //recyclerView.setAdapter(recyclerViewAdapter);
       // recyclerView.setHasFixedSize(true);

        //end here
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();                                  //MSG button
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each options_menu ID as a set of Ids because each
        // options_menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_order_history)      //added del add
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        LogOutBtn = findViewById(R.id.logOutBtn);
        LogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent backToSignUp = new Intent(demo.this,SignupActivity.class);
                startActivity(backToSignUp);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}