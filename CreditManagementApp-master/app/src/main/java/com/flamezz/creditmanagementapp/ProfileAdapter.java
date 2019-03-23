package com.flamezz.creditmanagementapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {


   private ArrayList<Profile> arrayList;
    private Context context;
    ProfileAdapter(ArrayList<Profile> arrayList)
    {
        this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_view,parent,false);
        ProfileViewHolder viewHolder = new ProfileViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        holder.textName.setText(arrayList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SelectUserActivity.class);
                intent.putExtra("NAME",arrayList.get(position).getName());
                intent.putExtra("EMAIL",arrayList.get(position).getEmail());
                intent.putExtra("PHONE",arrayList.get(position).getPhone());
                intent.putExtra("CREDIT",arrayList.get(position).getCurrentcredit());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder
    {
        TextView textName;
        ProfileViewHolder(View view)
        {
            super(view);
            textName = view.findViewById(R.id.textName);
        }
    }
}
