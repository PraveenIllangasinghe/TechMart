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

import com.example.techmart.Home;
import com.example.techmart.R;
import com.example.techmart.RecyclerViewAdapter;


public class SlideshowFragment extends Fragment {


    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
     RecyclerViewAdapter recyclerViewAdapter;
     Context cont;

    int []arr={R.drawable.casing, R.drawable.cpu, R.drawable.graphiccard, R.drawable.keyboard, R.drawable.monitor, R.drawable.mouse, R.drawable.power, R.drawable.ram};



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);




        //recycler view for order history
        recyclerView= (RecyclerView) root.findViewById(R.id.recyclerview);
        recyclerViewAdapter=new RecyclerViewAdapter(arr);
        recyclerView.setAdapter(recyclerViewAdapter);
        layoutManager=new GridLayoutManager(getActivity(),2);

        recyclerView.setLayoutManager(layoutManager);

   //     recyclerView.setHasFixedSize(true);

        //end here

        return root;
    }
}