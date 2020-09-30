package com.example.techmart.delivery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.techmart.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeliveryItemViewFragment extends Fragment {


    private MaterialToolbar materialToolBar;
    private DatabaseReference mdatabasereference;

    String itemId;

    TextView id, name, item, amount, phone, location, mail;
    Button delivered, complete;

    DeliveryItem deliveryItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_item_view, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        materialToolBar = requireActivity().findViewById(R.id.mainToolBar);
        materialToolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        materialToolBar.setNavigationOnClickListener(view -> {
            Navigation.findNavController(requireActivity(), R.id.mainNavHostFragment).navigate(R.id.action_deliveryItemViewFragment_to_mainFragment);
        });
        materialToolBar.setTitle(getString(R.string.pending));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        id = view.findViewById(R.id.oderId);
        name = view.findViewById(R.id.userName);
        item = view.findViewById(R.id.item);
        amount = view.findViewById(R.id.amount);
        phone = view.findViewById(R.id.contact);
        location = view.findViewById(R.id.location);
        mail = view.findViewById(R.id.mail);

        delivered = view.findViewById(R.id.deliverBtn);
        complete = view.findViewById(R.id.completeBtn);


        assert getArguments() != null;
        itemId = getArguments().getString("Key");

        mdatabasereference = FirebaseDatabase.getInstance().getReference("DeliveryCrew")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PendingDeliveries").child(itemId);

        mdatabasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                id.setText(snapshot.child("id").getValue(String.class));
                name.setText(snapshot.child("customerName").getValue(String.class));
                item.setText(snapshot.child("item").getValue(String.class));
                amount.setText(snapshot.child("amount").getValue(String.class));
                phone.setText(snapshot.child("contact").getValue(String.class));
                location.setText(snapshot.child("location").getValue(String.class));
                mail.setText(snapshot.child("email").getValue(String.class));

//                String amount, String id, String item, String location, String contact, String email, String customerName
                deliveryItem = new DeliveryItem(snapshot.child("amount").getValue(String.class),
                        snapshot.child("id").getValue(String.class),
                        snapshot.child("item").getValue(String.class),
                        snapshot.child("location").getValue(String.class),
                        snapshot.child("contact").getValue(String.class),
                        snapshot.child("email").getValue(String.class),
                        snapshot.child("customerName").getValue(String.class)
                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        delivered.setOnClickListener(view1 -> {
            delivered.setVisibility(View.GONE);
            complete.setVisibility(View.VISIBLE);
            materialToolBar.setTitle(getString(R.string.receipt));
        });

        complete.setOnClickListener(view12 -> {
            FirebaseDatabase.getInstance().getReference("DeliveryCrew")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("CompletedDeliveries").push().setValue(deliveryItem)
                    .addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Snackbar.make(requireView(), "Deliver completed successfully.", Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.green)).setTextColor(getResources().getColor(R.color.white)).show();

                        }
                    });

            FirebaseDatabase.getInstance().getReference("DeliveryCrew")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PendingDeliveries").child(itemId).removeValue()
                    .addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Navigation.findNavController(requireActivity(), R.id.mainNavHostFragment).navigate(R.id.action_deliveryItemViewFragment_to_deliveryHistoryFragment);
                        }
                    });
        });

        backCallBack();

    }

    private void backCallBack() {

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireActivity(), R.id.mainNavHostFragment).navigate(R.id.action_deliveryItemViewFragment_to_mainFragment);
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }
}