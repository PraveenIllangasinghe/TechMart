package com.example.techmart.delivery;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.techmart.R;
import com.example.techmart.delivery.adapters.ItemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DeliveryHistoryFragment extends Fragment {

    private MaterialToolbar materialToolBar;


    RecyclerView recyclerView;
    Query query1;
    private DatabaseReference mdatabasereference;
    private ProgressDialog progressDialog;
    FirebaseRecyclerAdapter<DeliveryItem, ItemViewHolder> firebaseRecyclerAdapter;
    LinearLayoutManager mLayoutManager;
    FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        materialToolBar = requireActivity().findViewById(R.id.mainToolBar);
        materialToolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        materialToolBar.setTitle(getString(R.string.history));

        materialToolBar.setNavigationOnClickListener(view -> {
            Navigation.findNavController(requireActivity(), R.id.mainNavHostFragment).navigate(R.id.action_deliveryHistoryFragment_to_mainFragment);
        });

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
                    Snackbar.make(requireView(), "No completed deliveries yet.", Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.red)).setTextColor(getResources().getColor(R.color.white)).show();
                }
            }
        }, 4000);

        mdatabasereference = FirebaseDatabase.getInstance().getReference("DeliveryCrew")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("CompletedDeliveries");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery_history, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.historyView);

        backCallBack();

    }

    @Override
    public void onStart() {
        super.onStart();
        query1 = FirebaseDatabase.getInstance().getReference("DeliveryCrew")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("CompletedDeliveries");

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
                holder.itemView.setOnLongClickListener(view -> {
                    String id = getRef(position).getKey();

                    FirebaseDatabase.getInstance().getReference("DeliveryCrew")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("CompletedDeliveries").child(id).removeValue()
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Snackbar.make(requireView(), "Record deleted successfully", Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.green)).setTextColor(getResources().getColor(R.color.white)).show();

                                }
                            });

                    return false;
                });

            }

        };

        firebaseRecyclerAdapter.startListening();

        Context context;
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }


    private void backCallBack() {

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireActivity(), R.id.mainNavHostFragment).navigate(R.id.action_deliveryHistoryFragment_to_mainFragment);
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

}