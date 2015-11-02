package com.example.martinstorage;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    DatabaseAdapter dbAdapter;
    SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        queryDB();
        queryDB2();

        getPreference();
    }

    public static String CONFIG_STORAGE_NAME = "my preferences";
    public static String CONFIG_PREFERENCE_1 = "preference 1";

    public void getPreference()
    {
        SharedPreferences settings = getSharedPreferences(CONFIG_STORAGE_NAME, 0);

        String pref = settings.getString(CONFIG_PREFERENCE_1, "not set");

        final EditText textBox = (EditText) findViewById(R.id.editText3);
        textBox.setText(pref);

    }

    public void setPreference(View v)
    {
        SharedPreferences settings = getSharedPreferences(CONFIG_STORAGE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        final EditText textBox = (EditText) findViewById(R.id.editText3);

        editor.putString(CONFIG_PREFERENCE_1, textBox.getText()+"");

        editor.commit();
    }

    public void queryDB() {

        StringBuilder sb = new StringBuilder();
        TextView tv = (TextView)findViewById(R.id.textView);

        //String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        Cursor c = dbAdapter.db.query("myList", new String[] { "_id", "name" }, null, null, null, null, null);

        if(c.moveToFirst())
        {
            do
            {
                int id = c.getInt(0);
                String name = c.getString(1);

                sb.append(""+id+ ": " + name);
                sb.append("\n");

                Log.d("g54mdp", id + " " + name);
            }
            while(c.moveToNext());
        }

        tv.setText(sb);
    }

    public void queryDB2() {
        Cursor cursor = dbAdapter.fetchAll();

        String[] columns = new String[] {
                DatabaseAdapter.KEY_ROWID,
                DatabaseAdapter.KEY_NAME
        };

        int[] to = new int[] {
                R.id.id,
                R.id.name,
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.item_layout,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);

    }

    public void add(View v) {

        final EditText inputField = (EditText) findViewById(R.id.editText1);
        String input = inputField.getText().toString();

        dbAdapter.addName(input);

        queryDB();
        queryDB2();
    }

}
