// app/src/main/java/com/nguyenkim/nguyenhoangkim_k224111452_k22411c/EmployeeTaskActivity.java
package com.nguyenkim.nguyenhoangkim_k224111452_k22411c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.adapters.DatabaseAdapter;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.SQLiteConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.Task;
import java.util.*;

public class EmployeeTaskActivity extends AppCompatActivity {
    private ListView lvTasks;
    private DatabaseAdapter dbAdapter;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_task);

        lvTasks = findViewById(R.id.lvTasks);
        setupDatabase();
        loadTasks();
        setupListItemClick();
    }

    private void setupDatabase() {
        SQLiteConnector connector = new SQLiteConnector(this);
        connector.copyDatabaseFromAssets();
        dbAdapter = new DatabaseAdapter(connector.openDatabase());
    }

    private void loadTasks() {
        // For testing, using accountId = 3 (can be replaced with actual logged-in account ID)
        tasks = dbAdapter.getEmployeeTasks(3);
        ArrayList<String> displayList = new ArrayList<>();

        for (Task task : tasks) {
            String status = task.isCompleted() ? "✅" : "❌";
            String line = String.format("%s (%s) [%s] %s",
                    task.getTitle(),
                    task.getDateAssigned(),
                    task.getProgress(),
                    status
            );
            displayList.add(line);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayList);
        lvTasks.setAdapter(adapter);
    }

    private void setupListItemClick() {
        lvTasks.setOnItemClickListener((parent, view, position, id) -> {
            Task task = tasks.get(position);
            Intent intent = new Intent(this, ListCustomerActivity.class);
            intent.putExtra("taskId", task.getId());
            startActivity(intent);
        });
    }
}