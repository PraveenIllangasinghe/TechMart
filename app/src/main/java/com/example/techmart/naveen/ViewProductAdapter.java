package com.example.techmart.naveen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmart.R;

import java.util.List;

public class ViewProductAdapter extends RecyclerView.Adapter<ViewProductAdapter.viewProdViewHolder> {


    List<Product> newProdList;

    public ViewProductAdapter(List<Product> newList) {
        this.newProdList = newList;
    }

    @NonNull
    @Override
    public viewProdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vw = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_products_list,parent,false);

        viewProdViewHolder viewprodholder = new viewProdViewHolder(vw);
        return viewprodholder;

    }

    @Override
    public void onBindViewHolder(@NonNull viewProdViewHolder holder, int position) {

            viewProdViewHolder viewprodviewhold = (viewProdViewHolder) holder;

            Product prodct = newProdList.get(position);

            viewprodviewhold.pid.setText(prodct.getProductId());
            viewprodviewhold.pName.setText(prodct.getProductName());
            viewprodviewhold.pPrice.setText(prodct.getPrice().toString());
            viewprodviewhold.pDes.setText(prodct.getDescription());

    }

    @Override
    public int getItemCount() {
        return newProdList.size();
    }


    public class viewProdViewHolder extends RecyclerView.ViewHolder{

        TextView pid,pName,pPrice,pDes;

        public viewProdViewHolder(@NonNull View itemView) {
            super(itemView);

            pid = itemView.findViewById(R.id.prodIdLayout);
            pName = itemView.findViewById(R.id.prodNameLayout);
            pPrice = itemView.findViewById(R.id.prodPriceLayout);
            pDes = itemView.findViewById(R.id.prodDesLayout);
        }
    }


}
