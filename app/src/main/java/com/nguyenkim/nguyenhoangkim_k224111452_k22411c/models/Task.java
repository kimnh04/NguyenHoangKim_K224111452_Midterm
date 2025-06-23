package com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models;

public class Task {
    public int id;
    public int accountId;
    public String title;
    public String dateAssigned;
    public int isCompleted;

    public Task(int id, int accountId, String title, String dateAssigned, int isCompleted) {
        this.id = id;
        this.accountId = accountId;
        this.title = title;
        this.dateAssigned = dateAssigned;
        this.isCompleted = isCompleted;
    }

    public boolean isDone() {
        return isCompleted == 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(String dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }
}