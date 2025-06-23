package com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models;

public class Account {
    public int id;
    public String username;
    public String password;
    public int typeOfAccount;

    public Account(int id, String username, String password, int typeOfAccount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.typeOfAccount = typeOfAccount;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(int typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }
}
