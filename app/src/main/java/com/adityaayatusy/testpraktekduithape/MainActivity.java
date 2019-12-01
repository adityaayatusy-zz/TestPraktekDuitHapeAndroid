package com.adityaayatusy.testpraktekduithape;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.adityaayatusy.testpraktekduithape.adapter.AdapterListUsers;
import com.adityaayatusy.testpraktekduithape.adapter.AdapterScroll;
import com.adityaayatusy.testpraktekduithape.api.ApiServer;
import com.adityaayatusy.testpraktekduithape.api.ResponsApi;
import com.adityaayatusy.testpraktekduithape.model.DataModel;
import com.adityaayatusy.testpraktekduithape.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ResponsApi api;
    RecyclerView rv;
    LinearLayoutManager lm;
    AdapterListUsers adapter;
    ProgressBar pb;
    List<UserModel> allData = new ArrayList<UserModel>();
    Button load;
    public int current_page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        FloatingActionButton fab = findViewById(R.id.add_btn);
        load = findViewById(R.id.load);
        pb = findViewById(R.id.main_loading);
        rv = findViewById(R.id.rv);
        lm = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(lm);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStaff.class);
                startActivity(intent);
            }
        });

        api = new ApiServer().getClient().create(ResponsApi.class);

        firstLoad();

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLoad();
            }
        });


    }

    public void firstLoad(){
        Call<DataModel> res = api.getUsers(current_page,10);
        res.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                pb.setVisibility(View.GONE);
                List<UserModel> data = response.body().getContent();
                allData = data;
                adapter = new AdapterListUsers(allData,MainActivity.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d("error",t.getMessage().toString());
            }
        });
    }

    public void nextLoad(){
        current_page++;
        Call<DataModel> res = api.getUsers(current_page,10);
        res.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                pb.setVisibility(View.GONE);
                List<UserModel> data = response.body().getContent();
                allData.addAll(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d("error",t.getMessage().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Keluar")
                .setMessage("Apa anda yakin?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("No",null).show();
    }
}
