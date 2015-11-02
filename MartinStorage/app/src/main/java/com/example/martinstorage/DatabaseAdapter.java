package com.example.martinstorage;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAdapter
{
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";

    private static final String SQLITE_TABLE = "myList";

    private static final String SQLITE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_NAME +
                    ");";

    private DatabaseHelper dbHelper;
    public SQLiteDatabase db;
    private Context context;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, "martinDB", null, 5);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("g54mdp", "onCreate");
            db.execSQL(SQLITE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public DatabaseAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    public void addName(String name)
    {
        db.execSQL("INSERT INTO myList (name) " +
                "VALUES " +
                "('" + name + "');");
    }

    public Cursor fetchAll()
    {
        Cursor c = db.query("myList", new String[] { "_id", "name" }, null, null, null, null, null);
        return c;
    }

}