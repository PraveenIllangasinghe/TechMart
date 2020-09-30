package com.example.techmart.delivery.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.techmart.LoginActivity;
import com.example.techmart.R;
import com.example.techmart.databinding.SignInFragmentBinding;
import com.example.techmart.delivery.DeliveryActivity;
import com.example.techmart.delivery.dataBinding.AuthForm;
import com.example.techmart.demo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class SignInFragment extends Fragment {

    private SignInViewModel mViewModel;
    private SignInFragmentBinding binding;

    Button signin;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.sign_in_fragment, container, false);
        View view = binding.getRoot();
        binding.setSignInForm(new AuthForm());
        binding.setAuthViewModel(mViewModel);
        mViewModel.authForm = binding.getSignInForm();
        binding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        binding.signUpBtn.setOnClickListener(view -> {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainNavHostFragment, new SignUpFragment());
            transaction.addToBackStack("TAG");
            transaction.commit();
        });



        backCallBack();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signin = view.findViewById(R.id.signInBtn);

        signin.setOnClickListener(view1 -> {
            hideKeyboardFrom(requireContext(),view);
            FirebaseAuth.getInstance().signInWithEmailAndPassword(mViewModel.authForm.getSignInUserName(), mViewModel.authForm.getSignInPassword()).addOnCompleteListener(requireActivity(), task -> {
                if(!task.isSuccessful()) {
                    Snackbar.make(requireView(), "Invalid user name or password", Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.red)).setTextColor(getResources().getColor(R.color.white)).show();
                }
                else {
                    SharedPreferences pref = requireActivity().getSharedPreferences("deliverAuthState", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("state", true);
                    editor.apply();
                    Intent intent = new Intent(requireActivity(), DeliveryActivity.class);
                    startActivity(intent);
                }
            });
        });
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
                requireActivity().finish();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

}