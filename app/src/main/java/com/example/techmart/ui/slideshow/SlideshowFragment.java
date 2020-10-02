package com.example.techmart.ui.slideshow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmart.HelperAdapter;
import com.example.techmart.Home;
import com.example.techmart.ProductModel;
import com.example.techmart.R;
import com.example.techmart.RecyclerViewAdapter;
import com.example.techmart.RecyclerViewClickInterface;
import com.example.techmart.demo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SlideshowFragment extends Fragment {

    List<ProductModel> productModel;
    RecyclerView recyclerView;
    HelperAdapter helperAdapter;
    DatabaseReference databaseReference;


/*    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
     RecyclerViewAdapter recyclerViewAdapter;
     Context cont;
     int i = 0;


    int []arr={R.drawable.casing, R.drawable.cpu, R.drawable.graphiccard, R.drawable.keyboard, R.drawable.monitor, R.drawable.mouse, R.drawable.power, R.drawable.ram};
    String []pNameArr={"casing","cpu","Graphic Card","keyboard","monitor","mouse","power supply","RAM"};    */

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

         final View root = inflater.inflate(R.layout.fragment_view_products, container, false);


        recyclerView= (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productModel=new ArrayList<>();

        databaseReference=FirebaseDatabase.getInstance().getReference("Product");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    ProductModel prd = ds.getValue(ProductModel.class);
                    productModel.add(prd);
                }
                helperAdapter = new HelperAdapter(productModel);
                recyclerView.setAdapter(helperAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //recycler view for order history
/*        recyclerView= (RecyclerView) root.findViewById(R.id.recyclerview);
        recyclerViewAdapter=new RecyclerViewAdapter(arr,pNameArr);
        recyclerView.setAdapter(recyclerViewAdapter);
        layoutManager=new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);   */
        //end here

        return root;
    }
}