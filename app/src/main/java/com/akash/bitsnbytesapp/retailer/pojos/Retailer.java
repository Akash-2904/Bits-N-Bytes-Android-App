package com.akash.bitsnbytesapp.retailer.pojos;

import java.io.Serializable;

public class Retailer implements Serializable {

    private String name;
    private String shop;
    private String phone;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Retailer{" +
                "name='" + name + '\'' +
                ", shop='" + shop + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Retailer(String name, String shop, String phone, String email, String password) {
        this.name = name;
        this.shop = shop;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Retailer() {
    }
}
