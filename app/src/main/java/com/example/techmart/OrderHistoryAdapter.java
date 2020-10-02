package com.example.techmart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>{


    List<OrderItem> dataList;

    public OrderHistoryAdapter(List<OrderItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_list_item,parent,false);
        OrderHistoryViewHolder orderHistoryViewHolder = new OrderHistoryViewHolder(view);
        return orderHistoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        OrderHistoryViewHolder orderHistoryViewHolder = (OrderHistoryViewHolder) holder;

        OrderItem orderItem = dataList.get(position);

        orderHistoryViewHolder.txtOrdDate.setText(orderItem.getOrderDate());
        orderHistoryViewHolder.txtOrdAmount.setText(orderItem.getTotalAmount().toString());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }




    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {

        TextView txtOrdDate, txtOrdAmount;

        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrdAmount = itemView.findViewById(R.id.order_history_rv_item_amount);
            txtOrdDate = itemView.findViewById(R.id.order_history_rv_item_date);

        }
    }
}
