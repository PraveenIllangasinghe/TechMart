package com.example.techmart.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.techmart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {

    TextView txtCusUniId,txtCusEmail;
    FirebaseAuth auth;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        auth = FirebaseAuth.getInstance();

        txtCusUniId = root.findViewById(R.id.Cus_unique_id);
        txtCusEmail = root.findViewById(R.id.Cus_email_preview);

        FirebaseUser user = auth.getCurrentUser();
        String CusId = user.getUid();
        String CusEmail = user.getEmail();

        txtCusUniId.setText(CusId);
        txtCusEmail.setText(CusEmail);

        return root;
    }
}