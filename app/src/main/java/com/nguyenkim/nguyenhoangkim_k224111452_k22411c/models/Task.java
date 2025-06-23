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
}