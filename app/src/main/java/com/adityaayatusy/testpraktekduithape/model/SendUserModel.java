package com.adityaayatusy.testpraktekduithape.model;

public class SendUserModel {
    public String name,email,phone,address,password;
    public int issuerRoleId;

    public SendUserModel(String name, String email, String phone, String address, String password, int issuerRoleId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.issuerRoleId = issuerRoleId;
    }
}
