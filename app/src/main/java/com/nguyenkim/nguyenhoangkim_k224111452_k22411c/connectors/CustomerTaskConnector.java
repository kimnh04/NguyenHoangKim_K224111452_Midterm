package com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.CustomerTask;
import java.util.ArrayList;

public class CustomerTaskConnector {
    SQLiteDatabase db;

    public CustomerTaskConnector(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<CustomerTask> getCustomersByTaskId(int taskId) {
        ArrayList<CustomerTask> list = new ArrayList<>();
        String query = "SELECT d.ID, d.TaskForTeleSalesID, c.ID, c.Name, c.Phone, d.IsCalled " +
                "FROM TaskForTeleSalesDetails d JOIN Customer c ON d.CustomerID = c.ID " +
                "WHERE d.TaskForTeleSalesID = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(taskId)});
        while (cursor.moveToNext()) {
            list.add(new CustomerTask(
                    cursor.getInt(cursor.getColumnIndex("ID")),
                    cursor.getInt(cursor.getColumnIndex("TaskForTeleSalesID")),
                    cursor.getInt(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getString(cursor.getColumnIndex("Phone")),
                    cursor.getInt(cursor.getColumnIndex("IsCalled"))));
        }
        cursor.close();
        return list;
    }
}