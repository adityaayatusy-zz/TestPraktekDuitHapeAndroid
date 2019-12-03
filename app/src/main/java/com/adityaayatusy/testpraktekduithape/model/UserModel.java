package com.adityaayatusy.testpraktekduithape.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    public String name,email,phone,address,password;
    public int issuerRoleId;
    public RoleModel issuerRole;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleModel getIssuerRole() {
        return issuerRole;
    }

    public void setIssuerRole(RoleModel issuerRole) {
        this.issuerRole = issuerRole;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIssuerRoleId() {
        return issuerRoleId;
    }

    public void setIssuerRoleId(int issuerRoleId) {
        this.issuerRoleId = issuerRoleId;
    }
}
