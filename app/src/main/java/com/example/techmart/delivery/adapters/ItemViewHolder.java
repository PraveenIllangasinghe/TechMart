package com.example.techmart.delivery.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmart.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    View view;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }

    public void setLocation(String loc) {
        TextView location = (TextView) view.findViewById(R.id.deliverLocation);
        location.setText(loc);
    }

    public void setId(String id) {
        TextView location = (TextView) view.findViewById(R.id.deliverId);
        location.setText(id);
    }
}
