package com.example.techmart.delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.techmart.R;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.MaterialDeliveryTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        NavHostFragment navHostFragment = new NavHostFragment();
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainNavHostFragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
    }

}