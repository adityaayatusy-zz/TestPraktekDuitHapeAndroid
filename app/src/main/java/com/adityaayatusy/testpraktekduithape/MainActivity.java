package com.adityaayatusy.testpraktekduithape;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import android.widget.TextView;

import com.adityaayatusy.testpraktekduithape.adapter.AdapterListUsers;
import com.adityaayatusy.testpraktekduithape.api.ApiServer;
import com.adityaayatusy.testpraktekduithape.api.ResponsApi;
import com.adityaayatusy.testpraktekduithape.model.DataModel;
import com.adityaayatusy.testpraktekduithape.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public ResponsApi api;
    public RecyclerView rv;
    public LinearLayoutManager lm;
    public AdapterListUsers adapter;
    public ProgressBar pb;
    public Button reload;
    public TextView notice;
    public List<UserModel> allData = new ArrayList<UserModel>();
    public int current_page = 0;
    public int last = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        //init widget
        FloatingActionButton fab = findViewById(R.id.add_btn);
        pb = findViewById(R.id.main_loading);
        rv = findViewById(R.id.rv);
        reload = findViewById(R.id.reload);
        notice = findViewById(R.id.notice);

        lm = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(lm);
        //event ketika di scroll
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //mengecek posisi terakhir list item
                if(lm.getItemCount()-1 == lm.findLastVisibleItemPosition()){
                    if(last == 0){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nextLoad();
                            }
                        },1000);
                    }
                    last = 1;
                }
            }
        });

        //init api
        api = new ApiServer().getClient().create(ResponsApi.class);

        //load data
        firstLoad();

        //button
        fab.setOnClickListener(this);
        reload.setOnClickListener(this);
    }

    //load data pertama
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
                pb.setVisibility(View.GONE);
                reload.setVisibility(View.VISIBLE);
                notice.setVisibility(View.VISIBLE);
                Log.d("error",t.getMessage().toString());
            }
        });
    }

    //load data selanjutnya
    public void nextLoad(){
        current_page++;
        Call<DataModel> res = api.getUsers(current_page,10);
        res.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                List<UserModel> data = response.body().getContent();
                allData.addAll(data);
                adapter.notifyDataSetChanged();
                last = 0;
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
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("Tidak",null).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.reload :  pb.setVisibility(View.VISIBLE);
                                notice.setVisibility(View.GONE);
                                reload.setVisibility(View.GONE);
                                firstLoad();
                                break;
            case R.id.add_btn:  Intent intent = new Intent(MainActivity.this, AddStaff.class);
                                startActivity(intent);
                                break;
        }
    }
}
