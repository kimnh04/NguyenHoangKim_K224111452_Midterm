package com.nguyenkim.nguyenhoangkim_k224111452_k22411c.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.Task;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.R;
import java.util.*;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, List<Task> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_task, parent, false);
        }

        TextView txtTitle = convertView.findViewById(R.id.txtTaskTitle);
        TextView txtDate = convertView.findViewById(R.id.txtDate);

        txtTitle.setText(task.title + (task.isDone() ? " ✅" : " ❌"));
        txtDate.setText("Ngày giao: " + task.dateAssigned);

        return convertView;
    }
}