package com.example.myapp;

public class registerpro {
    //should be public class
    private String Name,Email,Phone,Password;

    public registerpro(){

    }

    public registerpro(String name, String email, String phone, String password) {
       this.Name = name;
       this.Email = email;
        this.Phone = phone;
        this.Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

