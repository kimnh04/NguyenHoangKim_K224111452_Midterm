package com.nguyenkim.nguyenhoangkim_k224111452_k22411c;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.CustomerTaskConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.SQLiteConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.CustomerTask;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.adapters.CustomerTaskAdapter;

import java.util.*;

public class ListCustomerActivity extends AppCompatActivity {
    ListView lvCustomers;
    CustomerTaskAdapter adapter;
    ArrayList<CustomerTask> customerList;
    CustomerTaskConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);

        lvCustomers = findViewById(R.id.lvCustomers);
        customerList = new ArrayList<>();

        int taskId = getIntent().getIntExtra("taskId", -1);
        if (taskId == -1) {
            Toast.makeText(this, "❌ Không có Task ID!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        SQLiteConnector dbHelper = new SQLiteConnector(this);
        SQLiteDatabase db = dbHelper.openDatabase();
        connector = new CustomerTaskConnector(db);

        customerList.addAll(connector.getCustomersByTaskId(taskId));
        adapter = new CustomerTaskAdapter(this, customerList);
        lvCustomers.setAdapter(adapter);

        lvCustomers.setOnItemClickListener((parent, view, position, id) -> {
            CustomerTask task = customerList.get(position);
            if (!task.isCalled()) {
                connector.markCustomerCalled(task.id);
                task.isCalled = 1;
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã gọi khách: " + task.customerName, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Khách đã được gọi rồi.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
