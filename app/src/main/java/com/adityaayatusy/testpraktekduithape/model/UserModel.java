package com.adityaayatusy.testpraktekduithape.model;

public class UserModel {

    String name,email;
    RoleModel issuerRole;

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
}
