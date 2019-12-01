package com.adityaayatusy.testpraktekduithape.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adityaayatusy.testpraktekduithape.R;
import com.adityaayatusy.testpraktekduithape.model.UserModel;

import java.util.List;

public class AdapterListUsers extends RecyclerView.Adapter<AdapterListUsers.AdapterListHolder>{

    List<UserModel> users;
    Context context;

    public AdapterListUsers(List<UserModel> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_list_view,viewGroup,false);
        return new AdapterListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListHolder adapterListHolder, int i) {
        adapterListHolder.name.setText(users.get(i).getName());
        adapterListHolder.email.setText(users.get(i).getEmail());
        adapterListHolder.role.setText(users.get(i).getIssuerRole().getName().toLowerCase());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class AdapterListHolder extends RecyclerView.ViewHolder{
        TextView name,email,role;

        public AdapterListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            role = itemView.findViewById(R.id.role);
        }
    }
}
