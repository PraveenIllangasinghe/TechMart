package com.example.techmart.ishini;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.techmart.R;

import java.util.ArrayList;
import java.util.List;

class OrderListX extends ArrayAdapter<OrderModel> {
    private Activity context;
    List<OrderModel> orders;
    public OrderListX(Activity context, List<OrderModel> orders) {
        super(context, R.layout.fragment_list_item_rider, orders);
        this.context = context;
        this.orders = orders;
    }
    public class ViewHolder{
        TextView textViewId;
        TextView textViewCost;
        TextView textViewStatus;
    }
    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        ViewHolder viewHolder;
        LayoutInflater inflater = context.getLayoutInflater();
        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list_item_rider, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewId = row.findViewById(R.id.label_list_item);
            viewHolder.textViewCost = row.findViewById(R.id.label_list_item_id);
            viewHolder.textViewStatus = row.findViewById(R.id.label_list_item_status);
            row.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) row.getTag();
        }
        viewHolder.textViewId.setText("Order Id: " + String.valueOf(orders.get(position).getOrderId()));
        viewHolder.textViewCost.setText("Total price (Rs): " + orders.get(position).getTotalAmount().toString());
        viewHolder.textViewStatus.setText("Order status: " + orders.get(position).getStatus());

        return row;

    }
    public void update(ArrayList<OrderModel> results){
        orders = new ArrayList<>();
        orders.addAll(results);
        notifyDataSetChanged();
    }

}


