package com.example.techmart.ishini;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmart.R;

import java.util.ArrayList;
import java.util.List;

class RidersList extends ArrayAdapter<DeliveryPerson> {
    private Activity context;
    List<DeliveryPerson> riders;
    List<DeliveryPerson> filteredRiders = null;
    public RidersList(Activity context, List<DeliveryPerson> riders) {
        super(context, R.layout.fragment_list_item_rider, riders);
        this.context = context;
        this.riders = riders;
    }
    public class ViewHolder{
        TextView textViewName;
        TextView textViewEmail;
    }
    @Override
    public int getCount() {
        return riders.size();
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
            viewHolder.textViewName = row.findViewById(R.id.label_list_item);
            viewHolder.textViewEmail = row.findViewById(R.id.label_list_item_id);
            row.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) row.getTag();
        }
        viewHolder.textViewName.setText(riders.get(position).getRiderName());
        viewHolder.textViewEmail.setText(riders.get(position).getRiderEmail());

        return row;

    }
    public void update(ArrayList<DeliveryPerson> results){
        riders = new ArrayList<>();
        riders.addAll(results);
        notifyDataSetChanged();
    }

}


