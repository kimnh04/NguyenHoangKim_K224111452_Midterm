package com.nguyenkim.nguyenhoangkim_k224111452_k22411c;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.CustomerTaskConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.SQLiteConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.CustomerTask;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerActivity extends AppCompatActivity {

    ListView lvCustomers;
    ArrayAdapter<String> adapter;
    ArrayList<String> displayList;
    CustomerTaskConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);

        lvCustomers = findViewById(R.id.lvCustomers);
        displayList = new ArrayList<>();

        int taskId = getIntent().getIntExtra("taskId", -1);
        if (taskId == -1) {
            Toast.makeText(this, "❌ Không có Task ID!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        SQLiteConnector dbHelper = new SQLiteConnector(this);
        SQLiteDatabase db = dbHelper.openDatabase();
        connector = new CustomerTaskConnector(db);

        List<CustomerTask> customers = connector.getCustomersByTaskId(taskId);
        for (CustomerTask c : customers) {
            String status = c.isCalled() ? "✅" : "❌";
            displayList.add(c.customerName + " (" + c.phone + ") " + status);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        lvCustomers.setAdapter(adapter);
    }
}