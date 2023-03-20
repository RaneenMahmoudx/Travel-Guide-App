package com.example.raneenandnemat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Sort_Adapter extends RecyclerView.Adapter<Sort_Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Destination> destinations;

    public Sort_Adapter(Context ctx, List<Destination> destinations){
        this.inflater=LayoutInflater.from(ctx);
        this.destinations=destinations;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cost_list_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.city.setText(destinations.get(position).getCity());
        holder.cost.setText(destinations.get(position).getCost());
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView city ,cost;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            city= itemView.findViewById(R.id.city);
            cost=itemView.findViewById(R.id.cost);

        }
    }


}