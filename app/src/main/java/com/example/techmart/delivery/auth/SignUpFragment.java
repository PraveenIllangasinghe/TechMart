package com.example.techmart.delivery.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.techmart.R;
import com.example.techmart.databinding.FragmentSignUpBinding;
import com.example.techmart.delivery.DeliveryActivity;
import com.example.techmart.delivery.dataBinding.AuthForm;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class SignUpFragment extends Fragment {


    private SignInViewModel mViewModel;
    private FragmentSignUpBinding binding;

    Button signup;

    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        View view = binding.getRoot();
        binding.setSignUpForm(new AuthForm());
        binding.setAuthViewModel(mViewModel);
        mViewModel.authForm = binding.getSignUpForm();
        binding.setLifecycleOwner(this);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signup = view.findViewById(R.id.containedButton);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(view1 -> {
            hideKeyboardFrom(requireContext(),view);
            mAuth.createUserWithEmailAndPassword(Objects.requireNonNull(mViewModel.authForm.getMobile()), Objects.requireNonNull(mViewModel.authForm.getPassword()))
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseDatabase.getInstance().getReference("DeliveryCrew")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("MemberDetails").child("Name")
                                    .setValue(mViewModel.authForm.getUserName())
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Intent intent = new Intent(requireActivity(), DeliveryActivity.class);
                                            startActivity(intent);
                                            SharedPreferences pref = requireActivity().getSharedPreferences("deliverAuthState", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = pref.edit();
                                            editor.putBoolean("state", true);
                                            editor.apply();
                                        }
                                    });
                        }
                        else
                        {
                            Snackbar.make(requireView(), "Oopz! Something went wrong, Please try again.", Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.red)).setTextColor(getResources().getColor(R.color.white)).show();
                        }
                    });

        });

        backCallBack();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void backCallBack() {

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainNavHostFragment, new SignInFragment());
                transaction.addToBackStack("TAG");
                transaction.commit();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }


}