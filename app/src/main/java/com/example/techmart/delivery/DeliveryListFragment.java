package com.example.techmart.delivery;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmart.R;
import com.example.techmart.delivery.adapters.ItemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DeliveryListFragment extends Fragment {

    private DeliveryListViewModel mViewModel;
    private MaterialToolbar materialToolBar;
    private MaterialButton historyBtn;

    FirebaseAuth firebaseAuth;

    RecyclerView recyclerView;
    Query query1;
    private DatabaseReference mdatabasereference;
    private ProgressDialog progressDialog;
    FirebaseRecyclerAdapter<DeliveryItem, ItemViewHolder> firebaseRecyclerAdapter;
    LinearLayoutManager mLayoutManager;


    public static DeliveryListFragment newInstance() {
        return new DeliveryListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delivery_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DeliveryListViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        materialToolBar = requireActivity().findViewById(R.id.mainToolBar);
        materialToolBar.setNavigationIcon(null);
        materialToolBar.setTitle(getString(R.string.pending));
        historyBtn = setUpHistoryButton(materialToolBar);
        materialToolBar.addView(historyBtn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.getCurrentUser().getUid();

        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Loading Items Please Wait...");
        progressDialog.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                    Snackbar.make(requireView(), "No pending deliveries.", Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.red)).setTextColor(getResources().getColor(R.color.white)).show();

                }
            }
        }, 4000);


        mdatabasereference = FirebaseDatabase.getInstance().getReference("DeliveryCrew")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PendingDeliveries");
        recyclerView = (RecyclerView) view.findViewById(R.id.pendingView);


//        FirebaseDatabase.getInstance().getReference("DeliveryCrew")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PendingDeliveries").push().setValue(new DeliveryItem(" 3000.00", "IC8760", "Bed", "Nugegoda", "0702345858", "s@gmail.com", "Sanu"))
//                .addOnCompleteListener(task1 -> {
//                    if (task1.isSuccessful()) {
//                    }
//                });
//
//        FirebaseDatabase.getInstance().getReference("DeliveryCrew")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PendingDeliveries").push().setValue(new DeliveryItem(" 2010.00", "IC8460", "Bed", "Rajagiriya", "0702545858", "z@gmail.com", "Sam"))
//                .addOnCompleteListener(task1 -> {
//                    if (task1.isSuccessful()) {
//                    }
//                });
//        FirebaseDatabase.getInstance().getReference("DeliveryCrew")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PendingDeliveries").push().setValue(new DeliveryItem(" 3000.00", "IC8760", "Toys", "Maharagama", "0702387858", "j@gmail.com", "Jerry"))
//                .addOnCompleteListener(task1 -> {
//                    if (task1.isSuccessful()) {
//                    }
//                });

        backCallBack();

    }

    @Override
    public void onStart() {
        super.onStart();
        query1 = FirebaseDatabase.getInstance().getReference("DeliveryCrew")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("PendingDeliveries");

        FirebaseRecyclerOptions<DeliveryItem> options =
                new FirebaseRecyclerOptions.Builder<DeliveryItem>()
                        .setQuery(query1, DeliveryItem.class)
                        .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DeliveryItem, ItemViewHolder>(options) {

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.delivery_item, parent, false);
                progressDialog.dismiss();
                return new ItemViewHolder(view);

            }


            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull DeliveryItem model) {
                holder.setLocation(model.getLocation());
                holder.setId(model.getId());
                progressDialog.dismiss();
                holder.itemView.setOnClickListener(view -> {
                    String id = getRef(position).getKey();
//                    Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show();

                    Fragment fragment = new DeliveryItemViewFragment();
                    Bundle args =  new Bundle();
                    args.putString("Key", id);
                    fragment.setArguments(args);

                    Navigation.findNavController(requireActivity(), R.id.mainNavHostFragment).navigate(R.id.action_mainFragment_to_deliveryItemViewFragment, args);

                });

            }

        };

        firebaseRecyclerAdapter.startListening();

        Context context;
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    private MaterialButton setUpHistoryButton(MaterialToolbar materialToolbar) {

        MaterialButton b1 = new MaterialButton(requireContext(), null, R.attr.imageButtonStyle);


        b1.setIcon(ResourcesCompat.getDrawable(requireActivity().getResources(), R.drawable.ic_baseline_history_24, null));
        b1.setBackgroundColor(getResources().getColor(R.color.deliveryColorPrimary));
        MaterialToolbar.LayoutParams l3 = new Toolbar.LayoutParams(
                MaterialToolbar.LayoutParams.WRAP_CONTENT,
                MaterialToolbar.LayoutParams.WRAP_CONTENT
        );

        l3.setMargins(0, 0, 30, 0);
        l3.gravity = Gravity.END;
        b1.setLayoutParams(l3);


        b1.setOnClickListener(view -> {
            Navigation.findNavController(requireActivity(), R.id.mainNavHostFragment).navigate(R.id.action_mainFragment_to_deliveryHistoryFragment);
        });

        return b1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        historyBtn.setVisibility(View.GONE);
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