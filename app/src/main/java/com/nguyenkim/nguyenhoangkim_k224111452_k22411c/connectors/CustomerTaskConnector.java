package com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.CustomerTask;

import java.util.ArrayList;
import java.util.List;

public class CustomerTaskConnector {

    private SQLiteDatabase db;

    public CustomerTaskConnector(SQLiteDatabase db) {
        this.db = db;
    }

    // Truy vấn danh sách khách theo TaskID
    public List<CustomerTask> getCustomersByTaskId(int taskId) {
        List<CustomerTask> list = new ArrayList<>();
        String query = "SELECT d.ID, d.TaskForTeleSalesID, c.ID, c.Name, c.Phone, d.IsCalled " +
                "FROM TaskForTeleSalesDetails d JOIN Customer c ON d.CustomerID = c.ID " +
                "WHERE d.TaskForTeleSalesID = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(taskId)});
        while (cursor.moveToNext()) {
            list.add(new CustomerTask(
                    cursor.getInt(0),      // ID from TaskForTeleSalesDetails
                    cursor.getInt(1),      // TaskForTeleSalesID
                    cursor.getInt(2),      // CustomerID
                    cursor.getString(3),   // Customer Name
                    cursor.getString(4),   // Phone
                    cursor.getInt(5)       // IsCalled
            ));
        }
        cursor.close();
        return list;
    }

    // Đánh dấu đã gọi khách hàng
    public void markCustomerCalled(int detailId) {
        db.execSQL("UPDATE TaskForTeleSalesDetails SET IsCalled = 1 WHERE ID = ?",
                new Object[]{detailId});
    }
}