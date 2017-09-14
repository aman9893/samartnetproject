package com.example.admin.login;

/**
 * Created by admin on 8/29/2017.
 */

public class DatabaseModel {
    private String name;
    private String roll;
    private String address;
    private String branch;
    private String contact;
    private String email;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setcontact(String contact)   {

        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }
}