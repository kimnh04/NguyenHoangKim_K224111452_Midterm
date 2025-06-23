package com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models;

public class CustomerTask {
    public int id;
    public int taskId;
    public int customerId;
    public String customerName;
    public String phone;
    public int isCalled;

    public CustomerTask(int id, int taskId, int customerId, String customerName, String phone, int isCalled) {
        this.id = id;
        this.taskId = taskId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.phone = phone;
        this.isCalled = isCalled;
    }

    public boolean isCalled() {
        return isCalled == 1;
    }
}