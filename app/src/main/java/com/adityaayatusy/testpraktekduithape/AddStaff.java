package com.adityaayatusy.testpraktekduithape;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class AddStaff extends AppCompatActivity implements View.OnClickListener{
    TextInputLayout name,email,address,phone,password;
    Spinner role;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        getSupportActionBar().setTitle("Add Staff");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name =      findViewById(R.id.layout_input_name);
        email =     findViewById(R.id.layout_input_email);
        address =   findViewById(R.id.layout_input_address);
        phone   =   findViewById(R.id.layout_input_phone);
        password =  findViewById(R.id.layout_input_password);
        save = findViewById(R.id.save);

        save.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean validateName(){
        if(name.getEditText().getText().toString().trim().isEmpty()){
            name.setError("Field tidak boleh kosong");
            return false;
        }else{
            name.setError(null);
            return true;
        }
    }

    public boolean validateEmail(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String e = email.getEditText().getText().toString().trim();

        if(e.isEmpty()){
            email.setError("Field tidak boleh kosong");
            return false;
        }else if(!e.matches(emailPattern)){
            email.setError("Email tidak valid!");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }

    public boolean validateAddress(){
        if(address.getEditText().getText().toString().trim().isEmpty()){
            address.setError("Field tidak boleh kosong");
            return false;
        }else{
            address.setError(null);
            return true;
        }
    }

    public boolean validatePhone(){
        if(phone.getEditText().getText().toString().trim().isEmpty()){
            phone.setError("Field tidak boleh kosong");
            phone.getBoxCornerRadiusBottomEnd();
            return false;
        }else{
            phone.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        if(password.getEditText().getText().toString().trim().isEmpty()){
            password.setError("Field tidak boleh kosong");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                if(!validateName() | !validateAddress() | !validateEmail() | !validatePhone() | !validatePassword()){
                    return;
                }else{
                    Toast.makeText(AddStaff.this, "Berhasil", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
