package com.example.martincontentprovider;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContentProviderUser2 extends Activity {

    SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_user2);
        queryContentProvider();
    }

    public void queryContentProvider() {

        String[] projection = new String[] {
                MyProviderContract._ID,
                MyProviderContract.NAME
        };

        String colsToDisplay [] = new String[] {
                MyProviderContract.NAME
        };

        int[] colResIds = new int[] {
                R.id.name
        };

        Cursor cursor = getContentResolver().query(MyProviderContract.PEOPLE_URI, projection, null, null, null);

        dataAdapter = new SimpleCursorAdapter(
                this,
                R.layout.activity_content_provider_user,
                cursor,
                colsToDisplay,
                colResIds,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);
    }

    public void add(View v) {

        final EditText inputField = (EditText) findViewById(R.id.editText3);
        String input = inputField.getText().toString();

        ContentValues newValues = new ContentValues();
        newValues.put(MyProviderContract.NAME, input);

        getContentResolver().insert(MyProviderContract.PEOPLE_URI, newValues);

        queryContentProvider();
    }
}
