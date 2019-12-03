package com.adityaayatusy.testpraktekduithape.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.adityaayatusy.testpraktekduithape.R;
import com.adityaayatusy.testpraktekduithape.model.UserModel;

import java.util.List;

public class AdapterListUsers extends RecyclerView.Adapter<AdapterListUsers.AdapterListHolder>{

    public List<UserModel> users;
    public Context context;
    private int VIEW_TYPE_LOADING = 0;

    public AdapterListUsers(List<UserModel> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (VIEW_TYPE_LOADING == 0) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_list_view, viewGroup, false);
            return new AdapterListHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading, viewGroup, false);
            return new AdapterListHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListHolder adapterListHolder, int i) {
        if(i >= users.size()){
            adapterListHolder.pb.setVisibility(View.VISIBLE);
        }else{
            adapterListHolder.name.setText(users.get(i).getName());
            adapterListHolder.email.setText(users.get(i).getEmail());
            adapterListHolder.role.setText(users.get(i).getIssuerRole().getName().toLowerCase());
        }

    }

    @Override
    public int getItemCount() {
        return users.size() + 1;
    }

    public int getItemViewType(int position) {
        if(position == users.size()){
            return VIEW_TYPE_LOADING = 1;
        }else{
            return VIEW_TYPE_LOADING = 0;
        }
    }

    class AdapterListHolder extends RecyclerView.ViewHolder{
        TextView name,email,role;
        ProgressBar pb;

        public AdapterListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            role = itemView.findViewById(R.id.role);
            pb = itemView.findViewById(R.id.loadingBar);
        }
    }

}
