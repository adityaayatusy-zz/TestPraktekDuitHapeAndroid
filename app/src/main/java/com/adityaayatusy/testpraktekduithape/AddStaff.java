package com.adityaayatusy.testpraktekduithape;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.adityaayatusy.testpraktekduithape.api.ApiServer;
import com.adityaayatusy.testpraktekduithape.api.ResponsApi;
import com.adityaayatusy.testpraktekduithape.model.SendUserModel;
import com.adityaayatusy.testpraktekduithape.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStaff extends AppCompatActivity implements View.OnClickListener {
    //inisialisasi variabel
    public TextInputLayout name, email, address, phone, password;
    public AppCompatSpinner role;
    public String iName, iEmail, iAddress, iPhone, iPassword;
    public int iRoleId = 1;
    public Button save;
    public ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        //action bar
        getSupportActionBar().setTitle("Add Staff");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //init widget
        name = findViewById(R.id.layout_input_name);
        email = findViewById(R.id.layout_input_email);
        address = findViewById(R.id.layout_input_address);
        phone = findViewById(R.id.layout_input_phone);
        password = findViewById(R.id.layout_input_password);
        role = findViewById(R.id.roleId);
        save = findViewById(R.id.save);

        //action button
        save.setOnClickListener(this);

    }
    //tobol batal
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    //cek input nama
    public boolean validateName() {
        if (iName.isEmpty()) {
            name.setError("Field tidak boleh kosong");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }
    //cek input email
    public boolean validateEmail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (iEmail.isEmpty()) {
            email.setError("Field tidak boleh kosong");
            return false;
        } else if (!iEmail.matches(emailPattern)) {
            email.setError("Email tidak valid!");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    //cek input nomor
    public boolean validatePhone() {
        if (iPhone.isEmpty()) {
            phone.setError("Field tidak boleh kosong");
            phone.getBoxCornerRadiusBottomEnd();
            return false;
        } else {
            phone.setError(null);
            return true;
        }
    }
    //cek input password
    public boolean validatePassword() {
        if (iPassword.isEmpty()) {
            password.setError("Field tidak boleh kosong");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                save_jadwal();
                break;

        }
    }

    public void save_jadwal() {

        iName = name.getEditText().getText().toString().trim();
        iEmail = email.getEditText().getText().toString().trim();
        iAddress = address.getEditText().getText().toString().trim();
        iPhone = phone.getEditText().getText().toString().trim();
        iPassword = password.getEditText().getText().toString().trim();

        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iRoleId = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iRoleId = 1;
            }
        });

        if (!validateName() | !validateEmail() | !validatePhone() | !validatePassword()) {
            return;
        } else {
            //ProgressDialog
            pd = new ProgressDialog(this);
            pd.setMessage("Mengirim Data...");
            pd.show();

            SendUserModel sum = new SendUserModel(iName,iEmail,iPhone,iAddress,iPassword,iRoleId);
            ResponsApi api = new ApiServer().getClient().create(ResponsApi.class);
            final Call<UserModel> res = api.addUsers(sum);
              res.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    pd.hide(); pd.dismiss();

                    //cek respons api
                    if(response.body() == null){
                        phone.setError("Nomor Sudah Terdaftar!");
                        Toast.makeText(AddStaff.this, "Gagal: Phone has already been registered", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddStaff.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddStaff.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    pd.hide();
                    Log.d("Debug", t.getMessage().toString());
                    Toast.makeText(AddStaff.this, "Gagal koneksi", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}

