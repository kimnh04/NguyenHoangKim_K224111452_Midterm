package com.nguyenkim.nguyenhoangkim_k224111452_k22411c;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.CustomerTaskConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.SQLiteConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.CustomerTask;
import java.util.*;

public class EmployeeTaskActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> customerLines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_task);

        listView = findViewById(R.id.listViewCustomers);

        SQLiteConnector connector = new SQLiteConnector(this);
        CustomerTaskConnector customerConnector = new CustomerTaskConnector(connector.openDatabase());

        // Giả sử taskId = 1 để test
        List<CustomerTask> customers = customerConnector.getCustomersByTaskId(1);

        for (CustomerTask c : customers) {
            String line = c.customerName + " - " + c.phone + (c.isCalled() ? " ✅" : " ❌");
            customerLines.add(line);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, customerLines);
        listView.setAdapter(adapter);
    }
}
