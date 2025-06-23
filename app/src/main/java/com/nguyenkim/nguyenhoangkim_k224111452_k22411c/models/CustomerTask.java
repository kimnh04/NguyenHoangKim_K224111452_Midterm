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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsCalled() {
        return isCalled;
    }

    public void setIsCalled(int isCalled) {
        this.isCalled = isCalled;
    }
}