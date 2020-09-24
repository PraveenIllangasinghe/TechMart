package com.example.techmart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.CartViewHolder>{

    List<Cart> ReceivedList;

    public CartRecyclerAdapter(List<Cart> receivedList) {
        ReceivedList = receivedList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        CartViewHolder cartViewHolder = (CartViewHolder)holder;

        Cart cart = ReceivedList.get(position);

        cartViewHolder.CartItemId.setText(cart.getProductId());
        cartViewHolder.CartItemName.setText(cart.getProductName());
        cartViewHolder.CartItemPrice.setText(cart.getUnitPrice().toString());
        cartViewHolder.CartItemDesc.setText(cart.getProductDescription());
        cartViewHolder.CartItemQuantity.setText(cart.getQuantity().toString());
        cartViewHolder.CartItemNetAmount.setText(cart.getNetAmount().toString());

    }

    @Override
    public int getItemCount() {
        return ReceivedList.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView CartItemId, CartItemName, CartItemDesc, CartItemPrice, CartItemQuantity, CartItemNetAmount;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            CartItemId = itemView.findViewById(R.id.cart_item_id);
            CartItemName = itemView.findViewById(R.id.cart_item_name);
            CartItemDesc = itemView.findViewById(R.id.cart_item_description);
            CartItemPrice = itemView.findViewById(R.id.cart_item_unit_price);
            CartItemQuantity = itemView.findViewById(R.id.cart_item_quantity);
            CartItemNetAmount = itemView.findViewById(R.id.cart_item_net_amount);
        }
    }

}
