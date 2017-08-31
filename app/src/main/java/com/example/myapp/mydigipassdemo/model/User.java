package com.example.myapp.mydigipassdemo.model;

public class User {
    private String firstName;
    private String lastName;

    public User()  {
        this.firstName = "";
        this.lastName = "";
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
