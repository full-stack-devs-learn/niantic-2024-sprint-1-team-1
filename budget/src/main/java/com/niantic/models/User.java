package com.niantic.models;

public class User {
    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;


    public User() {
    }

    public User(String email, String phone, String lastName, String firstName, String userName, int userId) {
        this.email = email;
        this.phone = phone;
        this.lastName = lastName;
        this.firstName = firstName;
        this.userName = userName;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
