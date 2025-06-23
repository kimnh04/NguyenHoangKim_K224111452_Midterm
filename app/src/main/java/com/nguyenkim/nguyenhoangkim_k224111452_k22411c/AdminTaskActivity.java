package com.nguyenkim.nguyenhoangkim_k224111452_k22411c;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.adapters.TaskAdapter;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.SQLiteConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.TaskConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.Task;
import java.util.*;

public class AdminTaskActivity extends AppCompatActivity {
    ListView lvTasks;
    Button btnAddTask;
    ArrayList<Task> taskList;
    TaskAdapter adapter;
    TaskConnector taskConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_task);

        lvTasks = findViewById(R.id.listViewTasks);
        btnAddTask = findViewById(R.id.btnAddTask);

        SQLiteConnector connector = new SQLiteConnector(this);
        SQLiteDatabase db = connector.openDatabase();
        taskConnector = new TaskConnector(db);

        taskList = new ArrayList<>(taskConnector.getAllTasks());
        adapter = new TaskAdapter(this, taskList);
        lvTasks.setAdapter(adapter);

        btnAddTask.setOnClickListener(v -> showAddTaskDialog());
    }

    private void showAddTaskDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_add_task, null);
        EditText edtTitle = view.findViewById(R.id.edtTaskTitle);
        EditText edtDate = view.findViewById(R.id.edtDate);
        EditText edtAccountId = view.findViewById(R.id.edtAccountId);

        new AlertDialog.Builder(this)
                .setTitle("Thêm Task mới")
                .setView(view)
                .setPositiveButton("Thêm", (dialog, which) -> {
                    String title = edtTitle.getText().toString();
                    String date = edtDate.getText().toString();
                    int accId = Integer.parseInt(edtAccountId.getText().toString());
                    taskConnector.insertTask(accId, title, date);
                    taskList.clear();
                    taskList.addAll(taskConnector.getAllTasks());
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}