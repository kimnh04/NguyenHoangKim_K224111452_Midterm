package com.nguyenkim.nguyenhoangkim_k224111452_k22411c.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.R;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.CustomerTask;

import java.util.ArrayList;

public class CustomerTaskAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CustomerTask> customerList;

    public CustomerTaskAdapter(Context context, ArrayList<CustomerTask> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public Object getItem(int position) {
        return customerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return customerList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Dùng layout item_customer_task.xml
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_customer_task, parent, false);
        }

        TextView txtName = convertView.findViewById(R.id.txtCustomerName);
        TextView txtPhone = convertView.findViewById(R.id.txtCustomerPhone);
        TextView txtStatus = convertView.findViewById(R.id.txtCallStatus);

        CustomerTask task = customerList.get(position);
        txtName.setText(task.customerName);
        txtPhone.setText(task.phone);
        txtStatus.setText(task.isCalled == 1 ? "Đã gọi" : "Chưa gọi");

        return convertView;
    }
}