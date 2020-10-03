package com.example.techmart;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.ViewHolderClass> {


    List<ProductModel> RetrieveList;
    int []arr={R.drawable.power, R.drawable.cpu, R.drawable.monitor, R.drawable.motherboard, R.drawable.graphiccard, R.drawable.mouse, R.drawable.power, R.drawable.ram};


    public HelperAdapter(List<ProductModel> retrieveList) {
        RetrieveList = retrieveList;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {

        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;

        ProductModel productModel = RetrieveList.get(position);

        viewHolderClass.pid.setText(productModel.getProductId());
        viewHolderClass.pname.setText(productModel.getProductName());
        viewHolderClass.pprice.setText(productModel.getPrice().toString());
        viewHolderClass.pdescription.setText(productModel.getDescription());
        viewHolderClass.proImage.setImageResource(arr[position]);
   //     Picasso.get().load(RetrieveList.get(position).getProductImage()).into((Target) viewHolderClass.pimage);


    }

    @Override
    public int getItemCount() {
        return RetrieveList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pid,pname,pprice,pdescription;
        ImageView proImage;
        RelativeLayout relative;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            pid=itemView.findViewById(R.id.Pid);
            pname=itemView.findViewById(R.id.Pname);
            pprice=itemView.findViewById(R.id.Pprice);
            pdescription=itemView.findViewById(R.id.Pdescription);
            proImage=itemView.findViewById(R.id.productImageView);
   //         pimage=itemView.findViewById(R.id.productImageView);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            ProductModel p1 = RetrieveList.get(pos);
            String proID = p1.getProductId();
            String proName = p1.getProductName();
            Float proPrice = p1.getPrice();
            String proDescription = p1.getDescription();

            Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(),AddToCart.class);
            intent.putExtra("ProductID", proID);
            intent.putExtra("ProductName", proName);
            intent.putExtra("ProductPrice", proPrice);
            intent.putExtra("ProductDescription", proDescription);
            itemView.getContext().startActivity(intent);
            //TextView PID;
            //PID=itemView.findViewById(R.id.Pid);

        }
    }


}
