package com.itshizhan.gsondemo.gson;

import com.google.gson.annotations.SerializedName;

public class User {
    public String uname;
    public int age;

    @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
    public String emailAddress;

    public User(String uname, int age) {
        this.uname = uname;
        this.age = age;
    }

    public User(String uname, int age, String emailAddress) {
        this.uname = uname;
        this.age = age;
        this.emailAddress = emailAddress;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", age=" + age +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
