package com.example.techmart;

import android.content.Intent;
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


    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView CartItemId, CartItemName, CartItemDesc, CartItemPrice, CartItemQuantity, CartItemNetAmount;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            CartItemId = itemView.findViewById(R.id.cart_item_id);
            CartItemName = itemView.findViewById(R.id.cart_item_name);
            CartItemDesc = itemView.findViewById(R.id.cart_item_description);
            CartItemPrice = itemView.findViewById(R.id.cart_item_unit_price);
            CartItemQuantity = itemView.findViewById(R.id.cart_item_quantity);
            CartItemNetAmount = itemView.findViewById(R.id.cart_item_net_amount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int posit = getAdapterPosition();
            Cart c1 = ReceivedList.get(posit);
            String cart_prod_id= c1.getProductId();
            String cart_prod_name = c1.getProductName();
            String cart_prod_desc = c1.getProductDescription();
            Float cart_prod_unit_price = c1.getUnitPrice();
            Float cart_prod_quantity = c1.getQuantity();
            Float cart_prod_net_amount = c1.getNetAmount();

            Toast.makeText(view.getContext(), String.valueOf(posit), Toast.LENGTH_SHORT).show();
            Intent intent_editCart = new Intent(view.getContext(),EditCartItemActivity.class);
            intent_editCart.putExtra("CartProductID", cart_prod_id);
            intent_editCart.putExtra("CartProductName", cart_prod_name);
            intent_editCart.putExtra("CartUnitPrice", cart_prod_unit_price);
            intent_editCart.putExtra("CartProductDescription", cart_prod_desc);
            intent_editCart.putExtra("CartQuantity", cart_prod_quantity);
            intent_editCart.putExtra("CartNetAmount", cart_prod_net_amount);
            intent_editCart.putExtra("Position", posit);

            itemView.getContext().startActivity(intent_editCart);
        }
    }

}
