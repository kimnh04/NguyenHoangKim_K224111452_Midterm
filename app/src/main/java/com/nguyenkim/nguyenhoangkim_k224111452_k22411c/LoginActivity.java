package com.nguyenkim.nguyenhoangkim_k224111452_k22411c;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors.SQLiteConnector;
import com.nguyenkim.nguyenhoangkim_k224111452_k22411c.models.Account;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    RadioButton rbAdmin, rbEmployee;
    Button btnLogin;
    SQLiteConnector connector;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ánh xạ view
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        rbAdmin = findViewById(R.id.rbAdmin);
        rbEmployee = findViewById(R.id.rbEmployee);
        btnLogin = findViewById(R.id.btnLogin);

        // DB setup
        connector = new SQLiteConnector(this);
        connector.copyDatabaseFromAssets();
        db = connector.openDatabase();

        // Sự kiện login
        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        int selectedType = rbAdmin.isChecked() ? 1 : 2;

        Cursor cursor = db.rawQuery(
                "SELECT * FROM Account WHERE Username = ? AND Password = ? AND TypeOfAccount = ?",
                new String[]{username, password, String.valueOf(selectedType)}
        );

        if (cursor.moveToFirst()) {
            // ⚠️ Sử dụng chỉ số cột theo thứ tự SELECT *
            int id = cursor.getInt(0);
            String uname = cursor.getString(1);
            String pwd = cursor.getString(2);
            int typeFromDb = cursor.getInt(3);

            Account account = new Account(id, uname, pwd, typeFromDb);

            Toast.makeText(this, "✅ Login success!", Toast.LENGTH_SHORT).show();

            if (selectedType == 1) {
                startActivity(new Intent(this, AdminTaskActivity.class));
            } else {
                startActivity(new Intent(this, EmployeeTaskActivity.class));
            }
        } else {
            Toast.makeText(this, "❌ Sai thông tin đăng nhập hoặc vai trò không khớp!", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
    }
}