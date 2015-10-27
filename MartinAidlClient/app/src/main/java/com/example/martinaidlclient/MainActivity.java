package com.example.martinaidlclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.view.View;
import android.widget.EditText;

import com.example.martinaidlservice.IStringReverser;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(IStringReverser.class.getName());
        bindService(intent, this.connection, Context.BIND_AUTO_CREATE);
    }

    private IStringReverser service;

    private ServiceConnection connection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder iservice)
        {
            service = IStringReverser.Stub.asInterface(iservice);
        }

        public void onServiceDisconnected(ComponentName className)
        {
            service = null;
        }
    };


    public void onClickButton(View v)
    {
        final EditText inputTextBox = (EditText) findViewById(R.id.editText2);
        final EditText resultTextBox = (EditText) findViewById(R.id.editText);

        try
        {
            String toReverse = inputTextBox.getText().toString();
            String reversed = service.reverseString(toReverse);
            resultTextBox.setText(reversed);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
