package com.nguyenkim.nguyenhoangkim_k224111452_k22411c;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.adapters.TaskAdapter;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.SQLiteConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.TaskConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.Task;

import java.util.ArrayList;

public class EmployeeTaskActivity extends AppCompatActivity {

    private ListView lvTask;
    private ArrayList<Task> taskList;
    private TaskAdapter adapter;
    private int accountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_task);

        // Lấy ID nhân viên được truyền từ LoginActivity
        accountId = getIntent().getIntExtra("accountId", -1);
        if (accountId == -1) {
            Toast.makeText(this, "❌ Không có ID nhân viên!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Ánh xạ View
        lvTask = findViewById(R.id.lvTaskEmployee);

        // Mở DB và lấy Task theo AccountID
        SQLiteConnector connector = new SQLiteConnector(this);
        SQLiteDatabase db = connector.openDatabase();
        TaskConnector taskConnector = new TaskConnector(db);

        taskList = new ArrayList<>(taskConnector.getTasksByAccountId(accountId));
        adapter = new TaskAdapter(this, taskList);
        lvTask.setAdapter(adapter);

        // Bắt sự kiện chọn Task → mở danh sách khách
        lvTask.setOnItemClickListener((parent, view, position, id) -> {
            Task selectedTask = taskList.get(position);
            Intent intent = new Intent(this, com.nguyenkim.nguyenhoangkim_k224111452_k22411c.ListCustomerActivity.class);
            intent.putExtra("taskId", selectedTask.id);
            startActivity(intent);
        });
    }
}