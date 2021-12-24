package com.akash.bitsnbytesapp.customer.pojos;

public class User {
    private String Name;
    private String Phone;
    private String Email;
    private String Password;

    public User() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public User(String name, String phone, String email, String password) {
        this.Name = name;
        this.Phone = phone;
        this.Email = email;
        this.Password = password;

    }
}
