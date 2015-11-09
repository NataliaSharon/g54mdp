package com.example.martincontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyProvider extends ContentProvider
{
    private DBHelper dbHelper = null;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MyProviderContract.AUTHORITY, "people", 1);
        uriMatcher.addURI(MyProviderContract.AUTHORITY, "animals", 2);
    }

    @Override
    public boolean onCreate() {

        Log.d("g54mdp", "contentprovider oncreate");
        this.dbHelper = new DBHelper(this.getContext(), "mydb", null, 2);
        return true;
    }

    private String getTableName(Uri uri) {

        String tableName = "";

        switch (uriMatcher.match(uri)) {

            case 1:
                tableName = "peopleList";
                break;
            case 2:
                tableName = "animalList";
                break;
            default:
                tableName = "peopleList";
        }

        return tableName;
    }


    @Override
    public String getType(Uri uri) {

        String contentType;

        if (uri.getLastPathSegment()==null)
        {
            contentType = MyProviderContract.CONTENT_TYPE_MULTIPLE;
        }
        else
        {
            contentType = MyProviderContract.CONTENT_TYPE_SINGLE;
        }

        return contentType;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName = getTableName(uri);

        long id = db.insert(tableName, null, values);

        db.close();

        Uri nu = ContentUris.withAppendedId(uri, id);

        Log.d("g54mdp", nu.toString());

        return nu;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Log.d("g54mdp", uri.toString());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        Cursor c = db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
        return c;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("not implemented :(");
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("not implemented :(");
    }

}