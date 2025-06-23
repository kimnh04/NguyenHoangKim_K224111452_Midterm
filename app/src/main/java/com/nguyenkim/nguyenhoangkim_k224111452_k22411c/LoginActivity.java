package com.nguyenkim.nguyenhoangkim_k224111452_k22411c;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        // Gắn Toolbar làm ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        rbAdmin = findViewById(R.id.rbAdmin);
        rbEmployee = findViewById(R.id.rbEmployee);
        btnLogin = findViewById(R.id.btnLogin);

        connector = new SQLiteConnector(this);
        connector.copyDatabaseFromAssets();
        db = connector.openDatabase();

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
            int id = cursor.getInt(0);
            String uname = cursor.getString(1);
            String pwd = cursor.getString(2);
            int typeFromDb = cursor.getInt(3);

            Account account = new Account(id, uname, pwd, typeFromDb);

            Toast.makeText(this, "✅ Login success!", Toast.LENGTH_SHORT).show();

            if (selectedType == 1) {
                startActivity(new Intent(this, AdminTaskActivity.class));
            } else {
                Intent intent = new Intent(this, EmployeeTaskActivity.class);
                intent.putExtra("accountId", account.getId());
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "❌ Sai thông tin đăng nhập hoặc vai trò không khớp!", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}