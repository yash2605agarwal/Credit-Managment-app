package com.flamezz.creditmanagementapp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TrasnferViewHolder> {


   private ArrayList<Profile> arrayList;
    private Context context;
    private OnClickListener listener;
    TransferAdapter(ArrayList<Profile> arrayList,OnClickListener listener)
    {
        this.arrayList=arrayList;
        this.listener=listener;
    }
    @NonNull
    @Override
    public TrasnferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_view,parent,false);
        TrasnferViewHolder viewHolder = new TrasnferViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrasnferViewHolder holder, final int position) {
        holder.textName.setText(arrayList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(arrayList.get(position).getCurrentcredit(),arrayList.get(position).getName()
                ,arrayList.get(position).getEmail(),arrayList.get(position).getPhone());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class TrasnferViewHolder extends RecyclerView.ViewHolder
    {
        TextView textName;
        TrasnferViewHolder(View view)
        {
            super(view);
            textName  = view.findViewById(R.id.textName);
        }
    }
}
