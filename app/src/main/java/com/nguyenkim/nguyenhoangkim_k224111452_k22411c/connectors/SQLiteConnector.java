package com.nguyenkim.nguyenhoangkim_k224111452_k22411c.connectors;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.*;

public class SQLiteConnector {
    private static final String DATABASE_NAME = "Database.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private SQLiteDatabase database = null;
    private Activity context;

    public SQLiteConnector() {}

    public SQLiteConnector(Activity context) {
        this.context = context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public Activity getContext() {
        return context;
    }

    public void copyDatabaseFromAssets() {
        String dbPath = context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
        File dbFile = new File(dbPath);

        // ❗ Bắt buộc log để bạn biết copy chưa
        Log.i("DB_COPY", "DB Path: " + dbPath);
        Log.i("DB_COPY", "DB Exists: " + dbFile.exists());

        if (dbFile.exists()) {
            Log.i("DB_COPY", "✅ DB already exists, skip copying.");
            return;
        }

        try {
            InputStream is = context.getAssets().open(DATABASE_NAME);
            File dir = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!dir.exists()) dir.mkdir();

            OutputStream os = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            os.flush();
            os.close();
            is.close();
            Log.i("DB_COPY", "✅ Database copied successfully.");
        } catch (IOException e) {
            Log.e("DB_COPY", "❌ Error copying DB: " + e.getMessage());
        }
    }

    public SQLiteDatabase openDatabase() {
        if (context == null) {
            throw new IllegalStateException("Context is not set.");
        }

        String dbPath = context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;

        if (database == null || !database.isOpen()) {
            database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
            Log.i("DB_OPEN", "✅ DB opened: " + dbPath);
        }

        return database;
    }

    public SQLiteDatabase getDatabase() {
        if (database == null || !database.isOpen()) {
            throw new IllegalStateException("Database not opened. Call openDatabase() first.");
        }
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }
}