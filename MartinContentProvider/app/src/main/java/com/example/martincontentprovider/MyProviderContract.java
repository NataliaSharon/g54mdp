package com.example.martincontentprovider;

import android.net.Uri;

public class MyProviderContract
{
    public static final String AUTHORITY = "com.example.martincontentprovider.MyProvider";

    public static final Uri PEOPLE_URI = Uri.parse("content://"+AUTHORITY+"/people");

    public static final Uri ANIMALS_URI = Uri.parse("content://"+AUTHORITY+"/animals");

    public static final Uri PETS_URI = Uri.parse("content://"+AUTHORITY+"/pets");

    public static final String _ID = "_id";

    public static final String NAME = "name";

    public static final String CONTENT_TYPE_SINGLE = "vnd.android.cursor.item/MyProvider.data.text";

    public static final String CONTENT_TYPE_MULTIPLE = "vnd.android.cursor.dir/MyProvider.data.text";
}