package com.example.martinaidlservice;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.martinaidlservice.IStringReverser;

public class StringReverserImpl extends Service
{
    private final IStringReverser.Stub binder = new IStringReverser.Stub()
    {
        public String reverseString(String inString)
        {
            return new StringBuilder(inString).reverse().toString();
        }
    };

    @Override
    public IBinder onBind(Intent intent)
    {
        return this.binder;
    }
}
