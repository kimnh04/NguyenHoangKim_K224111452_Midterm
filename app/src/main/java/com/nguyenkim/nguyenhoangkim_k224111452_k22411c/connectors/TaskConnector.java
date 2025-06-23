package com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskConnector {

    private SQLiteDatabase db;

    // Constructor truyền vào database
    public TaskConnector(SQLiteDatabase db) {
        this.db = db;
    }

    // Lấy tất cả Task (dành cho admin)
    public List<Task> getAllTasks() {
        List<Task> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM TaskForTeleSales", null);
        while (cursor.moveToNext()) {
            list.add(new Task(
                    cursor.getInt(0),  // ID
                    cursor.getInt(1),  // AccountID
                    cursor.getString(2),  // TaskTitle
                    cursor.getString(3),  // DateAssigned
                    cursor.getInt(4)   // IsCompleted
            ));
        }
        cursor.close();
        return list;
    }

    // Lấy Task theo AccountID (dành cho employee)
    public List<Task> getTasksByAccountId(int accountId) {
        List<Task> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM TaskForTeleSales WHERE AccountID = ?", new String[]{String.valueOf(accountId)});
        while (cursor.moveToNext()) {
            list.add(new Task(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            ));
        }
        cursor.close();
        return list;
    }

    // Thêm task mới
    public void insertTask(int accountId, String title, String dateAssigned) {
        db.execSQL(
                "INSERT INTO TaskForTeleSales (AccountID, TaskTitle, DateAssigned, IsCompleted) VALUES (?, ?, ?, 0)",
                new Object[]{accountId, title, dateAssigned}
        );
    }
}