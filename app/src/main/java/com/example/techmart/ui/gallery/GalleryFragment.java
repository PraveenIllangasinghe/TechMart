package com.example.techmart.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmart.Cart;
import com.example.techmart.CartRecyclerAdapter;
import com.example.techmart.HelperAdapter;
import com.example.techmart.OrderHistoryActivity;
import com.example.techmart.PlaceOrderActivity;
import com.example.techmart.ProductModel;
import com.example.techmart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    List<Cart> cart;
    RecyclerView recyclerview;
    CartRecyclerAdapter cartRecyclerAdapter;
    DatabaseReference dbRef;
    FirebaseAuth fbAuth;
    private float totalAmount=0;
    TextView Tv_tot;
    Button place_order_btn, order_history_btn;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
   /*     galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });     */

       final  View root = inflater.inflate(R.layout.fragment_gallery, container, false);

       fbAuth = FirebaseAuth.getInstance();

        FirebaseUser cus_user = fbAuth.getCurrentUser();

        String uid = cus_user.getUid();

        recyclerview= (RecyclerView) root.findViewById(R.id.Cart_recycler_view);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        cart=new ArrayList<>();

        Tv_tot = root.findViewById(R.id.txt_TotalCartAmount);
        place_order_btn = root.findViewById(R.id.place_order_btn);
        order_history_btn = root.findViewById(R.id.View_order_history_btn);


        dbRef=FirebaseDatabase.getInstance().getReference("Cart").child(uid);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Cart crt = ds.getValue(Cart.class);
                    cart.add(crt);
                    totalAmount = (float) totalAmount+crt.getNetAmount();
                }
                cartRecyclerAdapter = new CartRecyclerAdapter(cart);
                recyclerview.setAdapter(cartRecyclerAdapter);
                Tv_tot.setText(String.valueOf(totalAmount));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        place_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent place_ord_intent = new Intent(getActivity(), PlaceOrderActivity.class);
                place_ord_intent.putExtra("TotalAmount", totalAmount);
                startActivity(place_ord_intent);
            }
        });


        order_history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order_history_intent = new Intent(getActivity(), OrderHistoryActivity.class);
                startActivity(order_history_intent);
            }
        });



        return root;
    }


}