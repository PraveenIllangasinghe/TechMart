package com.example.techmart.ui.gallery;

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

import com.example.techmart.Cart;
import com.example.techmart.ProductModel;
import com.example.techmart.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    //private ListView listView;
    private Button add;
    private EditText edit;
    DatabaseReference dbRef;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        edit = root.findViewById(R.id.editct);
        add = root.findViewById(R.id.add);

    /*    add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
                String txt_name = edit.getText().toString();
               // FirebaseDatabase.getInstance().getReference().child("STD").setValue(txt_name);
                dbRef.child("Name").setValue(txt_name);
            }
        }); */

    /*    listView = root.findViewById(R.id.listView);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, list);
        listView.setAdapter(adapter);*/

    /*   DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Product");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                String []nameArr = {};
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProductModel pm = snapshot.getValue(ProductModel.class);
                    //String txt = pm.getBrand() +" : "+ pm.getpName();
                   nameArr[i] = pm.getProductName();
                   i++;
                }
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });     */



        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      /*  DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Product");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                String []nameArr = {};
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProductModel pm = snapshot.getValue(ProductModel.class);
                    //String txt = pm.getBrand() +" : "+ pm.getpName();
                    nameArr[i] = pm.getProductName();
                    i++;
                }
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
                String txt_name = edit.getText().toString();
                // FirebaseDatabase.getInstance().getReference().child("STD").setValue(txt_name);
                dbRef.child("Name").setValue(txt_name);
            }
        });


    }
}